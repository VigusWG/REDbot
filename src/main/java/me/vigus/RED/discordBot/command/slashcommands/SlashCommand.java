package me.vigus.red.discordbot.command.slashcommands;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public abstract class SlashCommand extends Command{

    protected String description;

    public abstract CustomEmbedBuilder execute(SlashCommandInteractionEvent event);

    public abstract void onButtonPressed(ButtonInteractionEvent event);
    public abstract void onMenuInteraction(SelectMenuInteractionEvent event);
    
}
