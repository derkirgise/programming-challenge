package de.exxcellent.challenge.enums;

public enum WeatherHeader {
    DAY("Day"),
    MXT("MxT"),
    MNT("MnT"),
    AVT("AvT"),
    AVDP("AvDP"),
    ONE_HR_P_TPcpn("1HrP TPcpn"),
    PDIR("PDir"),
    AVSP("AvSp"),
    DIR("Dir"),
    MXS("MxS"),
    SKYC("SkyC"),
    MXR("MxR"),
    MN("Mn"),
    R_AVSLP("R AvSLP");

    private final String header;

    WeatherHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return this.header;
    }
}