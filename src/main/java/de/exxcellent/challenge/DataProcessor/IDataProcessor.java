package de.exxcellent.challenge.DataProcessor;

public interface IDataProcessor <TInput, TOutput, TBase> {
    public TOutput findSmallestSpread(TInput input, TBase comparatorOne, TBase comparatorTwo);
}
