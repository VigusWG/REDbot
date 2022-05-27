package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.enums.AssetTypes;
import net.dv8tion.jda.internal.requests.CompletedRestAction;

public class FavoriteItems {
    
    @JsonIgnore
    private ArrayList<Asset> games;

    @JsonIgnore
    public static CompletedRestAction<FavoriteItems> request(long userId, AssetTypes assetType){
        return request(userId, assetType, 1);
    }

    @JsonIgnore
    public static CompletedRestAction<FavoriteItems> request(long userId, AssetTypes assetType, int page){
        String url = String.format("https://www.roblox.com/users/favorites/list-json?assetTypeId=%s&itemsPerPage=100&pageNumber=%s&userId=%s", assetType.getValue(), page,userId);
        return null;
        
    }
}
