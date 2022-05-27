package me.vigus.red.discordbot.command.interfaces;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public interface SelectionMenu {
    public abstract void onMenuInteraction(SelectMenuInteractionEvent event);
}
