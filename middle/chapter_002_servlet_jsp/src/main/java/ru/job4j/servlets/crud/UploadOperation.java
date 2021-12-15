package ru.job4j.servlets.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Part filePart = req.getPart("file");

            Path uploads = Paths.get(System.getProperty("java.io.tmpdir"), "uploads");
            if (Files.notExists(uploads)) {
                Files.createDirectory(uploads);
            }

            Path file = Paths.get(uploads.toString(), filePart.getSubmittedFileName());
            Files.createFile(file);

            Files.write(file, filePart.getInputStream().readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(String.format("%s/upload", req.getContextPath()));
    }
}
