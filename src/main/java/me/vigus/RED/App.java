package me.vigus.red;

import me.vigus.red.discordbot.command.discordBot;

public class App
{
    public static void main(String[] args)
    {
        try {
            discordBot.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
