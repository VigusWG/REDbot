package me.vigus.red.robloxjava.connection.json;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class FollowingCount {    
    @JsonProperty("count")
    private int ammount;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    public static CompletableFuture<FollowingCount> request(long userId){
        return HTTPConnection.getInstance().makeRequest(String.format("https://friends.roblox.com/v1/users/%s/followings/count", userId))
            .thenApply(response -> {
                FollowingCount it;
                try {
                    it = CustomObjectMapper.getMapper().readValue(response.body(), FollowingCount.class);
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
