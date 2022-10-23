package me.vigus.red.discordbot;

import javax.security.auth.login.LoginException;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CommandDispatch;
import me.vigus.red.discordbot.command.commands.AboutMe;
import me.vigus.red.discordbot.command.commands.BadgeGameLink;
import me.vigus.red.discordbot.command.commands.Check;
import me.vigus.red.discordbot.command.commands.FavouriteItems;
import me.vigus.red.discordbot.command.commands.FriendGroupLink;
import me.vigus.red.discordbot.command.commands.Get;
import me.vigus.red.discordbot.command.commands.InventoryItems;
import me.vigus.red.discordbot.command.commands.View;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;

public class discordBot {
    static JDA bot;
    static String token;
    static String bloxToken = "";

    public static void setToken(String Token){
        token = Token;
    }

    public static void makeBot() throws InterruptedException, LoginException{
        if (bot == null){
            bot = JDABuilder.createLight(token)
                .setEventManager(new AnnotatedEventManager())
                .addEventListeners(new CommandDispatch())
                .setActivity(Activity.competing("against the cloud"))
                .build();

            bot.awaitReady(); //note: blocking
            System.out.println("The bot has fully loaded.");
            System.out.println("Started.\nThe RED discord bot was created by Vigus.\nFor more information visit https://github.com/VigusWG/REDbot ");
        }   
    }

    public static void updateCommands(boolean debugMode){
        if (debugMode){
            long guildId = 950081089278451773L;
            bot.getGuildById(guildId).updateCommands()
                .addCommands(Command.getAllCommands())
                .queue();
        }else{
            bot.updateCommands()
                .addCommands(Command.getAllCommands())
                .queue();
        }
    }


    public static void registerCommand(){
        Command.registerCommand(new Check());
        Command.registerCommand(new FriendGroupLink());
        Command.registerCommand(new View());
        Command.registerCommand(new BadgeGameLink());
        Command.registerCommand(new FavouriteItems());
        Command.registerCommand(new InventoryItems());
        Command.registerCommand(new Get());
        Command.registerCommand(new AboutMe());
    }

    public static void main() throws LoginException, InterruptedException
    {
        registerCommand();
        makeBot();
        updateCommands(true);
    }

    public static String getBloxToken() {
        return bloxToken;
    }

    public static void setBloxToken(String bloxLinkToken) {
        bloxToken = bloxLinkToken;
    }
}

