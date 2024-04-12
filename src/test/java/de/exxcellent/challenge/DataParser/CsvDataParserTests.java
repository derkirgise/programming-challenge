package de.exxcellent.challenge.DataParser;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CsvDataParserTests {

    //* Since the CSV definition is rather loose, these different inputs represent the basic types of input
    // the application should be able to handle (there could be many more...)

    private final List<String> correctInputStandard = Arrays.asList("Day,MxT,MnT", "1,88,59", "2,79,63");
    private final List<String> correctInputEmptyValue = Arrays.asList("Day,MxT,MnT", "1,88,", "2,79,63");
    private final List<String> incorrectInputRepeatedHeaderValue= Arrays.asList("Day,MxT,MxT", "1,88,59", "2,79,63");
    private final List<String> incorrectInputMissingValues = Arrays.asList("Day,MxT,MnT", "1,88", "2,79,63");
    private final List<String> incorrectInputTooManyValues = Arrays.asList("Day,MxT,MnT", "1,88,59,18", "2,79,63");
    private final List<String> incorrectInputHeaderOnly = List.of("Day,MxT,MnT");

    @Test
    public void testParseCorrectData_Success() {
        CsvDataParser csvDataParser = new CsvDataParser();
        List<Map<String, String>> data = csvDataParser.parse(correctInputStandard);

        assertFalse(data.isEmpty());
        assertEquals(2, data.size());
        assertEquals("1", data.get(0).get("Day"));
        assertEquals("2", data.get(1).get("Day"));
    }

    @Test
    public void testParseEmptyValue_Success() {
        CsvDataParser csvDataParser = new CsvDataParser();
        List<Map<String, String>> data = csvDataParser.parse(correctInputEmptyValue);

        assertFalse(data.isEmpty());
        assertEquals(2, data.size());
        assertEquals("", data.get(0).get("MxT"));
        assertEquals("63", data.get(1).get("MxT"));
    }

    @Test
    public void testParseRepeatedHeader_Failure() {
        CsvDataParser csvDataParser = new CsvDataParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvDataParser.parse(incorrectInputRepeatedHeaderValue));
    }

    @Test
    public void testParseMissingValue_Failure() {
        CsvDataParser csvDataParser = new CsvDataParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvDataParser.parse(incorrectInputMissingValues));
    }

    @Test
    public void testParseTooManyValues_Failure() {
        CsvDataParser csvDataParser = new CsvDataParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvDataParser.parse(incorrectInputTooManyValues));
    }

    @Test
    public void testParseHeaderOnly_Failure() {
        CsvDataParser csvDataParser = new CsvDataParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvDataParser.parse(incorrectInputHeaderOnly));
    }
}
