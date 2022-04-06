package me.vigus.red.discordbot.command.usercommands;

import org.w3c.dom.Text;

import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command.Type;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class TestUser extends UserCommand{

    public TestUser(){
        name = "TestUser";
    }

    @Override
    public CustomEmbedBuilder execute(UserContextInteractionEvent event) {
        String f = "Shut it fatso";
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("pLEASE WORK");
        b.setDescription(f);
        b.addDefaults();
        b.addField("filed", "value", false);
        return b;
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(Type.USER, name);
    }
    
}
