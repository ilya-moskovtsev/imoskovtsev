package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

public class SearchTest {
    File testDir;
    File testSubDir;

    @Test
    public void files() {
        Search search = new Search();
        List<File> result = search.
                filesByExtensions(testDir.getAbsolutePath(), List.of("fin", "pdf"));
        assertThat(result.get(0).getAbsolutePath(), endsWith("testdir/testFin.fin"));
        assertThat(result.get(1).getAbsolutePath(), endsWith("testdir/testsubdir/testPdf.pdf"));
    }

    @Before
    public void setUp() {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String fileSeparator = System.getProperty("file.separator");
        String testdir = tmpdir + fileSeparator + "testdir";
        String testsubdir = testdir + fileSeparator + "testsubdir";
        testDir = new File(testdir);
        testDir.mkdir();
        testSubDir = new File(testsubdir);
        testSubDir.mkdir();
        String testCsv = testdir + fileSeparator + "testCsv.csv";
        String testLog = testdir + fileSeparator + "testLog.log";
        String testFin = testdir + fileSeparator + "testFin.fin";
        String testPdf = testsubdir + fileSeparator + "testPdf.pdf";
        String testProperties = testsubdir + fileSeparator + "testProperties.properties";
        String testXlsx = testsubdir + fileSeparator + "testXlsx.xlsx";
        String testXls = testsubdir + fileSeparator + "testXls.xls";
        String testXml = testsubdir + fileSeparator + "testXml.xml";
        String testXslt = testsubdir + fileSeparator + "testXslt.xslt";

        try {
            new File(testCsv).createNewFile();
            new File(testLog).createNewFile();
            new File(testFin).createNewFile();
            new File(testPdf).createNewFile();
            new File(testProperties).createNewFile();
            new File(testXlsx).createNewFile();
            new File(testXls).createNewFile();
            new File(testXml).createNewFile();
            new File(testXslt).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        for (File file : testSubDir.listFiles()) {
            file.delete();
        }
        for (File file : testDir.listFiles()) {
            file.delete();
        }
        testDir.delete();
    }
}