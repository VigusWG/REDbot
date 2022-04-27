package me.vigus.red.discordbot;

import javax.security.auth.login.LoginException;

import me.vigus.red.discordbot.command.slashcommands.Help;
import me.vigus.red.discordbot.command.usercommands.TestUser;
import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CommandDispatch;
import me.vigus.red.discordbot.command.slashcommands.FieldTest;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class discordBot {

    static JDA bot;

    public static void makeBot() throws InterruptedException, LoginException{
        if (bot == null){
            bot = JDABuilder.createLight("NzAyMTQ4MDQxNzEyNTk5MDky.Xp70Ug.3tUevKp9qq-jKfSl2dAKUqbOdUY", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .setEventManager(new AnnotatedEventManager())
            .addEventListeners(new CommandDispatch())
            .setActivity(Activity.competing("big competitions"))
            .build();

        bot.awaitReady(); //note: blocking
        }
    }

    public static void updateCommands(boolean debugMode){
        if (debugMode){
            long guildId = 941005587540508753L;
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
        Command.registerCommand(new Help());
        Command.registerCommand(new FieldTest());
        Command.registerCommand(new TestUser());
    }

    public static void main() throws LoginException, InterruptedException
    {
        registerCommand();
        makeBot();
        updateCommands(true);

    }


    @SubscribeEvent
    public void onReady(ReadyEvent event){
        System.out.println("Successfully stareted something idk what.");
    }
}

