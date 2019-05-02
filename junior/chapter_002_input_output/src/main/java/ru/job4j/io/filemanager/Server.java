package ru.job4j.io.filemanager;

import java.io.File;
import java.util.List;

public interface Server {
    void start(String root);

    List<File> listFiles();

    void goToSubDirectory(String subDirectory);

    void goToParentDirectory();

    void dowloadFile(File toDownload);

    void uploadFile(File toUpload);
}
