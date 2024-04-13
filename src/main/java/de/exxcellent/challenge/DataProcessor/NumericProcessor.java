package de.exxcellent.challenge.DataProcessor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NumericProcessor implements IDataProcessor {
    @Override
    public Map<String, String> findSmallestSpread(List<Map<String, String>> input, String comparatorOne, String comparatorTwo) {
        if (Objects.equals(comparatorOne, comparatorTwo)) throw new IllegalArgumentException("Cannot compare same headers.");

        double smallestSpread = Double.MAX_VALUE;
        Map<String, String> result = null;

        for (Map<String, String> row : input) {
            String valueOneString = row.get(comparatorOne);
            String valueTwoString = row.get(comparatorTwo);

            if (valueOneString != null && !valueOneString.isEmpty() && valueTwoString != null && !valueTwoString.isEmpty()) {
                try {
                    double valueOne = Double.parseDouble(valueOneString);
                    double valueTwo = Double.parseDouble(valueTwoString);

                    double spread = Math.abs(valueOne - valueTwo);

                    if (spread < smallestSpread) {
                        smallestSpread = spread;
                        result = row;
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Non-Numeric value cannot be processed.", e);
                }
            }
        }
        if (result != null) return result;
        throw new IllegalArgumentException("Input did not contain processable fields.");
    }
}
