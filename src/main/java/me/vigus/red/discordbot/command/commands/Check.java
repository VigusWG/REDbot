package me.vigus.red.discordbot.command.commands;

import java.text.SimpleDateFormat;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.entities.Outfit;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Check extends Command implements SlashCommand{

    private static SimpleDateFormat formatter = new SimpleDateFormat("EEEE 'the' dd 'of' MMMM yyyy'\n'");

    public Check(){
        name = "check";
        description = "the check command";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        long startTime = System.nanoTime();
        
        Long userId = robloxUserArgument.fromOption(event.getOption("user").getAsString());
        if (userId == null){
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }
        long time2 = System.nanoTime();

        try {
            User vigus = new UserBuilder(userId)
                    .setBasicUser(true)
                    .setFriendCount(true)
                    .setFollowingsCount(true)
                    .setFollowerCount(true)
                    .setBadges(true)
                    .setFavoriteGames(true)
                    .setGroups(true)
                    .setOutfits(true)
                    .setThumbnail(true)
                    .build();
            long time3 = System.nanoTime();
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle(vigus.getName());
            b.setThumbnail(vigus.getThumbnail());

            String desc = new StringBuilder()
                    .append("\nJoinDate: ")
                    .append(formatter.format(vigus.getCreated()))
                    .append("Followers: ")
                    .append(vigus.getFollowerCount())
                    .append("\nFollowing: ")
                    .append(vigus.getFollowingCount())
                    .append("\nFriends: ")
                    .append(vigus.getFriendCount())
                    .append("\nNumber of badges: ")
                    .append(vigus.getFavoriteGames().size())
                    .append("\nNumber of favourite games: ")
                    .append(vigus.getFavoriteGames().size())
                    .append("\n\nDescription: ")
                    .append(vigus.getDescritption())
                    .toString();
            b.setDescription(desc);

            StringBuilder groups = new StringBuilder();
            for (UserInGroup userInGroup : vigus.getGroups()){
                groups.append(String.format("[%s](https://www.roblox.com/groups/%s)    -    %s%n", userInGroup.getName(), userInGroup.getId(), userInGroup.getRoleName()));
            }

            if (groups.isEmpty()){
                groups.append("None.");
            }else{
                groups.setLength(groups.length() - 1); //to remove the last new line
            }

            b.addField("Groups", groups.toString(), false);


            StringBuilder outfits = new StringBuilder();
            for (Outfit outfit : vigus.getOutfits()){
                outfits.append(String.format("%s%n", outfit.getName()));
            }

            if (outfits.isEmpty()){
                outfits.append("None.");
            }else{
                outfits.setLength(outfits.length() - 1); //to remove the last new line
            }

            b.addField("Outfits", outfits.toString(), false);

            long endTime = System.nanoTime();
            b.addField("Time", String.format("Time 1: %s%nTime 2: %s%nTime 3: %s", (time2- startTime)/1000000, (time3- startTime)/ 1000000, (endTime-startTime)/1000000), false);            
            

            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption());
    }
 
}
