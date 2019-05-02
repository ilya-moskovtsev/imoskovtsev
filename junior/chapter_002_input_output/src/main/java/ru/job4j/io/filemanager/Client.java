package ru.job4j.io.filemanager;

import java.io.File;

public interface Client {
    void start();

    void listFiles();

    void goToSubDirectory(String subDirectory);

    void goToParentDirectory();

    void dowloadFile(File toDownload);

    void uploadFile(File toUpload);
}
