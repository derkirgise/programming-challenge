package de.exxcellent.challenge.DataReader;

import java.io.IOException;
import java.util.List;

public interface IDataReader {
    public List<String> readAllLines(String source) throws IOException;
}