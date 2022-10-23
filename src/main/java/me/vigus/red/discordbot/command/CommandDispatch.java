package me.vigus.red.discordbot.command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.MessageCommand;
import me.vigus.red.discordbot.command.interfaces.SelectionMenu;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.command.interfaces.UserCommand;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

public class CommandDispatch {

    private static final String INVALIDCOMMAND = ":warning:This command does not exist.";
    private static ExecutorService executorService = Executors.newFixedThreadPool(20); //20 simul commands (i think)

    private static void submitToExcecutor(Runnable runner, String command){
        executorService.execute(runner);
        System.out.println("New Command: " + command);
    }

    @SubscribeEvent
    public void onSlashCommand(SlashCommandInteractionEvent event){
        Command com = Command.getCommand(event.getName());
        
        if (com == null){
            event.reply(INVALIDCOMMAND)
                .setEphemeral(true)
                .queue();
            return;
        }
        submitToExcecutor(() -> ((SlashCommand)com).execute(event), event.getCommandString());
    }

    @SubscribeEvent
    public void onUserCommand(UserContextInteractionEvent event){
        Command com = Command.getCommand(event.getName());
        if (com == null){
            event.reply(INVALIDCOMMAND)
                .setEphemeral(true)
                .queue();
            return;
        }
        submitToExcecutor(() -> ((UserCommand) com).execute(event), event.getCommandString());
    }

    @SubscribeEvent
    public void onMessageCommand(MessageContextInteractionEvent event){
        Command com = Command.getCommand(event.getName());

        if (com == null){
            event.reply(INVALIDCOMMAND)
                .setEphemeral(true)
                .queue();
            return;
        }
        submitToExcecutor(() -> ((MessageCommand) com).execute(event), event.getCommandString());
    }

    @SubscribeEvent
    public void onButtonClick(ButtonInteractionEvent event) {
        Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
        submitToExcecutor(() -> ((Buttons) com).onButtonPressed(event), event.getComponentId());
    }

    @SubscribeEvent
    public void onSelectMenu(SelectMenuInteractionEvent event) {
        Command com = Command.getCommand(event.getInteraction().getComponentId().split("_", 0)[0]);
        submitToExcecutor(() -> ((SelectionMenu) com).onMenuInteraction(event), event.getComponentId());
    }   
}
