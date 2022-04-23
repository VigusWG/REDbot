package me.vigus.red.discordbot.command;

import java.util.concurrent.CompletableFuture;

import me.vigus.red.discordbot.command.messagecommand.MessageCommand;
import me.vigus.red.discordbot.command.slashcommands.SlashCommand;
import me.vigus.red.discordbot.command.usercommands.UserCommand;
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

        event.deferReply(com.ephemeral).queue();
        CustomEmbedBuilder x = ((SlashCommand) com).execute(event);
        event.getHook().editOriginalEmbeds(x.formattedBuild()).queue();
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
        
        event.deferReply(com.ephemeral).queue();
        CompletableFuture.supplyAsync(() -> ((UserCommand) com).execute(event))
            .thenAccept(embed -> event.getHook().editOriginalEmbeds(embed.formattedBuild()).queue())
            .thenAccept(done -> System.out.println(String.format("Done with all futures %s", done)))
            .thenRun(() -> System.out.println("Running all futures in parallel took time"));
        //CustomEmbedBuilder x = ((UserCommand) com).execute(event);
        //event.getHook().editOriginalEmbeds(x.formattedBuild()).queue();
    }

    @SubscribeEvent
    public void onMessageCommand(MessageContextInteractionEvent event){
        Command com = Command.getCommand(event.getName());
        event.deferReply(com.ephemeral).queue();
        CustomEmbedBuilder x = ((MessageCommand) com).execute(event);
        event.getHook().editOriginalEmbeds(x.formattedBuild()).queue();
    }

    @SubscribeEvent
    public void onButtonClick(ButtonInteractionEvent event) {
       Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
       ((SlashCommand) com).onButtonPressed(event);
    }

    @SubscribeEvent
    public void onSelectMenu(SelectMenuInteractionEvent event) {
        Command com = Command.getCommand(event.getComponentId().split("_", 0)[0]);
        ((SlashCommand) com).onMenuInteraction(event);
    }

    



}
