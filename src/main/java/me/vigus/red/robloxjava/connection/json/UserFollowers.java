package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
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
import me.vigus.red.robloxjava.exceptions.RequestError;
public class UserFollowers {    
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    private Map<String, Object> additionalProperties;

    @JsonIgnore
    public static CompletableFuture<ArrayList<UserFollowers>> request(long userId) throws InterruptedException{
        return HTTPConnection.getInstance().makeRequest(String.format("https://friends.roblox.com/v1/users/%s/followers", userId))
            .thenApply(response -> {
                ArrayList<UserFollowers> it = new ArrayList<>();
                    try {
                        JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                        if (jsonNode.get("errors") != null) {
                            throw new CompletionException(new RequestError(
                                    CustomObjectMapper.getMapper().treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                        } else {
                            Iterator<JsonNode> f = jsonNode.get("data").elements();
                            while (f.hasNext()) {
                                it.add(CustomObjectMapper.getMapper().treeToValue(f.next(), UserFollowers.class));
                            }
                            return it;
                        }

                    } catch (JsonProcessingException e) {
                        throw new CompletionException(e);
                    }
            });

    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }


    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
