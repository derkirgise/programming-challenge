package de.exxcellent.challenge.DataParser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvParser implements IDataParser {
    @Override
    public List<Map<String, String>> parse(List<String> input) {
        // input list needs to contain at least two rows (header and values)
        if (input == null || input.isEmpty() || input.size() == 1)
            throw new IllegalArgumentException("Parsing error: Input list does not contain any entries.");

        List<String> header = Arrays.stream(input.get(0).split(",")).map(String::trim).toList();

        if (header.stream().anyMatch(String::isEmpty))
            throw new IllegalArgumentException("Parsing error: Input has empty headers.");

        if (header.size() > new HashSet<>(header).size())
            throw new IllegalArgumentException("Parsing error: Input has duplicate headers.");

        return input.stream()
            .skip(1)
            .map(row -> {
                // extracting and trimming values from each row, also keeping empty entries
                List<String> values = Arrays.stream(row.split(",", -1)).map(String::trim).toList();

                if (!Objects.equals(values.size(), header.size()))
                    throw new IllegalArgumentException("Parsing error: The number of values in the row does not match the number of headers.");

                return IntStream.range(0, header.size())
                        .boxed().collect(Collectors.toMap(header::get, values::get));
            })
            .toList();
    }
}
