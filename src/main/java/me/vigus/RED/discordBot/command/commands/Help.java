package me.vigus.red.discordbot.command.commands;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Help extends Command{

    public Help(){
        name = "help";
        description = "the help command";
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description)
            .addOption(OptionType.BOOLEAN, "imdumb", "are you?");
    }

    @Override
    public CustomEmbedBuilder execute(SlashCommandInteractionEvent event) {
        String f = "Shut it fatso";
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("pLEASE WORK");
        b.setDescription(f);
        b.addDefaults();
        b.addField("filed", "value", false);
        return b;
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMenuInteraction(SelectMenuInteractionEvent event) {
        // TODO Auto-generated method stub
        
    }
}
