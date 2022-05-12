package me.vigus.red.robloxjava.connection.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Badge;
import me.vigus.red.robloxjava.enums.AssetTypes;
import me.vigus.red.robloxjava.exceptions.RequestError;

public class UserBadges {

    @JsonProperty("errors")
    public List<ErrorJson> errors = null;

    @JsonIgnore
    private ArrayList<Badge> badges;

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("displayDescription")
    private String displayDescription;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("iconImageId")
    private Long iconImageId;
    
    @JsonProperty("displayIconImageId")
    private Long displayIconImageId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][]'Z'", timezone = "UTC")
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date updated;

    @JsonIgnore
    private Integer pastDayAwardedCount;
    @JsonIgnore
    private Integer awardedCount;
    @JsonIgnore
    private Float winRatePercentage;

    @JsonProperty("statistics")
    private void unpackNestedStat(Map<String,Object> statistic) {
        this.pastDayAwardedCount = (Integer)statistic.get("pastDayAwardedCount");
        this.awardedCount = (Integer)statistic.get("awardedCount"); 
        this.winRatePercentage = ((Number)statistic.get("winRatePercentage")).floatValue();
    }

    @JsonIgnore
    private Long awarderId;
    @JsonIgnore
    private AssetTypes awarderType;

    @JsonProperty("awarder")
    private void unpackNestedAwarder(Map<String,Object> awarder) {
        this.awarderId = ((Number)awarder.get("id")).longValue();
        this.awarderType = AssetTypes.valueOf(awarder.get("type").toString().toUpperCase());
    }

    @JsonIgnore
    private static ArrayList<Badge> handleData(Iterator<JsonNode> data) throws JsonProcessingException, IllegalArgumentException{
        ArrayList<Badge> lis = new ArrayList<>();

        while (data.hasNext()) {
            UserBadges ew = CustomObjectMapper.getMapper().treeToValue(data.next(), UserBadges.class);
            Badge cool = new Badge();

            cool.setId(ew.getId());
            cool.setName(ew.getName());
            cool.setDescription(ew.getDescription());
            cool.setDisplayName(ew.getDisplayName());
            cool.setDisplayDescription(ew.getDisplayDescription());
            cool.setEnabled(ew.getEnabled());
            cool.setIconImageId(ew.getIconImageId());
            cool.setDisplayIconImageId(ew.getDisplayIconImageId());
            cool.setCreated(ew.getCreated());
            cool.setPastDayAwardedCount(ew.getPastDayAwardedCount());
            cool.setAwardedCount(ew.getAwardedCount());
            cool.setWinRatePercentage(ew.getWinRatePercentage());
            cool.setAwarderId(ew.getAwarderId());
            cool.setAwarderType(ew.getAwarderType());
            lis.add(cool);  
        }
        return lis;
    }

    @JsonIgnore
    public static CompletableFuture<ArrayList<Badge>> request(long userId){
        return request(userId, null);
    }

    @JsonIgnore
    public static CompletableFuture<ArrayList<Badge>> request(long userId, String cursor){
        String url = (cursor == null) ?  String.format("https://badges.roblox.com/v1/users/%s/badges?limit=100", userId) : String.format("https://badges.roblox.com/v1/users/%s/badges?limit=100&cursor=%s", userId, cursor); 
        return HTTPConnection.getInstance()
            .makeRequest(url)
            .thenApply(response -> {
                ArrayList<Badge> finalList = new ArrayList<>();
                try {
                    JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                    
                    if (jsonNode.get("errors") != null) {
                        throw new CompletionException(new RequestError(
                                CustomObjectMapper.getMapper().treeToValue(jsonNode.get("errors").get(0), ErrorJson.class)));
                    }

                    String nextCursor = jsonNode.get("nextPageCursor").asText();
                    finalList.addAll(handleData(jsonNode.get("data").elements()));

                    if (!nextCursor.equals("null")){
                        finalList.addAll(request(userId, nextCursor).get());
                    }
                    
                    return finalList;

                } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                    throw new CompletionException(e);
                }
            });
    }

    

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    public List<ErrorJson> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorJson> errors) {
        this.errors = errors;
    }

    public ArrayList<Badge> getBadges() {
        return this.badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayDescription() {
        return this.displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getIconImageId() {
        return this.iconImageId;
    }

    public void setIconImageId(Long iconImageId) {
        this.iconImageId = iconImageId;
    }

    public Long getDisplayIconImageId() {
        return this.displayIconImageId;
    }

    public void setDisplayIconImageId(Long displayIconImageId) {
        this.displayIconImageId = displayIconImageId;
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

    public Integer getPastDayAwardedCount() {
        return this.pastDayAwardedCount;
    }

    public void setPastDayAwardedCount(Integer pastDayAwardedCount) {
        this.pastDayAwardedCount = pastDayAwardedCount;
    }

    public Integer getAwardedCount() {
        return this.awardedCount;
    }

    public void setAwardedCount(Integer awardedCount) {
        this.awardedCount = awardedCount;
    }

    public Float getWinRatePercentage() {
        return this.winRatePercentage;
    }

    public void setWinRatePercentage(Float winRatePercentage) {
        this.winRatePercentage = winRatePercentage;
    }

    public Long getAwarderId() {
        return this.awarderId;
    }

    public void setAwarderId(Long awarderId) {
        this.awarderId = awarderId;
    }

    public AssetTypes getAwarderType() {
        return this.awarderType;
    }

    public void setAwarderType(AssetTypes awarderType) {
        this.awarderType = awarderType;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }



}
