package me.vigus.RED.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

public class CommandDispatch {

    @SubscribeEvent
    public void onSlashCommand(SlashCommandInteractionEvent event){
        Command com = Command.getCommand(event.getName());
        event.deferReply(com.ephemeral).queue();
        CustomEmbedBuilder x = com.execute(event);
        event.getHook().editOriginalEmbeds(x.formattedBuild()).queue();
    }

    @SubscribeEvent
    public void onButtonClick(ButtonInteractionEvent event) {
       Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
       com.onButtonPressed(event);
    }

    @SubscribeEvent
    public void onSelectMenu(SelectMenuInteractionEvent event) {
        Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
        com.onMenuInteraction(event);
    }

    



}
