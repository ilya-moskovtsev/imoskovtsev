package ru.job4j.io.filemanager;

import com.google.common.base.Joiner;
import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.job4j.io.server.Server.*;

public class ServerImpl implements Server {
    public static final String APP_PROPERTIES = "app.properties";
    public static final String ROOT = "/Users/ilya/IdeaProjects/imoskovtsev";
    // user input
    public static final String LIST_FILES = "list files";
    public static final String GO_TO_SUBDIRECTORY_ = "go to subdirectory ";
    public static final String GO_TO_PARENT_DIRECTORY = "go to parent directory";
    private Socket socket;
    private String currentDirectory;

    public ServerImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void start(String root) {
        this.currentDirectory = root;
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                String clientInput;
                do {
                    System.out.println(WAITING_FOR_COMMAND);
                    clientInput = in.readLine();
                    System.out.println(Joiner.on(SPACE).join(CLIENT_SAID, clientInput));
                    if (LIST_FILES.equals(clientInput)) {
                        this.listFiles().forEach(out::println);
                        out.println(EMPTY_LINE);
                    } else if (clientInput.startsWith(GO_TO_SUBDIRECTORY_)) {
                        String subDirectory = clientInput.replace(GO_TO_SUBDIRECTORY_, "");
                        this.goToSubDirectory(subDirectory);
                        out.println(Joiner.on(" ").join("Current directory is set to", this.currentDirectory));
                        out.println(EMPTY_LINE);
                    } else if (GO_TO_PARENT_DIRECTORY.equals(clientInput)) {
                        this.goToParentDirectory();
                        out.println(Joiner.on(" ").join("Current directory is set to", this.currentDirectory));
                        out.println(EMPTY_LINE);
                    } else {
                        out.println(I_DON_T_UNDERSTAND);
                        out.println(EMPTY_LINE);
                    }
                } while (!(EXIT.equals(clientInput)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<File> listFiles() {
        return Arrays.asList(Objects.requireNonNull(new File(this.currentDirectory).listFiles()));
    }

    @Override
    public void goToSubDirectory(String subDirectory) {
        this.currentDirectory = Joiner.on("/").join(this.currentDirectory, subDirectory);
    }

    @Override
    public void goToParentDirectory() {
        String[] split = this.currentDirectory.split("/");
        this.currentDirectory = this.currentDirectory.replace(Joiner.on("").join("/", split[split.length - 1]), "");

    }

    @Override
    public void dowloadFile(File toDownload) {

    }

    @Override
    public void uploadFile(File toUpload) {

    }

    public static void main(String[] args) {
        Config config = new Config(APP_PROPERTIES);
        config.load();
        String port = config.value("network.file.manager.port");

        try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(port))) {
            System.out.println(WAITING_FOR_CLIENT);
            Socket accept = serverSocket.accept();
            System.out.println(CLIENT_CONNECTED);
            ServerImpl server = new ServerImpl(accept);
            server.start(ROOT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
