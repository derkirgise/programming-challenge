package de.exxcellent.challenge.DataReader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocalFileReaderTests {
    private static String filledFilePath = "test.csv";
    private static String emptyFilePath = "test_empty.csv";

    @Test
    public void testReadDataFromExistingFile_Success() throws IOException {

        LocalFileReader dataReader = new LocalFileReader();
        List<String> data = dataReader.readAllLines(filledFilePath);

        assertFalse(data.isEmpty());
        assertEquals(31, data.size());
    }

    @Test
    public void testReadDataFromEmptyFile_Success() throws IOException {
        LocalFileReader dataReader = new LocalFileReader();
        List<String> data = dataReader.readAllLines(emptyFilePath);

        assertTrue(data.isEmpty());
    }

    @Test
    public void testReadDataFromNonExistingFile_Failure() {
        LocalFileReader dataReader = new LocalFileReader();

        assertThrows(NoSuchFileException.class, () -> dataReader.readAllLines("idontexist.csv"));
    }
}
