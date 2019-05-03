package ru.job4j.io.client;

import com.google.common.base.Joiner;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 5000;
    public static final String SPACE = " ";
    public static final String COLON = ":";
    // client status
    public static final String CONNECTING_TO_SERVER = "Connecting to server:";
    public static final String CONNECTED_TO_SERVER = "Connected to server";
    public static final String SERVER_SAID = "Server said:";
    // user input
    public static final String EXIT = "exit";

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
                    String userSaid;
                    while (true) {
                        userSaid = userInput.readLine();
                        out.println(userSaid);
                        if (EXIT.equals(userSaid)) {
                            break;
                        }
                        String serverSaid;
                        do {
                            serverSaid = in.readLine();
                            System.out.println(Joiner.on(SPACE).join(SERVER_SAID, serverSaid));
                        } while (!serverSaid.isEmpty());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(
                Joiner.on(SPACE).join(CONNECTING_TO_SERVER,
                        Joiner.on(COLON).join(HOST, PORT))
        );
        try (final Socket socket = new Socket(InetAddress.getByName(HOST), PORT)) {
            System.out.println(CONNECTED_TO_SERVER);
            Client client = new Client(socket);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
