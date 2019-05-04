package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private Args argz;

    public Zip(Args argz) {
        this.argz = argz;
    }

    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                if (source.isDirectory()) {
                    zip.putNextEntry(new ZipEntry(relativize(source) + "/"));
                } else {
                    zip.putNextEntry(new ZipEntry(relativize(source)));
                    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                        zip.write(in.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String relativize(File source) {
        return new File(argz.directory()).toURI().relativize(new File(source.getPath()).toURI()).getPath();
    }

    public static void main(String[] args) {
        Args argz = new Args(args);

        List<File> files = new Search().filesThatExcludeExtensions(argz.directory(), List.of(argz.exclude()));

        new Zip(argz).pack(files, new File(argz.output()));
    }
}
