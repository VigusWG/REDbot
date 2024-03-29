package me.vigus.red.robloxjava.connection.json;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.exceptions.RequestError;

//@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonIgnore
    public static CompletableFuture<UserJson> request(long userId) throws InterruptedException{
        return HTTPConnection.getInstance().makeRequest(String.format("https://users.roblox.com/v1/users/%s", userId))
            .thenApply(response -> {
                UserJson it;
                try {
                    it = CustomObjectMapper.getMapper().readValue(response.body(), UserJson.class);
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
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

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
    public Long getId() {
    return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
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

    @JsonProperty("errors")
    public List<ErrorJson> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setError(List<ErrorJson> jsonErrors) {
        this.errors = jsonErrors;
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
