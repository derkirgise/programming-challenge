package de.exxcellent.challenge.DataReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;



public class LocalFileReader implements IDataReader {
    @Override
    public List<String> readAllLines(String source) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(source);

        if (inputStream == null) {
            throw new NoSuchFileException("Resource file " + source + " could not be found");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        } catch (SecurityException e) {
            throw new IOException("There was a security issue while reading the file: " + source, e);
        }
    }
}
