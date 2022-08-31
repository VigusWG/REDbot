package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SelectionMenu;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.AssetSelectionMenu;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.FavoriteItems;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.enums.AssetTypes;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class FavouriteItems extends Command implements SlashCommand, SelectionMenu, Buttons{

    public FavouriteItems(){
        name = "favourites";
        description = "get the favourite assets of the specifified user";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Long userId = robloxUserArgument.fromOption(event.getOption("user").getAsString());
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }
        event.reply("Select the Item to view")
            .addActionRow(AssetSelectionMenu.getMenu(String.format("favourites_%d", userId)))
            .queue();
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption()));
        return F;
    }


    @Override
    public void onMenuInteraction(SelectMenuInteractionEvent event) {
        event.deferEdit().queue();
        AssetTypes asset = AssetTypes.valueOf(event.getValues().get(0));
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        Long userId = Long.parseLong(event.getComponentId().split("_", 2)[1]);

        try {
            User vigus = new UserBuilder(userId)
                    .setThumbnail(true)
                    .build();

            ArrayList<Asset> favorites = FavoriteItems.request(userId, asset, 1).get();
            
            b.setTitle(String.format("%s's favourite %s's", vigus.getName(), asset.toString()));
            b.setThumbnail(vigus.getThumbnail());

            StringBuilder desc = new StringBuilder();
            for (Asset ass : favorites) {
                desc.append(
                        String.format("%n[%s](%s)", ass.getName(), ass.getAbsoluteURL()));
            }
            if (desc.length() == 0){
                desc.append("The user has favourited none of the asset.");
            }
            b.setDescription(desc.toString());
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();

        } catch (Exception e) {
            b.setTitle("Sorry, Error");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
        }
        
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        Long userId = robloxUserArgument.fromOption(event.getComponentId().split("_", 2)[1]);
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }
        event.reply("Select the Item to view")
            .addActionRow(AssetSelectionMenu.getMenu(String.format("favourites_%d", userId)))
            .queue();
    }
    
}
