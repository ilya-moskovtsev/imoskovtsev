package ru.job4j.io.filemanager;

public interface Client {
    void start();

    void downloadFile(String toDownload);

    void uploadFile(String toUpload);
}
