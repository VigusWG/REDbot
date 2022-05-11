package me.vigus.red.robloxjava.enums;

public enum ThumnailSize {

    S30("30x30"),
    S48("48x48"),
    S60("60x60"),
    S75("75x75"),
    S100("100x100"),
    S110("110x110"),
    S140("140x140"),
    S150("150x150"),
    S150BY200("150x200"),
    S180("180x180"),
    S200("200x200"),
    S250("250x250"),
    S352("352x352"),
    S420("420x420"), //nice
    S720("720x730");


    private final String value;

    ThumnailSize(final String newValue) {
        value = newValue;
    }


    public String getValue() { return value; }
}
