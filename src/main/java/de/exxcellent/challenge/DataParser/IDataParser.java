package de.exxcellent.challenge.DataParser;

public interface IDataParser <TInput, TOutput> {
    public TOutput parse(TInput input);
}
