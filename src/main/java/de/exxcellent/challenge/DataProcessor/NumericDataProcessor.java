package de.exxcellent.challenge.DataProcessor;

import de.exxcellent.challenge.enums.WeatherHeader;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NumericDataProcessor implements IDataProcessor <List<Map<String, String>>, Map<String, String>, String> {
    @Override
    public Map<String, String> findSmallestDifference(List<Map<String, String>> input, String comparatorOne, String comparatorTwo) {
        if (Objects.equals(comparatorOne, comparatorTwo)) throw new IllegalArgumentException("Cannot compare same headers.");

        double smallestDifference = Double.MAX_VALUE;
        Map<String, String> result = null;

        for (Map<String, String> row : input) {
            String valueOneString = row.get(comparatorOne);
            String valueTwoString = row.get(comparatorTwo);

            if (valueOneString != null && !valueOneString.isEmpty() && valueTwoString != null && !valueTwoString.isEmpty()) {
                try {
                    double valueOne = Double.parseDouble(valueOneString);
                    double valueTwo = Double.parseDouble(valueTwoString);

                    double difference = Math.abs(valueOne - valueTwo);

                    if (difference < smallestDifference) {
                        smallestDifference = difference;
                        result = row;
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Non-Numeric value cannot be processed.", e);
                }
            } else throw new IllegalArgumentException("Empty value cannot be processed.");
        }
        return result;
    }
}
