package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Objects;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());
    public static final int N = 10000;
    public static final String ENTRIES_XML = "entries.xml";
    public static final String TRANSFORMED_XML = "transformed.xml";
    public static final String XSL = "schema.xsl";

    public static void main(String[] args) {
        Config config = new Config().init();
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            List<StoreXML.Entry> entries = storeSQL.init().generate(N).getEntries();
            new StoreXML(new File(ENTRIES_XML)).save(entries);
            URI uri = Objects.requireNonNull(Main.class.getClassLoader().getResource(XSL)).toURI();
            new TransformXML().transform(new File(ENTRIES_XML), new File(TRANSFORMED_XML), new File(uri));
            new ParseXML().sumFields(new File(TRANSFORMED_XML));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
