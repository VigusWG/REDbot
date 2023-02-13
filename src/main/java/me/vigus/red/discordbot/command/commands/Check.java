package me.vigus.red.discordbot.command.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.AssetInformationJson;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.Outfit;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Check extends Command implements SlashCommand, Buttons{

    private static SimpleDateFormat formatter = new SimpleDateFormat("EEEE 'the' dd 'of' MMMM yyyy'\n'");

    public Check(){
        name = "check";
        description = "the check command";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        interactionHookExecute(event.getHook(), event.getOption("user").getAsString());
    }

    private void interactionHookExecute(InteractionHook hook, String userOption){
        Long userId = robloxUserArgument.fromOption(userOption);
        if (userId == null){
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

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
                    .setAvatar(true)
                    .build();
            
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
                    .append(vigus.getBadges().size() == 1400 ? "1400+" : vigus.getBadges().size())
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

            StringBuilder avatar = new StringBuilder();
            if (vigus.getAvatar() != null){
                for (Asset ass : vigus.getAvatar().getAssets()){
                    String price = ass.getPrice() == null ? "" :  " currently costs " + ass.getPrice().toString() + " robux";
                    avatar.append(String.format("%n[%s](https://www.roblox.com/catalog/%d)%s", ass.getName(), ass.getAssetId(), price));
                }
            }
            if (avatar.isEmpty()){
                avatar.append("Nothing. (There's a high chance in reality it errored)");
            }
            b.addField("Currently Wearing", avatar.toString(), false);

            // long endTime = System.nanoTime();
            // b.addField("Time", String.format("Time 1: %s%nTime 2: %s%nTime 3: %s", (time2- startTime)/1000000, (time3- startTime)/ 1000000, (endTime-startTime)/1000000), false);            
            Collection<MessageEmbed> replys = b.formattedBuild();
            //hook.editOriginalEmbeds(replys.stream().findFirst().get()).queue();
            //replys.stream().skip(1).forEach(x -> hook.sendMessageEmbeds(x).queue());
            replys.stream().forEach(x -> hook.sendMessageEmbeds(x).queue());
        } catch (Exception e) {
            e.printStackTrace();
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Sorry Error.");
            b.setDescription(e.getCause().getMessage());
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
        }
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption()));
        return F;
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        event.deferReply().queue();
        interactionHookExecute(event.getHook(), event.getComponentId().split("_", 2)[1]);
    }
 
}
