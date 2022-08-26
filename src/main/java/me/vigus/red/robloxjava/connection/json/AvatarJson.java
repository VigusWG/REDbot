package me.vigus.red.robloxjava.connection.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Asset;
import me.vigus.red.robloxjava.entities.Avatar;
import me.vigus.red.robloxjava.enums.AssetTypes;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class AvatarJson {

    @JsonIgnore
    public static CompletableFuture<Avatar> request(long userId) throws InterruptedException{
        return HTTPConnection.getInstance().makeRequest(String.format("https://avatar.roblox.com/v1/users/%s/avatar", userId))
            .thenApply(response -> {

                Avatar av = new Avatar();

                JsonNode node;
                try {
                    node = CustomObjectMapper.getMapper().readTree(response.body());

                    if (node.get("errors") != null){
                        ErrorJson error = CustomObjectMapper.getMapper().treeToValue(node.get("errors").get(0), ErrorJson.class);
                        throw new CompletionException(new RequestError(error));
                    }

                    av.setPlayerAvatarType(node.get("playerAvatarType").asText());
                    av.setDefaultPants(node.get("defaultPantsApplied").asBoolean());
                    av.setDefaultShirt(node.get("defaultShirtApplied").asBoolean());

                    JsonNode bodyColors = node.get("bodyColors");
                    av.setHeadColorId(bodyColors.get("headColorId").asInt());
                    av.setTorsoColorId(bodyColors.get("torsoColorId").asInt());
                    av.setRightArmColorId(bodyColors.get("rightArmColorId").asInt());
                    av.setLeftArmColorId(bodyColors.get("leftArmColorId").asInt());
                    av.setLeftLegColorId(bodyColors.get("leftLegColorId").asInt());
                    av.setRightLegColorId(bodyColors.get("rightLegColorId").asInt());

                    JsonNode scales = node.get("scales");
                    av.setHeight(scales.get("height").floatValue());
                    av.setWidth(scales.get("width").floatValue());
                    av.setHead(scales.get("head").floatValue());
                    av.setDepth(scales.get("depth").floatValue());
                    av.setProportion(scales.get("proportion").floatValue());
                    av.setBodyType(scales.get("bodyType").floatValue());

                    Iterator<JsonNode> f = node.get("assets").elements();
                    while (f.hasNext()) {
                        JsonNode next = f.next();
                        Asset item = new Asset();
                        item.setAssetId(next.get("id").longValue());
                        item.setName(next.get("name").asText());
                        item.setAssetType(AssetTypes.valueOf(next.get("assetType").get("name").asText().toUpperCase()));                        
                        item.setAssetType(AssetTypes.valueOf("HAT"));                        
                        
                        av.getAssets().add(item);
                    }

                } catch (JsonProcessingException e) {
                    throw new CompletionException(e);
                }
                return av;
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
}
