package de.exxcellent.challenge.DataReader;

public interface IDataReader <TOutput> {
    public TOutput readAllLines(String source);
}