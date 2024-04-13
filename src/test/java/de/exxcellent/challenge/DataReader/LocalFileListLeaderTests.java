package de.exxcellent.challenge.DataReader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LocalFileListLeaderTests {
    private static Path filledFilePath;
    private static Path emptyFilePath;

    @BeforeAll
    public static void setUpClass() throws URISyntaxException {
        URL filledFileUrl = LocalFileListLeaderTests.class.getClassLoader().getResource("test.csv");
        assertNotNull(filledFileUrl, "Test CSV file not found");
        filledFilePath = Paths.get(filledFileUrl.toURI());

        URL emptyFileUrl = LocalFileListLeaderTests.class.getClassLoader().getResource("test_empty.csv");
        assertNotNull(emptyFileUrl, "Empty test CSV file not found");
        emptyFilePath = Paths.get(emptyFileUrl.toURI());
    }

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        filledFilePath = Paths.get(Objects.requireNonNull(LocalFileListLeaderTests.class.getClassLoader().getResource("test.csv")).toURI());
        emptyFilePath = Paths.get(Objects.requireNonNull(LocalFileListLeaderTests.class.getClassLoader().getResource("test_empty.csv")).toURI());
    }

    @Test
    public void testReadDataFromExistingFile_Success() throws IOException {

        LocalFileListLeader dataReader = new LocalFileListLeader();
        List<String> data = dataReader.readAllLines(filledFilePath);

        assertFalse(data.isEmpty());
        assertEquals(31, data.size());
    }

    @Test
    public void testReadDataFromEmptyFile_Success() throws IOException {
        LocalFileListLeader dataReader = new LocalFileListLeader();
        List<String> data = dataReader.readAllLines(emptyFilePath);

        assertTrue(data.isEmpty());
    }

    @Test
    public void testReadDataFromNonExistingFile_Failure() {
        Path nonExistingFilePath = Paths.get("idontexist.csv");
        LocalFileListLeader dataReader = new LocalFileListLeader();

        assertThrows(NoSuchFileException.class, () -> dataReader.readAllLines(nonExistingFilePath));
    }
}
