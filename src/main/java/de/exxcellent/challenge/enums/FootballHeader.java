package de.exxcellent.challenge.enums;

public enum FootballHeader {
    TEAM("Team"),
    GAMES("Games"),
    WINS("Wins"),
    LOSSES("Losses"),
    DRAWS("Draws"),
    GOALS("Goals"),
    GOALS_ALLOWED("Goals Allowed"),
    POINTS("Points");

    private final String header;

    FootballHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return this.header;
    }
}
