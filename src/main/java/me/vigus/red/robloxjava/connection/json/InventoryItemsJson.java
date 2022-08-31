package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.Badge;
import me.vigus.red.robloxjava.enums.AssetTypes;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class InventoryItemsJson {

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    private ArrayList<Badge> badges;

    @JsonProperty("userAssetId")
    private Long userAssetId;

    @JsonProperty("assetId")
    private Long assetId;

    @JsonProperty("assetName")
    private String assetName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][]'Z'", timezone = "UTC")
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date updated;

    @JsonIgnore
    private Long ownerUserId;
    @JsonIgnore
    private String ownerName;

    @JsonProperty("owner")
    private void unpackNestedStat(Map<String,Object> owner) {
        //this.ownerUserId = (Long) owner.get("userId"); // Same errors as before
        this.ownerName = (String) owner.get("username"); 
        this.ownerUserId = 0L;
    }

    @JsonIgnore
    private static ArrayList<Asset> handleData(Iterator<JsonNode> data) throws JsonProcessingException, IllegalArgumentException{
        ArrayList<Asset> lis = new ArrayList<>();

        while (data.hasNext()) {
            InventoryItemsJson ew = CustomObjectMapper.getMapper().treeToValue(data.next(), InventoryItemsJson.class);
            Asset cool = new Asset();

            cool.setAssetId(ew.getAssetId());
            cool.setName(ew.getAssetName());
            cool.setCreated(ew.getCreated());
            cool.setUpdated(ew.getUpdated());
            cool.setCreatorId(ew.getOwnerUserId()); // THIS ISNT RIGHT, IK, ITS FOR INTERNAL USE ITS NOT LIKE THIS IS GONNA BE A REAL API WRAPPER
            cool.setCreatorName(ew.getOwnerName()); // I FUCKING HATE THIS PROJECT RN

            lis.add(cool);  
        }
        return lis;
    }

    @JsonIgnore
    public static CompletableFuture<ArrayList<Asset>> request(long userId, AssetTypes assetTypes) throws InterruptedException{
        return request(userId, assetTypes, null);
    }

    @JsonIgnore
    public static CompletableFuture<ArrayList<Asset>> request(long userId,  AssetTypes assetTypes, String cursor) throws InterruptedException{
        String url = (cursor == null) ?  String.format("https://inventory.roblox.com/v2/users/%s/inventory/%d?sortOrder=Asc&limit=100", userId, assetTypes.getValue()) : String.format("https://inventory.roblox.com/v2/users/%d/inventory/%d?sortOrder=Asc&limit=100&cursor=%s", userId, assetTypes.getValue(), cursor); 
        return HTTPConnection.getInstance()
            .makeRequest(url)
            .thenApply(response -> {
                ArrayList<Asset> finalList = new ArrayList<>();
                try {
                    JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                    
                    if (jsonNode.get("errors") != null) {
                        throw new CompletionException(new RequestError(
                                CustomObjectMapper.getMapper().treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                    }

                    String nextCursor = jsonNode.get("nextPageCursor").asText();
                    finalList.addAll(handleData(jsonNode.get("data").elements()));

                    if (!nextCursor.equals("null")){
                        finalList.addAll(request(userId, assetTypes, nextCursor).get());
                    }
                    
                    return finalList;

                } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                    throw new CompletionException(e);
                }
            });
    }

    

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }

    public ArrayList<Badge> getBadges() {
        return this.badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public Long getUserAssetId() {
        return this.userAssetId;
    }

    public void setUserAssetId(Long userAssetId) {
        this.userAssetId = userAssetId;
    }

    public Long getAssetId() {
        return this.assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getOwnerUserId() {
        return this.ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }


}
