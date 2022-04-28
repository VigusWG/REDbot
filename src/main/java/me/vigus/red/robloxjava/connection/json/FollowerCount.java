package me.vigus.red.robloxjava.connection.json;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class FollowerCount {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    @JsonProperty("counts")
    private int ammount;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    public static CompletableFuture<FollowerCount> request(long userId){
        return HTTPConnection.getInstance().makeRequest(String.format("https://friends.roblox.com/v1/users/%s/followers/count", userId))
            .thenApply(response -> {
                FollowerCount it;
                try {
                    it = objectMapper.readValue(response.body(), FollowerCount.class);
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


    public int getAmmount() {
        return this.ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }

}
