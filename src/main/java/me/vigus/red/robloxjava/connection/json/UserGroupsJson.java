package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class UserGroupsJson {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private Integer id;
    private String name;
    private String description;
    private String ownerBuildersClubMembershipType;
    private Integer ownerUserId;
    private String ownerUsername;
    private String ownerDisplayName;
    private Object shout;
    private Integer memberCount;
    private Boolean isBuildersClubOnly;
    private Boolean publicEntryAllowed;
    private Integer roleId;
    private String roleName;
    private Integer roleRank;

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @SuppressWarnings("unchecked")
    @JsonProperty("group")
    private void unpackNestedGroup(Map<String,Object> group) {
        this.id = (Integer)group.get("id");
        this.name= (String)group.get("name");
        this.description= (String)group.get("description");
        this.shout= (String)group.get("shout");
        this.memberCount = (Integer)group.get("memberCount");
        this.isBuildersClubOnly = (Boolean)group.get("isBuildersClubOnly");
        this.publicEntryAllowed = (Boolean)group.get("publicEntryAllowed");

        Map<String,Object> owner = (Map<String,Object>)group.get("owner");
        this.ownerUsername = (String)owner.get("username");
        this.ownerDisplayName = (String)owner.get("displayName");
        this.ownerBuildersClubMembershipType = (String)owner.get("buildersClubMembershipType"); 
        this.ownerUserId = (Integer)owner.get("userId");
    }

    @JsonProperty("role")
    private void unpackNestedRole(Map<String,Object> role) {
        this.roleRank = (Integer)role.get("roleRank");
        this.roleName = (String)role.get("name"); 
        this.roleId = (Integer)role.get("id");
    }


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    public static CompletableFuture<ArrayList<UserGroupsJson>> request(long userId) {
        return HTTPConnection.getInstance()
                .makeRequest(String.format("https://groups.roblox.com/v1/users/%s/groups/roles", userId))
                .thenApply(response -> {
                    ArrayList<UserGroupsJson> it = new ArrayList<>();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(response.body());
                        if (jsonNode.get("errors") != null) {
                            throw new CompletionException(new RequestError(
                                    objectMapper.treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                        } else {
                            Iterator<JsonNode> f = jsonNode.get("data").elements();
                            while (f.hasNext()) {
                                it.add(objectMapper.treeToValue(f.next(), UserGroupsJson.class));
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerBuildersClubMembershipType() {
        return this.ownerBuildersClubMembershipType;
    }

    public void setOwnerBuildersClubMembershipType(String ownerBuildersClubMembershipType) {
        this.ownerBuildersClubMembershipType = ownerBuildersClubMembershipType;
    }

    public Integer getOwnerUserId() {
        return this.ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerUsername() {
        return this.ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerDisplayName() {
        return this.ownerDisplayName;
    }

    public void setOwnerDisplayName(String ownerDisplayName) {
        this.ownerDisplayName = ownerDisplayName;
    }

    public Object getShout() {
        return this.shout;
    }

    public void setShout(Object shout) {
        this.shout = shout;
    }

    public Integer getMemberCount() {
        return this.memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Boolean isIsBuildersClubOnly() {
        return this.isBuildersClubOnly;
    }

    public Boolean getIsBuildersClubOnly() {
        return this.isBuildersClubOnly;
    }

    public void setIsBuildersClubOnly(Boolean isBuildersClubOnly) {
        this.isBuildersClubOnly = isBuildersClubOnly;
    }

    public Boolean isPublicEntryAllowed() {
        return this.publicEntryAllowed;
    }

    public Boolean getPublicEntryAllowed() {
        return this.publicEntryAllowed;
    }

    public void setPublicEntryAllowed(Boolean publicEntryAllowed) {
        this.publicEntryAllowed = publicEntryAllowed;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleRank() {
        return this.roleRank;
    }

    public void setRoleRank(Integer roleRank) {
        this.roleRank = roleRank;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
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
