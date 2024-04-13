package de.exxcellent.challenge.DataParser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTests {

    //* Since the CSV definition is rather loose, these different inputs represent the basic types of input
    // the application should be able to handle (there could be many more...)

    private final List<String> correctInputStandard = Arrays.asList("Day,MxT,MnT", "1,88,59", "2,79,63");
    private final List<String> correctInputEmptyValue = Arrays.asList("Day,MxT,MnT", ",,", "2,79,63");
    private final List<String> incorrectInputEmptyHeaderValue= Arrays.asList("Day,,MxT", "1,88,59", "2,79,63");
    private final List<String> incorrectInputRepeatedHeaderValue= Arrays.asList("Day,MxT,MxT", "1,88,59", "2,79,63");
    private final List<String> incorrectInputMissingValues = Arrays.asList("Day,MxT,MnT", "1,88", "2,79,63");
    private final List<String> incorrectInputTooManyValues = Arrays.asList("Day,MxT,MnT", "1,88,59,18", "2,79,63");
    private final List<String> incorrectInputHeaderOnly = List.of("Day,MxT,MnT");

    @Test
    public void testParseCorrectData_Success() {
        CsvParser csvParser = new CsvParser();
        List<Map<String, String>> data = csvParser.parse(correctInputStandard);

        assertFalse(data.isEmpty());
        assertEquals(2, data.size());
        assertEquals("1", data.get(0).get("Day"));
        assertEquals("2", data.get(1).get("Day"));
    }

    @Test
    public void testParseEmptyValue_Success() {
        CsvParser csvParser = new CsvParser();
        List<Map<String, String>> data = csvParser.parse(correctInputEmptyValue);

        assertFalse(data.isEmpty());
        assertEquals(2, data.size());

        assertEquals("", data.get(0).get("Day"));
        assertEquals("2", data.get(1).get("Day"));

        assertEquals("", data.get(0).get("MxT"));
        assertEquals("79", data.get(1).get("MxT"));

        assertEquals("", data.get(0).get("MnT"));
        assertEquals("63", data.get(1).get("MnT"));
    }

    @Test
    public void testParseEmptyHeaderValue_Failure() {
        CsvParser csvParser = new CsvParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvParser.parse(incorrectInputEmptyHeaderValue));
    }

    @Test
    public void testParseRepeatedHeader_Failure() {
        CsvParser csvParser = new CsvParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvParser.parse(incorrectInputRepeatedHeaderValue));
    }

    @Test
    public void testParseMissingValue_Failure() {
        CsvParser csvParser = new CsvParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvParser.parse(incorrectInputMissingValues));
    }

    @Test
    public void testParseTooManyValues_Failure() {
        CsvParser csvParser = new CsvParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvParser.parse(incorrectInputTooManyValues));
    }

    @Test
    public void testParseHeaderOnly_Failure() {
        CsvParser csvParser = new CsvParser();

        assertThrowsExactly(IllegalArgumentException.class, () -> csvParser.parse(incorrectInputHeaderOnly));
    }
}
