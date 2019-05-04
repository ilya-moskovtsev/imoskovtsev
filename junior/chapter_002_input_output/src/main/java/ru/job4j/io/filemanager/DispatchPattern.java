package ru.job4j.io.filemanager;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static ru.job4j.io.filemanager.ServerImpl.CURRENT_DIRECTORY_IS_SET_TO;
import static ru.job4j.io.filemanager.ServerImpl.SLASH;
import static ru.job4j.io.server.Server.EMPTY_LINE;
import static ru.job4j.io.server.Server.SPACE;

public class DispatchPattern {
    private ServerImpl server;
    private PrintWriter out;

    public DispatchPattern(ServerImpl server, PrintWriter out) {
        this.server = server;
        this.out = out;
    }

    /**
     * Contains destinations.
     */
    private final Map<Message.Type, Function<String, Boolean>> dispatch = new HashMap<>();

    public Function<String, Boolean> listFiles() {
        return s -> {
            Arrays.asList(Objects.requireNonNull(new File(this.server.getCurrentDirectory()).listFiles())).forEach(out::println);
            out.println(EMPTY_LINE);
            return true;
        };
    }

    public Function<String, Boolean> goToParentDirectory() {
        return s -> {
            this.server.setCurrentDirectory(new File(this.server.getCurrentDirectory()).getParent());
            out.println(Joiner.on(" ").join(CURRENT_DIRECTORY_IS_SET_TO, server.getCurrentDirectory()));
            out.println(EMPTY_LINE);
            return true;
        };
    }

    public Function<String, Boolean> goToSubDirectory() {
        return subDirectory -> {
            if (subDirectory != null) {
                server.setCurrentDirectory(Joiner.on(SLASH).join(server.getCurrentDirectory(), subDirectory));
                out.println(Joiner.on(SPACE).join(CURRENT_DIRECTORY_IS_SET_TO, server.getCurrentDirectory()));
                out.println(EMPTY_LINE);
            } else {
                out.println(EMPTY_LINE);
            }
            return true;
        };
    }

    public Function<String, Boolean> downloadFile() {
        return toDownload -> {
            try (FileInputStream fis = new FileInputStream(Joiner.on(SLASH).join(server.getCurrentDirectory(), toDownload))) {
                byte[] buffer = new byte[100000];
                int read = fis.read(buffer);
                while (read != -1) {
                    server.getSocket().getOutputStream().write(buffer, 0, read);
                    read = fis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        };
    }

    public Function<String, Boolean> uploadFile() {
        return toUpload -> {
        String[] split = toUpload.split(SLASH);
        String file = split[split.length - 1];
        try (FileOutputStream fos = new FileOutputStream(Joiner.on(SLASH).join(server.getCurrentDirectory(), file))) {
            byte[] buffer = new byte[100000];
            int read;
            do {
                read = server.getSocket().getInputStream().read(buffer);
                fos.write(buffer, 0, read);
            } while (read > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return true;
        };
    }

    /**
     * Init's dispatch.
     *
     * @return current object.
     */
    public DispatchPattern init() {
        this.load(Message.Type.LS, this.listFiles());
        this.load(Message.Type.UP, this.goToParentDirectory());
        this.load(Message.Type.CD, this.goToSubDirectory());
        this.load(Message.Type.DOWNLOAD, this.downloadFile());
        this.load(Message.Type.UPLOAD, this.uploadFile());
        return this;
    }

    /**
     * Load handlers for destinations.
     *
     * @param type   Message type.
     * @param handle Handler.
     */
    public void load(Message.Type type, Function<String, Boolean> handle) {
        this.dispatch.put(type, handle);
    }

    public Function<String, Boolean> get(final Message msg) {
        return this.dispatch.get(msg.type());
    }
}
