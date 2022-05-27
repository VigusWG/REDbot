package me.vigus.red.discordbot.command.usercommands;

import java.util.concurrent.ExecutionException;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.UserCommand;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.entities.User;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command.Type;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class RightyClicky extends Command implements UserCommand{

    public RightyClicky(){
        name = "rightyclicky";
        description = "the rightyclicky command";
    }

    @Override
    public void execute(UserContextInteractionEvent event) {
        event.deferReply().queue();
        try {
            User vigus = new UserBuilder(175135924)
                .setBasicUser(true)
                .setFriendCount(true)
                .setFollowingsCount(true)
                .setFollowerCount(true)
                .build();

            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle(vigus.getName());
            
            String desc = new StringBuilder()
                .append("Followers: ")
                .append(vigus.getFollowerCount())
                .append("\nFollowing: ")
                .append(vigus.getFollowingCount())
                .append("\nFriends: ")
                .append(vigus.getFriendCount())
                .append("\n\nDescription: ")
                .append(vigus.getDescritption())
                .toString();
            b.setDescription(desc);
            
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(Type.USER, name);
    }
    
}
