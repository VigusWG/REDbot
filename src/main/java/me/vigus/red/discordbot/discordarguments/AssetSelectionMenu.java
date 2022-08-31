package me.vigus.red.discordbot.discordarguments;

import java.util.List;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.internal.interactions.component.SelectMenuImpl;

public class AssetSelectionMenu {

    public static SelectMenu getMenu(String customId){
        return SelectMenu.create(customId)
                .setPlaceholder("Choose which asset to view.")
                .setMinValues(1)
                .setMaxValues(1)
                .addOption("T-Shirts", "TSHIRT", Emoji.fromUnicode("U+1F455"))
                .addOption("Audio", "AUDIO", Emoji.fromUnicode("U+1F3B5"))
                .addOption("Hats", "HAT", Emoji.fromUnicode("U+1F3A9"))
                .addOption("Games/Places", "PLACE", Emoji.fromUnicode("U+1F3AE"))
                .addOption("Models", "MODEL", Emoji.fromUnicode("U+1F682"))
                .addOption("Shirts", "SHIRT", Emoji.fromUnicode("U+1F97C"))
                .addOption("Pants", "PANTS", Emoji.fromUnicode("U+1F456"))
                .addOption("Decals", "DECAL", Emoji.fromUnicode("U+1F5BC"))
                .addOption("Heads", "HEAD", Emoji.fromUnicode("U+1F636"))
                .addOption("Faces", "FACE", Emoji.fromUnicode("U+1F600"))
                //.addOption("Badges", "BADGE", Emoji.fromUnicode("U+1F396"))
                .addOption("Animations", "ANIMATION", Emoji.fromUnicode("U+1F6B6"))
                //.addOption("Gamepass's", "GAMEPASS", Emoji.fromUnicode("U+1F3AB"))
                .addOption("Shoes", "LEFTSHOEACCESSORY", Emoji.fromUnicode("U+1F45F"))
                .build();
    }
}
