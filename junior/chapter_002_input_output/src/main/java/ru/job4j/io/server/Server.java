package ru.job4j.io.server;

import com.google.common.base.Joiner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;
    public static final String SPACE = " ";
    public static final String EMPTY_LINE = "";
    // server status
    public static final String WAITING_FOR_CLIENT = "Waiting for client...";
    public static final String CLIENT_CONNECTED = "Client connected";
    public static final String WAITING_FOR_COMMAND = "Waiting for command...";
    public static final String CLIENT_SAID = "Client said:";
    public static final String SERVER_SAID = "Server said:";
    // user input
    public static final String HELLO_ORACLE = "Hello oracle";
    public static final String EXIT = "exit";
    // oracle phrases
    public static final String HELLO_USER = "Hello, dear friend, I'm oracle.";
    public static final String I_DON_T_UNDERSTAND = "I don't understand";

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                String clientInput;
                do {
                    System.out.println(WAITING_FOR_COMMAND);
                    clientInput = in.readLine();
                    System.out.println(Joiner.on(SPACE).join(CLIENT_SAID, clientInput));
                    if (HELLO_ORACLE.equals(clientInput)) {
                        out.println(HELLO_USER);
                        System.out.println(Joiner.on(SPACE).join(SERVER_SAID, HELLO_USER));
                        out.println(EMPTY_LINE);
                    } else if (!(EXIT.equals(clientInput))) {
                        out.println(I_DON_T_UNDERSTAND);
                        System.out.println(Joiner.on(SPACE).join(SERVER_SAID, I_DON_T_UNDERSTAND));
                        out.println(EMPTY_LINE);
                    }
                } while (!(EXIT.equals(clientInput)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(WAITING_FOR_CLIENT);
            Socket socket = serverSocket.accept();
            System.out.println(CLIENT_CONNECTED);
            Server server = new Server(socket);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
