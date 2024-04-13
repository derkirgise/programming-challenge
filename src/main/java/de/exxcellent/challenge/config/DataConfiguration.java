package de.exxcellent.challenge.config;

import de.exxcellent.challenge.DataParser.IDataParser;
import de.exxcellent.challenge.DataParser.factory.DataParserSimpleFactory;
import de.exxcellent.challenge.DataProcessor.IDataProcessor;
import de.exxcellent.challenge.DataProcessor.factory.DataProcessorSimpleFactory;
import de.exxcellent.challenge.DataReader.IDataReader;
import de.exxcellent.challenge.DataReader.factory.DataReaderSimpleFactory;

import java.util.Objects;

import static de.exxcellent.challenge.DataParser.factory.DataParserSimpleFactory.DataParserTypes;
import static de.exxcellent.challenge.DataParser.factory.DataParserSimpleFactory.DataParserTypes.CSV;
import static de.exxcellent.challenge.DataProcessor.factory.DataProcessorSimpleFactory.DataProcessorTypes;
import static de.exxcellent.challenge.DataProcessor.factory.DataProcessorSimpleFactory.DataProcessorTypes.NUMERIC;
import static de.exxcellent.challenge.DataReader.factory.DataReaderSimpleFactory.DataReaderTypes;
import static de.exxcellent.challenge.DataReader.factory.DataReaderSimpleFactory.DataReaderTypes.LOCAL;

public class DataConfiguration {
    private IDataReader dataReader;
    private IDataParser dataParser;
    private IDataProcessor dataProcessor;

    public DataConfiguration(String[] args) {
        String domain = args[0];
        String source = args[1];

        configure(domain, source);
    }

    private void configure (String domain, String source) {
        DataReaderTypes dataReaderType;
        DataParserTypes dataParserType;
        DataProcessorTypes dataProcessorType;

        // logic to decide what kind of reader we might need
        dataReaderType = (source.split("\\.").length == 2) ? LOCAL : LOCAL;

        // logic to decide what kind of parser we need
        dataParserType = (Objects.equals(source.split("\\.")[1], "csv")) ? CSV : CSV;

        // logic to decide what kind of processing we need
        dataProcessorType = NUMERIC;

        dataReader = DataReaderSimpleFactory.createDataReader(dataReaderType);
        dataParser = DataParserSimpleFactory.createDataParser(dataParserType);
        dataProcessor = DataProcessorSimpleFactory.createDataProcessor(dataProcessorType);
    }

    public IDataReader getDataReader() {
        return dataReader;
    }

    public IDataParser getDataParser() {
        return dataParser;
    }

    public IDataProcessor getDataProcessor() {
        return dataProcessor;
    }
}
