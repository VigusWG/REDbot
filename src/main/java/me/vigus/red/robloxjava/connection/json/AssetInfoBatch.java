package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.enums.AssetTypes;

public class AssetInfoBatch {
    @JsonIgnore
    public static CompletableFuture<ArrayList<Asset>> request(ArrayList<Long> assetIds) throws InterruptedException {
            
        StringBuilder stringBuilder = new StringBuilder("{\"items\":[ ");
        for (Long asset : assetIds){
            stringBuilder.append(String.format("{\"itemType\":\"Asset\",\"id\":%d},", asset));
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]}");

        return HTTPConnection.getInstance()
                .postRequest("https://catalog.roblox.com/v1/catalog/items/details",stringBuilder.toString())
                .thenApply(response -> {
                    ArrayList<Asset> finalList = new ArrayList<>();
                    try {
                        JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                        Iterator<JsonNode> d = jsonNode.get("data").elements();
                        while (d.hasNext()){
                            JsonNode item = d.next();
                            Asset cool = new Asset();
                            cool.setAssetId(item.get("id").longValue());
                            cool.setAssetType(AssetTypes.typeOfValue(item.get("assetType").intValue()));
                            cool.setName(item.get("name").asText());
                            cool.setDescription(item.get("description").asText());
                            cool.setProductId(item.get("productId").asLong());
                            cool.setCreatorId(item.get("creatorTargetId").longValue());
                            cool.setCreatorName(item.get("creatorName").asText());
                            if (item.has("price")){
                                cool.setPrice(item.get("price").asLong());
                            }
                            finalList.add(cool);
                        }
                        
                        return finalList;

                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                });
    }
}
