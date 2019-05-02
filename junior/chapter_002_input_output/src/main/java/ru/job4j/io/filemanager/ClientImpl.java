package ru.job4j.io.filemanager;

import com.google.common.base.Joiner;
import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static ru.job4j.io.client.Client.*;

public class ClientImpl implements Client {
    public static final String APP_PROPERTIES = "app.properties";
    private Socket socket;

    public ClientImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
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

    @Override
    public void listFiles() {

    }

    @Override
    public void goToSubDirectory(String subDirectory) {

    }

    @Override
    public void goToParentDirectory() {

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
        final String host = config.value("network.file.manager.host");
        final String port = config.value("network.file.manager.port");

        System.out.println(
                Joiner.on(SPACE).join(CONNECTING_TO_SERVER,
                        Joiner.on(COLON).join(host, port))
        );
        try (final Socket socket = new Socket(InetAddress.getByName(host), Integer.valueOf(port))) {
            System.out.println(CONNECTED_TO_SERVER);
            ClientImpl client = new ClientImpl(socket);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
