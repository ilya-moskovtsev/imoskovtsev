package ru.job4j.gc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TxtFilesCache extends Cache {
    private static final Logger LOG = LogManager.getLogger(TxtFilesCache.class.getName());
    private String directory;
    private HashMap<String, SoftReference<String>> map = new HashMap<>();

    public TxtFilesCache(String directory) {
        this.directory = directory;
    }

    @Override
    public String get(String file) {
        SoftReference<String> soft = map.get(file);
        String content = null;
        if (soft == null) {
            try {
                content = new String(Files.readAllBytes(Paths.get(directory, file)));
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error(e.getMessage(), e);
            }
            map.put(file, new SoftReference<>(content));
        } else {
            content = soft.get();
        }
        return content;
    }

    @Override
    public int size() {
        return map.size();
    }
}
