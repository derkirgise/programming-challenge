package de.exxcellent.challenge;

import de.exxcellent.challenge.config.DataConfiguration;

import java.util.List;
import java.util.Map;

import static de.exxcellent.challenge.enums.FootballHeader.*;
import static de.exxcellent.challenge.enums.WeatherHeader.*;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        if (args.length != 2) {
            System.out.println("Invalid arguments. Use --weather or --football and the file you want to read.");
            return;
        }

        try {
            String domain = args[0];
            String source = args[1];

            DataConfiguration config = new DataConfiguration(args);

            List<String> rawData = config.getDataReader().readAllLines(source);
            List<Map<String, String>> parsedData = config.getDataParser().parse(rawData);

            switch (domain) {
                case "--weather": {
                    Map<String, String> result = config.getDataProcessor().findSmallestSpread(parsedData, MXT.getHeader(), MNT.getHeader());
                    String dayWithSmallestTempSpread = result.get(DAY.getHeader());
                    System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
                    break;
                }
                case "--football": {
                    Map<String, String> result = config.getDataProcessor().findSmallestSpread(parsedData, GOALS.getHeader(), GOALS_ALLOWED.getHeader());
                    String teamWithSmallestGoalSpread = result.get(TEAM.getHeader());
                    System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
                    break;
                }
                default: {
                    System.out.println("Can only handle weather or football data so far.");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }
}
