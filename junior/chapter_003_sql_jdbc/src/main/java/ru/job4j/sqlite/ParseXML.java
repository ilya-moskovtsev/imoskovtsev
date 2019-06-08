package ru.job4j.sqlite;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileReader;

/**
 * Parsing an XML File Using SAX
 * https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
 */
public class ParseXML {
    private static final Logger LOG = LogManager.getLogger(ParseXML.class.getName());
    public static final String ATTRIBUTE_NAME = "field";

    public int sumFields(File xml) {
        LOG.info(String.format("Will parse %s for %s values and sum them", xml.getName(), ATTRIBUTE_NAME));
        final int[] sum = {0};
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            xmlReader.setContentHandler(new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                    sum[0] += atts.getValue(ATTRIBUTE_NAME) != null ? Integer.parseInt(atts.getValue(ATTRIBUTE_NAME)) : 0;
                }
            });
            xmlReader.parse(new InputSource(new FileReader(xml)));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info(String.format("The sum of all fields is %d", sum[0]));
        return sum[0];
    }
}
