package de.exxcellent.challenge.DataProcessor;

import de.exxcellent.challenge.enums.WeatherHeader;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.exxcellent.challenge.enums.WeatherHeader.*;
import static javax.swing.UIManager.put;
import static org.junit.jupiter.api.Assertions.*;

public class NumericDataProcessorTests {

    private final Map<String, String> correctMapOne = new HashMap<>() {{
        put(DAY.getHeader(), "1");
        put(MXT.getHeader(), "88");
        put(MNT.getHeader(), "59");
    }};

    private final Map<String, String> correctMapTwo = new HashMap<>() {{
        put(DAY.getHeader(), "2");
        put(MXT.getHeader(), "79");
        put(MNT.getHeader(), "63");
    }};

    private final Map<String, String> incorrectMapWithNonNumericField = new HashMap<>() {{
        put(DAY.getHeader(), "1");
        put(MXT.getHeader(), MXT.getHeader());
        put(MNT.getHeader(), "63");
    }};

    private final Map<String, String> incorrectMapWithEmptyField = new HashMap<>() {{
        put(DAY.getHeader(), "1");
        put(MXT.getHeader(), "");
        put(MNT.getHeader(), "63");
    }};

    @Test
    public void testFindSmallestDifference_Success() {
        List<Map<String, String>> correctParsedCsvData = List.of(correctMapOne, correctMapTwo);

        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();
        Map<String, String> result = numericDataProcessor.findSmallestDifference(correctParsedCsvData, MXT.getHeader(), MNT.getHeader());

        assertEquals(correctMapTwo, result);
    }

    @Test
    public void testFindSmallestDifferenceWithSameComparator_Failure() {
        List<Map<String, String>> correctParsedCsvData = List.of(correctMapOne, correctMapTwo);

        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(correctParsedCsvData, MXT.getHeader(), MXT.getHeader()));
    }

    @Test
    public void testFindSmallestDifferenceWithNonNumericField_Failure() {
        List<Map<String, String>> incorrectDataWithNonNumericData = List.of(correctMapOne, incorrectMapWithNonNumericField);

        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(incorrectDataWithNonNumericData, MXT.getHeader(), MNT.getHeader()));
    }

    @Test
    public void testFindSmallestDifferenceWithMissingValue_Failure() {
        List<Map<String, String>> incorrectDataMissingValue = List.of(correctMapOne, incorrectMapWithEmptyField);

        NumericDataProcessor numericDataProcessor = new NumericDataProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericDataProcessor.findSmallestDifference(incorrectDataMissingValue, MXT.getHeader(), MNT.getHeader()));
    }
}
