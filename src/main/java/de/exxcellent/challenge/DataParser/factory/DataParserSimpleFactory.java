package de.exxcellent.challenge.DataParser.factory;


import de.exxcellent.challenge.DataParser.CsvParser;
import de.exxcellent.challenge.DataParser.IDataParser;

public class DataParserSimpleFactory {
    public enum DataParserTypes {
        CSV,
        JSON
    }

    public static IDataParser createDataParser (DataParserTypes type) {
        return switch (type) {
            case CSV -> new CsvParser();
            case JSON -> new CsvParser();
        };
    }
}
