package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

/**
 * Generates XML from database.
 */
public class StoreXML {
    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());
    private final File target;

    @XmlRootElement
    public static class Entries {
        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }

    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        public Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }

    /**
     * @param target file to save
     */
    StoreXML(File target) {
        this.target = target;
    }

    /**
     * Uses JAXB to create xml.
     * <p>
     * XML structure:
     * <entries>
     * <entry>
     * <field>value of field</field>
     * </entry>
     * ...
     * <entry>
     * <field>value of field</field>
     * </entry>
     * </entries>
     *
     * @param list to be saved to file.
     */
    public void save(List<Entry> list) {
        LOG.info(String.format("Will save list of %d entries to %s", list.size(), target.getName()));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Entries(list), target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
