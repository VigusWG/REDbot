package me.vigus.red.discordbot.command;

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

    @SubscribeEvent
    public void onSlashCommand(SlashCommandInteractionEvent event){
        Command com = Command.getCommand(event.getName());
        
        if (com == null){
            event.reply(INVALIDCOMMAND)
                .setEphemeral(true)
                .queue();
            return;
        }

        ((SlashCommand)com).execute(event);
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
        
        ((UserCommand) com).execute(event);
        
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
        ((MessageCommand) com).execute(event);
    }

    @SubscribeEvent
    public void onButtonClick(ButtonInteractionEvent event) {
        Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
       ((Buttons) com).onButtonPressed(event);
    }

    @SubscribeEvent
    public void onSelectMenu(SelectMenuInteractionEvent event) {
        Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
        ((SelectionMenu) com).onMenuInteraction(event);
    }

    



}
