package me.vigus.red.discordbot.command.usercommands;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public abstract class UserCommand extends Command{

    public abstract CustomEmbedBuilder execute(UserContextInteractionEvent event);

    
}
