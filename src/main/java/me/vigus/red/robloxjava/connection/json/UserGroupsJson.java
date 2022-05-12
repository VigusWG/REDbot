package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class UserGroupsJson {
    private Integer id;
    private String name;
    private String description;
    private String ownerBuildersClubMembershipType;
    private Long ownerUserId;
    private String ownerUsername;
    private String ownerDisplayName;
    private String shout;
    private Long shoutUserId;
    private String shoutUsername;
    private String shoutDisplayName;
    private Integer memberCount;
    private Boolean isBuildersClubOnly;
    private Boolean publicEntryAllowed;
    private Integer roleId;
    private String roleName;
    private Integer roleRank;
    private Boolean locked;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updated;


    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @SuppressWarnings("unchecked")
    @JsonProperty("group")
    private void unpackNestedGroup(Map<String,Object> group) {
        this.id = (Integer)group.get("id");
        this.name= (String)group.get("name");
        this.description= (String)group.get("description");
        
        this.memberCount = (Integer)group.get("memberCount");
        this.isBuildersClubOnly = (Boolean)group.get("isBuildersClubOnly");
        this.publicEntryAllowed = (Boolean)group.get("publicEntryAllowed");
        this.locked = (Boolean) group.get("isLocked");
        this.created = (Date) group.get("created"); 
        this.updated = (Date) group.get("updated"); 


        Map<String,Object> owner = (Map<String,Object>)group.get("owner");
        this.ownerUsername = (String)owner.get("username");
        this.ownerDisplayName = (String)owner.get("displayName");
        this.ownerBuildersClubMembershipType = (String)owner.get("buildersClubMembershipType"); 
        this.ownerUserId = ((Number)owner.get("userId")).longValue();

        //Shouts dont work (no its not the code its the api even tho the code wouldnt work) tl;dr: fuck off
        if (group.get("shout") == null){
            this.shoutUsername = null;
            this.shoutDisplayName = null;
            this.shoutUserId = null;
        }else{
            Map<String,Object> shout2 = (Map<String,Object>)group.get("shout");
            this.shoutUsername = (String)shout2.get("username");
            this.shoutDisplayName = (String)shout2.get("displayName");
            this.shoutUserId = (Long)shout2.get("userId");
        }
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
                        JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                        if (jsonNode.get("errors") != null) {
                            throw new CompletionException(new RequestError(
                                    CustomObjectMapper.getMapper().treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                        } else {
                            Iterator<JsonNode> f = jsonNode.get("data").elements();
                            while (f.hasNext()) {
                                it.add(CustomObjectMapper.getMapper().treeToValue(f.next(), UserGroupsJson.class));
                            }
                            return it;
                        }

                    } catch (JsonProcessingException e) {
                        throw new CompletionException(e);
                    }

                });

    }


    public Long getShoutUserId() {
        return this.shoutUserId;
    }

    public void setShoutUserId(long shoutUserId) {
        this.shoutUserId = shoutUserId;
    }

    public String getShoutUsername() {
        return this.shoutUsername;
    }

    public void setShoutUsername(String shoutUsername) {
        this.shoutUsername = shoutUsername;
    }

    public String getShoutDisplayName() {
        return this.shoutDisplayName;
    }

    public void setShoutDisplayName(String shoutDisplayName) {
        this.shoutDisplayName = shoutDisplayName;
    }

    public Boolean isLocked() {
        return this.locked;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
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

    public Long getOwnerUserId() {
        return this.ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
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
