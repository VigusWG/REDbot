package me.vigus.red.discordbot.command.interfaces;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public interface UserCommand{

    public abstract void execute(UserContextInteractionEvent event);
}
