package de.exxcellent.challenge.DataProcessor;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class NumericDataProcessorTests {
    private final List<Map<String, String>> correctParsedCsvData = List.of(
            new HashMap<>() {{
                put("Day", "1");
                put("MxT", "88");
                put("MnT", "59");
            }},
            new HashMap<>() {{
                put("Day", "2");
                put("MxT", "79");
                put("MnT", "63");
            }}
    );

    private final List<Map<String, String>> incorrectDataWithCharacters = List.of(
            new HashMap<>() {{
                put("Day", "Day");
                put("MxT", "88");
                put("MnT", "59");
            }},
            new HashMap<>() {{
                put("Day", "2");
                put("MxT", "79");
                put("MnT", "63");
            }}
    );

    private final List<Map<String, String>> incorrectDataMissingValue = List.of(
            new HashMap<>() {{
                put("Day", "");
                put("MxT", "88");
                put("MnT", "59");
            }},
            new HashMap<>() {{
                put("Day", "2");
                put("MxT", "79");
                put("MnT", "63");
            }}
    );

    @Test
    public void testFindSmallestDifference_Success() {
        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();
        String result = numericDataProcessor.findSmallestDifference(correctParsedCsvData, "Mxt", "Mnt");

        assertEquals("2", result);
    }

    @Test
    public void testFindSmallestDifferenceWithSameComparator_Failure() {
        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrowsExactly(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(correctParsedCsvData, "Mxt", "Mxt"));
    }

    @Test
    public void testFindSmallestDifferenceWithCharacters_Failure() {
        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrowsExactly(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(incorrectDataWithCharacters, "Mxt", "Mnt"));
    }

    @Test
    public void testFindSmallestDifferenceWithMissingValue_Failure() {
        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrowsExactly(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(incorrectDataMissingValue, "Mxt", "Mxt"));
    }
}
