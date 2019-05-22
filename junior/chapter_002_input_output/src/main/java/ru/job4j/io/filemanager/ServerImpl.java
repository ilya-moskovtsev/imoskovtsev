package ru.job4j.io.filemanager;

import com.google.common.base.Joiner;
import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

import static ru.job4j.io.server.Server.CLIENT_CONNECTED;
import static ru.job4j.io.server.Server.CLIENT_SAID;
import static ru.job4j.io.server.Server.EMPTY_LINE;
import static ru.job4j.io.server.Server.EXIT;
import static ru.job4j.io.server.Server.I_DON_T_UNDERSTAND;
import static ru.job4j.io.server.Server.SPACE;
import static ru.job4j.io.server.Server.WAITING_FOR_CLIENT;
import static ru.job4j.io.server.Server.WAITING_FOR_COMMAND;

public class ServerImpl implements Server {
    public static final String APP_PROPERTIES = "app.properties";
    public static final String ROOT = "/Users/ilya/IdeaProjects/imoskovtsev";
    public static final String SLASH = "/";
    // server status
    public static final String CURRENT_DIRECTORY_IS_SET_TO = "Current directory is set to";
    // user input
    private Socket socket;
    private String currentDirectory;

    public ServerImpl(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    @Override
    public void start(String root) {
        this.currentDirectory = root;
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String clientInput;
            do {
                System.out.println(WAITING_FOR_COMMAND);
                clientInput = in.readLine();
                System.out.println(Joiner.on(ru.job4j.io.server.Server.SPACE).join(CLIENT_SAID, clientInput));

                String[] split = clientInput.split(SPACE);
                String command = split[0];
                String argument = (split.length > 1) ? split[1] : null;

                DispatchPattern dispatch = new DispatchPattern(this, out);
                if (Message.Type.contains(command)) {
                    String finalCommand = command.toUpperCase();
                    Function<String, Boolean> fun = dispatch.init().get(() -> Message.Type.valueOf(finalCommand));
                    fun.apply(argument);
                } else {
                    out.println(I_DON_T_UNDERSTAND);
                    out.println(EMPTY_LINE);
                }
            } while (!(EXIT.equals(clientInput)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
