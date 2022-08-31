package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class AboutMe extends Command implements SlashCommand{

    /*
     * NOTE TO ANYONE THAT DOWNLOADS AND SELF HOSTS:
     * Obviously you can whatever you like with this (assumiong it follows the
     * licensing stuff)
     * But if you could leave this, or at least the credit that it gives me, I would
     * really appreciate that. I deserve a lil credit :).
     * Oh also please star the github lol.
     */

    public AboutMe(){
        name = "about";
        description = "about the bot";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("About the bot");
        b.setDescription("This bot was made by Vigus WG. This project started off as a lil project for a roleplay intelligence groups in like 2019, and the proceeded to get popular. This is now the 3rd \"version\" of the bot, due to discord forcing me to implement slash commands. If I'm honest I loathe this project and the fact it got so popular (compared to a different one of my projects) but oh well.\n\nYou can see all the commands by doing /, or do get.\nYou can join the discord server with this link: https://discord.gg/TG66NSem5A\nYou can star the project on github: https://github.com/VigusWG/REDbot\nOh and if you can sponsor me at https://github.com/sponsors/VigusWG");
        event.replyEmbeds(b.formattedBuild()).queue();
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description));
        return F;
    }
    

    
}
