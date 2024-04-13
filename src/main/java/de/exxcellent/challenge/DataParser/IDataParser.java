package de.exxcellent.challenge.DataParser;

import java.util.List;
import java.util.Map;

public interface IDataParser {
    public List<Map<String, String>> parse(List<String> input);
}
