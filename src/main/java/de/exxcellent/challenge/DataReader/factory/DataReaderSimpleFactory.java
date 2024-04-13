package de.exxcellent.challenge.DataReader.factory;

import de.exxcellent.challenge.DataReader.IDataReader;
import de.exxcellent.challenge.DataReader.LocalFileReader;

public class DataReaderSimpleFactory {
    public enum DataReaderTypes {
        LOCAL,
        REMOTE
    }

    public static IDataReader createDataReader (DataReaderTypes type) {
        return switch (type) {
            case LOCAL -> new LocalFileReader();
            case REMOTE -> new LocalFileReader();
        };
    }
}
