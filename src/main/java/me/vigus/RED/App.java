package me.vigus.RED;

import javax.security.auth.login.LoginException;

import me.vigus.RED.discordBot.command.discordBot;

public class App
{
    public static void main(String[] args)
    {
        try {
            discordBot.main();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
