package de.exxcellent.challenge.DataProcessor;

public interface IDataProcessor <TInput, TOutput, TComparator> {
    public TOutput findSmallestDifference(TInput input, TComparator comparatorOne, TComparator comparatorTwo);
}
