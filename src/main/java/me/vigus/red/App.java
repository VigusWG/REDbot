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
            if (args.length == 2){
                String bloxLinkToken = args[1];
                discordBot.setBloxToken(bloxLinkToken);
            }
            discordBot.setToken(token);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No arguments provided. Please give the token as an argument and optionally a bloxlink api token");
            return;
        }
        System.out.println("Started.");
        discordBot.main();
    }
}