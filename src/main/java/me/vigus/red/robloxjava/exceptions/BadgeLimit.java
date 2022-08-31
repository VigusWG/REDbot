package me.vigus.red.robloxjava.exceptions;

public class BadgeLimit extends Exception{
    // This error is brought to you by Dayz, who (at time of writing) has 69k badges

    public BadgeLimit(){
        super("The user has more badges we can count.");
    }
}
