package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class UserUsernames {
    private ArrayList<String> names;

    @JsonIgnore
    public static CompletableFuture<UserUsernames> request(long userId) throws InterruptedException {
        return HTTPConnection.getInstance()
                .makeRequest(String.format("https://users.roblox.com/v1/users/%s/username-history?limit=100", userId))
                .thenApply(response -> {
                    ArrayList<String> it = new ArrayList<>();
                    try {
                        JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                        if (jsonNode.get("errors") != null) {
                            throw new CompletionException(new RequestError(
                                    CustomObjectMapper.getMapper().treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                        } else {
                            Iterator<JsonNode> f = jsonNode.get("data").elements();
                            while (f.hasNext()) {
                                it.add(f.next().get("name").asText());
                            }
                            UserUsernames names = new UserUsernames();
                            names.setNames(it);
                            return names;
                        }

                    } catch (JsonProcessingException e) {
                        throw new CompletionException(e);
                    }

                });

    }

    public ArrayList<String> getNames() {
        return this.names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

}
