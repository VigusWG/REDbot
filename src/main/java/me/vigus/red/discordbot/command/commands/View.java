package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.ThumbnailJson;
import me.vigus.red.robloxjava.connection.structs.ThumbnailRequest;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.enums.ThumnailBatch;
import me.vigus.red.robloxjava.enums.ThumnailFormat;
import me.vigus.red.robloxjava.enums.ThumnailSize;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class View extends Command implements SlashCommand {

    public View(){
        name = "view";
        description = "View the specified player.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        Long userId = robloxUserArgument.fromOption(event.getOption("user").getAsString());
        if (userId == null) {
            
            b.setTitle("Error. Invalid User.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

        User user;
        try {
            user = new UserBuilder(userId)
                    .setThumbnail(true)
                    .build();
            
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        ThumbnailRequest thumbnailRequest = new ThumbnailRequest(user.getId(), ThumnailBatch.AVATAR, ThumnailSize.S420, ThumnailFormat.PNG);
        ThumbnailJson js;
        try {
            js = ThumbnailJson.request(thumbnailRequest).get();
        } catch (InterruptedException | ExecutionException e) {
            b.setTitle("Error getting Image.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }
        b.setTitle(user.getName());
        b.setImage(js.getImageURL());
        b.setThumbnail(user.getThumbnail());
        event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption()));
        return F;
    }
    
}
