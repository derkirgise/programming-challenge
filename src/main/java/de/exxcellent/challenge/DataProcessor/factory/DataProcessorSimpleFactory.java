package de.exxcellent.challenge.DataProcessor.factory;

import de.exxcellent.challenge.DataProcessor.IDataProcessor;
import de.exxcellent.challenge.DataProcessor.NumericProcessor;

public class DataProcessorSimpleFactory {
    public enum DataProcessorTypes {
        NUMERIC,
        TIME
    }

    public static IDataProcessor createDataProcessor (DataProcessorTypes type) {
        return switch (type) {
            case NUMERIC -> new NumericProcessor();
            case TIME -> new NumericProcessor();
        };
    }
}
