package me.vigus.red.discordbot.command.messagecommand;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public abstract class MessageCommand extends Command{

    public CustomEmbedBuilder execute(MessageContextInteractionEvent event) {
        return null;
    }
    
}
