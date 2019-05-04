package ru.job4j.io.filemanager;

import com.google.common.base.Joiner;
import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static ru.job4j.io.client.Client.COLON;
import static ru.job4j.io.client.Client.CONNECTED_TO_SERVER;
import static ru.job4j.io.client.Client.CONNECTING_TO_SERVER;
import static ru.job4j.io.client.Client.EXIT;
import static ru.job4j.io.client.Client.SERVER_SAID;
import static ru.job4j.io.client.Client.SPACE;
import static ru.job4j.io.client.Client.EMPTY_LINE;

public class ClientImpl implements Client {
    public static final String APP_PROPERTIES = "app.properties";
    // client status
    public static final String DOWNLOAD_COMPLETE = "Download complete";
    public static final String UPLOAD_COMPLETE = "Upload complete";
    // user input
    public static final String DOWNLOAD_FILE = "download ";
    public static final String UPLOAD_FILE = "upload ";
    private Socket socket;

    public ClientImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String userSaid;
            while (true) {
                userSaid = userInput.readLine();
                out.println(userSaid);
                if (EXIT.equals(userSaid)) {
                    break;
                } else if (userSaid.startsWith(DOWNLOAD_FILE)) {
                    this.downloadFile(userSaid.replace(DOWNLOAD_FILE, EMPTY_LINE));
                    System.out.println(DOWNLOAD_COMPLETE);
                } else if (userSaid.startsWith(UPLOAD_FILE)) {
                    this.uploadFile(userSaid.replace(UPLOAD_FILE, EMPTY_LINE));
                    System.out.println(UPLOAD_COMPLETE);
                } else {
                    String serverSaid;
                    do {
                        serverSaid = in.readLine();
                        System.out.println(Joiner.on(SPACE).join(SERVER_SAID, serverSaid));
                    } while (!serverSaid.isEmpty());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void downloadFile(String toDownload) {
        try (FileOutputStream fos = new FileOutputStream("Downloaded " + toDownload)) {
            byte[] buffer = new byte[100000];
            int read;
            do {
                read = socket.getInputStream().read(buffer);
                fos.write(buffer, 0, read);
            } while (read > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadFile(String toUpload) {
        try (FileInputStream fis = new FileInputStream(toUpload)) {
            byte[] buffer = new byte[100000];
            int read = fis.read(buffer);
            while (read != -1) {
                socket.getOutputStream().write(buffer, 0, read);
                read = fis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
