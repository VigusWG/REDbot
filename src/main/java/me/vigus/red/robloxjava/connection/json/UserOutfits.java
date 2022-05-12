package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.HashMap;
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
import me.vigus.red.robloxjava.entities.Outfit;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class UserOutfits {
    private ArrayList<Outfit> outfits = new ArrayList<>();
    
    @JsonProperty("total")
    private Integer total;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonProperty("data")
    private void unpackNestedData(List<JsonNode> data) {
        for (JsonNode node: data){
            Outfit out = new Outfit();
            out.setEditable(node.get("isEditable").booleanValue());
            out.setName(node.get("name").asText());
            out.setId(node.get("id").asLong());
            this.outfits.add(out);
        }
    }

    @JsonIgnore
    public static CompletableFuture<UserOutfits> request(long userId){
        return HTTPConnection.getInstance().makeRequest(String.format("https://avatar.roblox.com/v1/users/%s/outfits", userId))
            .thenApply(response -> {
                UserOutfits it;
                try {
                    it = CustomObjectMapper.getMapper().readValue(response.body(), UserOutfits.class);
                } catch (JsonProcessingException e) {
                    throw new CompletionException(e);
                }

                if (it.getErrors() == null){
                    return it;
                }else {
                    throw new CompletionException(new RequestError(it.getErrors().get(0)));
                }
                
            });
    }

    public ArrayList<Outfit> getOutfits() {
        return this.outfits;
    }

    public void setOutfits(ArrayList<Outfit> outfits) {
        this.outfits = outfits;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
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
