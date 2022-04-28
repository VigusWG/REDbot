package me.vigus.red.discordbot.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;


public abstract class Command{
    private static HashMap<String, Command> registeredCommands = new HashMap<>();
    
    protected String name;
    protected Boolean ephemeral = true;

    public abstract CommandData make();

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
