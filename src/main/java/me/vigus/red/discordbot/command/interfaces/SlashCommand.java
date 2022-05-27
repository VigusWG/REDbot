package me.vigus.red.discordbot.command.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommand {
    public abstract void execute(SlashCommandInteractionEvent event);
}
