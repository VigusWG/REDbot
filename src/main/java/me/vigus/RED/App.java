package me.vigus.RED;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import me.vigus.RED.command.Command;
import me.vigus.RED.command.CommandDispatch;
import me.vigus.RED.commands.Help;
import me.vigus.RED.options.RobloxUser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) throws LoginException, InterruptedException
    {
        Command.registerCommand(new Help());
        

        JDA jda = JDABuilder.createLight("NzAyMTQ4MDQxNzEyNTk5MDky.Xp70Ug.3tUevKp9qq-jKfSl2dAKUqbOdUY", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .setEventManager(new AnnotatedEventManager())
            .addEventListeners(new App())
            .addEventListeners(new CommandDispatch())
            .setActivity(Activity.playing("Type !ping"))
            .build();

        jda.awaitReady();

        long guildId = 941005587540508753L;
        jda.getGuildById(guildId).updateCommands()
            .addCommands(new CommandDataImpl("say", "to say stuff")
                .addOption(OptionType.STRING, "content", "What do you wanna say", true)
                .addOption(OptionType.BOOLEAN, "show", "show it>", false))
                
            .addCommands(new CommandDataImpl("hello", "to button stuff"))
            .addCommands(new CommandDataImpl("info", "to info stuff"))
            .addCommands(new CommandDataImpl("support", "to 10 second stuff"))
            .addCommands(new CommandDataImpl("roblox", "test")
                .addOptions(RobloxUser.getOption()))
            .addCommands(Command.getAllCommands())
            .queue();
    }

    @SubscribeEvent
    public void onReady(ReadyEvent event){
        System.out.println("Successfully stareted something idk what.");
    }

        
    @SubscribeEvent
    public void onSlash(SlashCommandInteractionEvent event){
        if (event.getName().equals("say")) {
            if (event.getOption("show").getAsBoolean()){
                event.reply(event.getOption("content").getAsString()).setEphemeral(false).queue();
            }else{
                event.reply(event.getOption("content").getAsString()).queue();
            }
        }
    }


    @SubscribeEvent
    public void onRobloxcmd(SlashCommandInteractionEvent event){
        if (event.getName().equals("roblox")) {
            event.reply(event.getOption("robloxuser").getAsString()).queue();
        }
    }

    @SubscribeEvent
    public void onSlashCommand(SlashCommandInteractionEvent event) {
        event.getHook().setEphemeral(true);

        if (event.getName().equals("hello")) {
            event.reply("Click the button to say hello")
                .addActionRow(
                  Button.primary("hello", "Click Me"), // Button with only a label
                  Button.success("emoji", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji
                .addActionRow(
                    Button.danger("idk", "look at it idiot")
                )
                .queue();

        } else if (event.getName().equals("info")) {
            event.reply("Click the buttons for more info")
                .addActionRow( // link buttons don't send events, they just open a link in the browser when clicked
                    Button.link("https://github.com/DV8FromTheWorld/JDA", "GitHub")
                      .withEmoji(Emoji.fromMarkdown("<:github:849286315580719104>")), // Link Button with label and emoji
                    Button.link("https://ci.dv8tion.net/job/JDA/javadoc/", "Javadocs")) // Link Button with only a label
                .queue();
        }else if (event.getName().equals("support")){
            event.deferReply(true);
            event.getHook().editOriginal("oh god")
                .queueAfter(10, TimeUnit.SECONDS);
        }
    }
 
    @SubscribeEvent
    public void onButtonClick(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        }else if (event.getComponentId().equals("idk")){

            EmbedBuilder emd = new EmbedBuilder();
            for (int i=0; i < 30; i++){
                emd.addField("fuck my life", Integer.toString(i), false);
            }
            
             
            event.replyEmbeds(emd.build()).queue();
        }
    }
}
