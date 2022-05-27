package me.vigus.red.discordbot.command.interfaces;

import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public interface MessageCommand{
    public CustomEmbedBuilder execute(MessageContextInteractionEvent event);
}
