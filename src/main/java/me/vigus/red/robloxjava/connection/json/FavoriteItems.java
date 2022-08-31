package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.enums.AssetTypes;
import me.vigus.red.robloxjava.exceptions.RequestError;

@JsonIgnoreProperties
public class FavoriteItems {
    private Asset item;
    private String creatorName;
    private Long creatorId;
    private String thumbnailUrl;

    @JsonProperty("Creator")
    private void unpackCreator(Map<String, Object> creator) {
        //this.creatorId = (Long)creator.get("Id"); SAMWE FUCKING PROBLEM IDK HOW TO FIX IT
        this.creatorId = 0L;
        this.creatorName = (String) creator.get("Name");
    }

    @JsonProperty("Thumbnail")
    private void unpackThumbnail(Map<String, Object> thumnail) {
        this.thumbnailUrl = thumnail.get("Url").toString();
    }

    @JsonProperty("Item")
    private void unpackItem(Map<String, Object> item){
        Asset cool = new Asset();
        cool.setAssetId((Number)item.get("AssetId"));
        cool.setName((String)item.get("Name"));
        cool.setDescription((String)item.get("Description"));
        cool.setAssetType(AssetTypes.typeOfValue((Integer)item.get("AssetType")));
        cool.setAbsoluteURL(item.get("AbsoluteUrl").toString());
        this.item = cool;
    }

    @JsonIgnore
    public static CompletableFuture<ArrayList<Asset>> request(long userId, AssetTypes assetType, int page) throws InterruptedException{
        String url = String.format("https://www.roblox.com/users/favorites/list-json?assetTypeId=%s&itemsPerPage=100&pageNumber=%s&userId=%s", assetType.getValue(), page,userId);
        return HTTPConnection.getInstance()
            .makeRequest(url)
            .thenApply(respone -> {
                ArrayList<Asset> finalList = new ArrayList<>();
                try {
                    JsonNode jsonN = CustomObjectMapper.getMapper().readTree(respone.body());
                    if (jsonN.get("IsValid").equals(false)){
                        String errror = jsonN.get("Data").toString();
                        ErrorJson errorJson = new ErrorJson();
                        errorJson.setMessage(errror);
                        errorJson.setUserFacingMessage(errror);
                        throw new CompletionException(new RequestError(errorJson));
                    }

                    Integer totalItems = jsonN.get("Data").get("TotalItems").asInt();
                    Integer end = jsonN.get("Data").get("End").asInt();

                    finalList.addAll(handleData(jsonN.get("Data").get("Items").elements()));

                    if (end <= (totalItems-1)) {
                        finalList.addAll(request(userId, assetType, page+1).get());
                    }

                    return finalList;
                } catch (Exception e){
                        throw new CompletionException(new RequestError(e.getMessage()));
                }
            });

    }

    private static ArrayList<Asset> handleData(Iterator<JsonNode> data) {
        ArrayList<Asset> lis = new ArrayList<>();

        while (data.hasNext()) {
            FavoriteItems ew;
            try {
                ew = CustomObjectMapper.getMapper().treeToValue(data.next(), FavoriteItems.class);
            } catch (JsonProcessingException | IllegalArgumentException e) {
                e.printStackTrace();
                continue;
            }
            Asset cool = ew.getItem();
            cool.setCreatorId(ew.getCreatorId());
            cool.setCreatorName(ew.getCreatorName());
            lis.add(cool);
        }
        return lis;
    }



    public Asset getItem() {
        return this.item;
    }

    public void setItem(Asset item) {
        this.item = item;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    




}
