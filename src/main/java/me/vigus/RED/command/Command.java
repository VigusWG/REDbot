package me.vigus.RED.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;


public abstract class Command{
    private static HashMap<String, Command> registeredCommands = new HashMap<>();
    
    protected String name;
    protected String description;
    protected Boolean ephemeral = true;

    public abstract CommandData make();
    public abstract CustomEmbedBuilder execute(SlashCommandInteractionEvent event);

    public abstract void onButtonPressed(ButtonInteractionEvent event);
    public abstract void onMenuInteraction(SelectMenuInteractionEvent event);


    public static void registerCommand(Command command){
        registeredCommands.put(command.name, command);
    }

    public static Command getCommand(String name) {
        return registeredCommands.get(name);
    }

    public static Collection<CommandData> getAllCommands() {
        Collection<CommandData> collection = new ArrayList<>();
        for (Command x: registeredCommands.values()){
            collection.add(x.make());
        }
        return collection;
    }
}
