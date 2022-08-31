package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SelectionMenu;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.AssetSelectionMenu;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.FavoriteItems;
import me.vigus.red.robloxjava.connection.json.InventoryItemsJson;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.enums.AssetTypes;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class InventoryItems extends Command implements SlashCommand, SelectionMenu {

    public InventoryItems(){
        name = "inventory";
        description = "get the inventory assets of the specifified user";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Long userId = robloxUserArgument.fromOption(event.getOption("user").getAsString());
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            event.replyEmbeds(b.formattedBuild()).queue();
            return;
        }

        User user;
        try {
            user = new UserBuilder(userId)
                .setInventoryLocked(true)
                .build();
        } catch (InterruptedException | ExecutionException e) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Failed to see if inventory was open.");
            event.replyEmbeds(b.formattedBuild()).queue();
            return;
        }
        
        if (!user.getInvLocked()){
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Users Inventory is locked.");
            event.replyEmbeds(b.formattedBuild()).queue();
            return;
        }
        
        event.reply("Select the Item to view")
            .addActionRow(AssetSelectionMenu.getMenu(String.format("inventory_%d", userId)))
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

            ArrayList<Asset> favorites = InventoryItemsJson.request(userId, asset).get();
            
            b.setTitle(String.format("%s's %s's in their inventory.", vigus.getName(), asset.toString()));
            b.setThumbnail(vigus.getThumbnail());

            StringBuilder desc = new StringBuilder();
            for (Asset ass : favorites) {
                desc.append(
                        String.format("%n[%s](%s)", ass.getName(), ass.getAbsoluteURL()));
            }
            if (desc.length() == 0){
                desc.append("The user has none of the asset in their inventory.");
            }
            b.setDescription(desc.toString());
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();

        } catch (Exception e) {
            b.setTitle("Sorry, Error");
            b.setDescription(e.getCause().getMessage());
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
        }
        
    }
    
}
