package de.exxcellent.challenge.DataReader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class LocalFileDataReaderTests {
    private final String filledFile = "test.csv";
    private final String emptyFile = "test_empty.csv";
    private final String nonExistingFile = "idontexist.csv";

    @Test
    public void testReadDataFromExistingFile_Success() {
        LocalFileDataReader dataReader = new LocalFileDataReader();
        List<String> data = dataReader.readAllLines(filledFile);

        assertFalse(data.isEmpty());
        assertEquals(31, data.size());
    }

    @Test
    public void testReadDataFromEmptyFile_Success() {
        LocalFileDataReader dataReader = new LocalFileDataReader();
        List<String> data = dataReader.readAllLines(emptyFile);

        assertTrue(data.isEmpty());
    }

    @Test
    public void testReadDataFromNonExistingFile_Failure() {
        LocalFileDataReader dataReader = new LocalFileDataReader();

        assertThrowsExactly(IOException.class, () -> dataReader.readAllLines(nonExistingFile));
    }
}
