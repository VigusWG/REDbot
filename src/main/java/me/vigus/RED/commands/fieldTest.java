package me.vigus.RED.commands;

import me.vigus.RED.command.Command;
import me.vigus.RED.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class fieldTest extends Command {

    public fieldTest(){
        name = "fieldtest";
        description = "the fieldTest command";
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description);
    }

    @Override
    public CustomEmbedBuilder execute(SlashCommandInteractionEvent event) {
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("field test");
        for (int i =0; i < 30; i++){
            b.addField("sdfdfdfdffdf", Integer.toString(i), false);
        }
        b.setDescription(String.format("hello i am a description under 6000 chars!%nisSendable() returns %b", b.isValidLength()));
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
