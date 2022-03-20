package me.vigus.RED.commands;

import me.vigus.RED.command.Command;
import me.vigus.RED.command.CustomEmbedBuilder;
import me.vigus.RED.command.TextFormat;
import me.vigus.RED.enums.textFormats;
import net.dv8tion.jda.api.EmbedBuilder;
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
        String f = "```ansi\n%sName = %svigus```";
        f = String.format(f, TextFormat.getCode(0, 31, 45), TextFormat.getCode(34));
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("pLEASE WORK");
        b.setDescription(f);
        b.addDefaults();
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
