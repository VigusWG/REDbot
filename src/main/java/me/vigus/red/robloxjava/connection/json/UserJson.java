package me.vigus.red.robloxjava.connection.json;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"description",
"created",
"isBanned",
"externalAppDisplayName",
"id",
"name",
"displayName"
})
public class UserJson {

    /*
    @JsonIgnore
    public static URI makeUri(int userId){
        return URI.create(String.format("https://users.roblox.com/v1/users/%s", userId));
    }
    */

    private static ObjectMapper objectMapper = new ObjectMapper();

    @JsonIgnore
    public static CompletableFuture<UserJson> request(long userId){
        return HTTPConnection.getInstance().makeRequest(String.format("https://users.roblox.com/v1/users/%s", userId))
            .thenApply(response -> {
                try {
                    return objectMapper.readValue(response.body(), UserJson.class);
                } catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            });

    }             

    @JsonProperty("description")
    private String description;
    @JsonProperty("created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date created;
    @JsonProperty("isBanned")
    private Boolean isBanned;
    @JsonProperty("externalAppDisplayName")
    private Object externalAppDisplayName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("displayName")
    private String displayName;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("description")
    public String getDescription() {
    return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
    this.description = description;
    }

    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(Date created) {
    this.created = created;
    }

    @JsonProperty("isBanned")
    public Boolean getIsBanned() {
    return isBanned;
    }

    @JsonProperty("isBanned")
    public void setIsBanned(Boolean isBanned) {
    this.isBanned = isBanned;
    }

    @JsonProperty("externalAppDisplayName")
    public Object getExternalAppDisplayName() {
    return externalAppDisplayName;
    }

    @JsonProperty("externalAppDisplayName")
    public void setExternalAppDisplayName(Object externalAppDisplayName) {
    this.externalAppDisplayName = externalAppDisplayName;
    }

    @JsonProperty("id")
    public Integer getId() {
    return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
    this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
    return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
    this.displayName = displayName;
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
