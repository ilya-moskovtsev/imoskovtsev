package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

public class TransformXML {
    private static final Logger LOG = LogManager.getLogger(TransformXML.class.getName());

    public void transform(File src, File dst, File schema) {
        LOG.info(String.format("Will transform %s into %s using %s", src.getName(), dst.getName(), schema.getName()));
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(schema));
            transformer.transform(new StreamSource(src), new StreamResult(dst));
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
