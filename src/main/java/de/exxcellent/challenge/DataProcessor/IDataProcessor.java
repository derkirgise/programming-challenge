package de.exxcellent.challenge.DataProcessor;

import java.util.List;
import java.util.Map;

public interface IDataProcessor {
    public Map<String, String> findSmallestSpread(List<Map<String, String >> input, String comparatorOne, String comparatorTwo);
}
