package ru.job4j.downloader;

import org.apache.commons.httpclient.HttpConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private static final Logger LOG = LogManager.getLogger(Downloader.class.getName());

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("downloader <url> <speed limit in kilobytes per second>");
        }

        String urlString = args[0];

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
        }
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
