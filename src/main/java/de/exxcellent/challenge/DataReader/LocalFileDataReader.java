package de.exxcellent.challenge.DataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocalFileDataReader implements IDataReader <Path, List<String>> {
    @Override
    public List<String> readAllLines(Path source) throws IOException {
        try {
            return Files.readAllLines(source);
        } catch (SecurityException e) {
            throw new IOException("There was an security issue while reading the file.", e);
        }
    }
}
