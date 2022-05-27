package me.vigus.red.discordbot.command.slashcommands;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Help extends Command implements SlashCommand, Buttons{

    public Help(){
        name = "help";
        description = "the help command";
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String f = "Shut it fatso";
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("Help");
        b.setDescription(f);
        b.addDefaults();
        b.addField("filed", "value", false);
        event.replyEmbeds(b.formattedBuild())
            .addActionRow(
                Button.danger("help_danger", "DANGER")
            )
            .queue();
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        System.out.println("Button pressed");
        event.reply("WELL DONE IDIOT").queue();
    }

    

}