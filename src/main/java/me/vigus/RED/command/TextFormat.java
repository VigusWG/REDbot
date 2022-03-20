package me.vigus.RED.command;

public interface TextFormat {
    public static String getCode(int format, int colour, int background){
        if (background != 0) {
            return String.format("\u001b[%d;%d;%dm", format, colour, background);
        }
        else{
            return String.format("\u001b[%d;%dm", format, colour);
        }
    }

    public static String getCode(int format, int colour){
        return getCode(format, colour, 0);
    }

    public static String getCode(int colour){
        return getCode(0, colour, 0);
    }
}
