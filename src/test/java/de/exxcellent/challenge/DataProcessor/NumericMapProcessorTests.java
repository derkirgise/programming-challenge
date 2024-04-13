package de.exxcellent.challenge.DataProcessor;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.exxcellent.challenge.enums.WeatherHeader.*;
import static org.junit.jupiter.api.Assertions.*;

public class NumericMapProcessorTests {

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
    public void testFindSmallestSpread_Success() {
        List<Map<String, String>> correctParsedCsvData = List.of(correctMapOne, correctMapTwo);

        NumericMapProcessor numericMapProcessor = new NumericMapProcessor();
        Map<String, String> result = numericMapProcessor.findSmallestSpread(correctParsedCsvData, MXT.getHeader(), MNT.getHeader());

        assertEquals(correctMapTwo, result);
    }

    //* Function can handle single empty fields, but at least one map must be comparable
    @Test
    public void testFindSmallestSpreadWithMissingValue_Success() {
        List<Map<String, String>> incorrectDataMissingValue = List.of(correctMapOne, incorrectMapWithEmptyField);

        NumericMapProcessor numericMapProcessor = new NumericMapProcessor();
        Map<String, String> result = numericMapProcessor.findSmallestSpread(incorrectDataMissingValue, MXT.getHeader(), MNT.getHeader());

        assertEquals(correctMapOne, result);
    }

    @Test
    public void testFindSmallestSpreadWithMissingValue_Failure() {
        List<Map<String, String>> incorrectDataMissingValue = List.of(incorrectMapWithEmptyField);

        NumericMapProcessor numericMapProcessor = new NumericMapProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericMapProcessor.findSmallestSpread(incorrectDataMissingValue, MXT.getHeader(), MNT.getHeader()));
    }

    @Test
    public void testFindSmallestSpreadWithSameComparator_Failure() {
        List<Map<String, String>> correctParsedCsvData = List.of(correctMapOne, correctMapTwo);

        NumericMapProcessor numericMapProcessor = new NumericMapProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericMapProcessor.findSmallestSpread(correctParsedCsvData, MXT.getHeader(), MXT.getHeader()));
    }

    @Test
    public void testFindSmallestSpreadWithNonNumericField_Failure() {
        List<Map<String, String>> incorrectDataWithNonNumericData = List.of(correctMapOne, incorrectMapWithNonNumericField);

        NumericMapProcessor numericMapProcessor = new NumericMapProcessor();

        assertThrows(IllegalArgumentException.class,
                () -> numericMapProcessor.findSmallestSpread(incorrectDataWithNonNumericData, MXT.getHeader(), MNT.getHeader()));
    }
}
