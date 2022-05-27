package me.vigus.red.discordbot.command.interfaces;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface Buttons {
    public abstract void onButtonPressed(ButtonInteractionEvent event);
}
