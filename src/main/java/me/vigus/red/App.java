package me.vigus.red;

import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;

import me.vigus.red.discordbot.discordBot;


public class App
{
    public static void main(String[] args) throws InterruptedException, ExecutionException, LoginException
    {
        try {
            String token = args[0];
            discordBot.setToken(token);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No arguments provided. Please give the token as an argument.");
            return;
        }
        System.out.println("Started.");
        discordBot.main();
    }
}