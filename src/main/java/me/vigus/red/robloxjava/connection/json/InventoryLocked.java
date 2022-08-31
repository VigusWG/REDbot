package me.vigus.red.robloxjava.connection.json;

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

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class InventoryLocked {
    
    @JsonProperty("canView")
    private Boolean canView;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("canView")
    public Boolean getCanView() {
        return canView;
    }
    
    @JsonProperty("canView")
    public void setCanView(Boolean canView) {
        this.canView = canView;
    }
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }
    
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @JsonIgnore
    public static CompletableFuture<Boolean> request(long userId) throws InterruptedException {
        return HTTPConnection.getInstance()
                .makeRequest(String.format("https://inventory.roblox.com/v1/users/%d/can-view-inventory", userId))
                .thenApply(response -> {
                    InventoryLocked it;
                    try {
                        it = CustomObjectMapper.getMapper().readValue(response.body(), InventoryLocked.class);
                    } catch (JsonProcessingException e) {
                        throw new CompletionException(e);
                    }

                    if (it.getErrors() == null) {
                        return it.getCanView();
                    } else {
                        throw new CompletionException(new RequestError(it.getErrors().get(0)));
                    }
                });

    }
    
}