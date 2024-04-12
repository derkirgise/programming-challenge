package de.exxcellent.challenge.DataReader;

import java.io.IOException;

public interface IDataReader <TInput, TOutput> {
    public TOutput readAllLines(TInput source) throws IOException;
}