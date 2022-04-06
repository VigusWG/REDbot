package me.vigus.red.robloxjava.connection.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class User {

    @JsonProperty("description")
    private String description;
    @JsonProperty("created")
    private String created;
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
    public String getCreated() {
    return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
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
