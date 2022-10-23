package me.vigus.red;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;

import me.vigus.red.discordbot.discordBot;


public class App
{
    public static void main(String[] args) throws InterruptedException, ExecutionException, LoginException
    {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("properties.config"));
        } catch (FileNotFoundException e1) {
            System.err.println("No Properties file was found. Please make one, or look at the github for more information");
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            System.err.println("IO exception");
            e1.printStackTrace();
            return;
        }
        String token = props.getProperty("discordSecret");
        String bloxLinkToken = props.getProperty("bloxLinkToken");
        
        if (token == null){
            System.err.println("A token is not provided in the config file. Please see the github page for more info.");
            return;
        }

        discordBot.setToken(token);
        discordBot.setBloxToken(bloxLinkToken);

        System.out.println("Started.\nThe RED discord bot was created by Vigus.\nFor more information visit https://github.com/VigusWG/REDbot ");
        discordBot.main();
    }
}