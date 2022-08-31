package me.vigus.red.robloxjava.connection.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.enums.AssetTypes;
import me.vigus.red.robloxjava.exceptions.RequestError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetInformationJson {
    
    
    @JsonProperty("TargetId")
    private Long targetId;
    
    @JsonProperty("AssetId")
    private Long assetId;
    
    @JsonProperty("ProductId")
    private Long productId;
    
    @JsonProperty("Name")
    private String name;
    
    @JsonProperty("Description")
    private String description;
    
    @JsonProperty("ProductType")
    private String productType;
    
    private AssetTypes assetTypeId;
    private Object creator;
    
    @JsonProperty("IconImageAssetId")
    private Long iconImageAssetId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonProperty("Created")
    private String created;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonProperty("Updated")
    private String updated;
    
    @JsonProperty("PriceInRobux")
    private Integer priceInRobux;
    
    @JsonProperty("Sales")
    private Integer sales;
    
    @JsonProperty("IsNew")
    private Boolean isNew;
    
    @JsonProperty("IsForSale")
    private Boolean isForSale;
    
    @JsonProperty("IsPublicDomain")
    private Boolean isPublicDomain;
    
    @JsonProperty("IsLimited")
    private Boolean isLimited;
    
    @JsonProperty("IsLimitedUnique")
    private Boolean isLimitedUnique;
    
    @JsonProperty("MinimumMembershipLevel")
    private Integer minimumMembershipLevel;
    
    @JsonProperty("ContentRatingTypeId")
    private Integer contentRatingTypeId;
    
    
    @JsonProperty("errors")
    public List<ErrorJson> errors = null;
    
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
    
    
    @JsonIgnore
    public static CompletableFuture<AssetInformationJson> request(long assetId) throws InterruptedException {
        return HTTPConnection.getInstance()
        .makeRequest(String.format("https://economy.roblox.com/v2/assets/%d/details", 
        assetId))
        .thenApply(response -> {
            AssetInformationJson it;
            try {
                it = CustomObjectMapper.getMapper().readValue(response.body(), AssetInformationJson.class);
                
                if (it.getErrors() == null) {
                    return it;
                } else {
                    throw new CompletionException(new RequestError(it.getErrors().get(0)));
                }
                
            } catch (JsonProcessingException e) {
                throw new CompletionException(e);
            }
        });
    }
    
    @JsonProperty("Creator")
    private void unpackCreator(Map<String, Object> creator) {
        //Long cId = (Long)creator.get("CreatorTargetId");
        Long cId = 0L; //no fucking time
        if (creator.get("CreatorType").equals("User")){
            this.creator = new User(cId);
            ((User)this.creator).setName((String)creator.get("Name"));
        }else{
            this.creator = new Group(cId);
            ((Group) this.creator).setName((String) creator.get("Name"));
        }
    }

    

    @JsonProperty("AssetTypeId")
    private void handleAssetType(Integer assetTyoe) {
        if (assetTyoe == null) {
            return;
        }
        this.assetTypeId = AssetTypes.typeOfValue(assetTyoe);
    }


    public Long getTargetId() {
        return this.targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getAssetId() {
        return this.assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public AssetTypes getAssetTypeId() {
        return this.assetTypeId;
    }

    public void setAssetTypeId(AssetTypes assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public Object getCreator() {
        return this.creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Long getIconImageAssetId() {
        return this.iconImageAssetId;
    }

    public void setIconImageAssetId(Long iconImageAssetId) {
        this.iconImageAssetId = iconImageAssetId;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getPriceInRobux() {
        return this.priceInRobux;
    }

    public void setPriceInRobux(Integer priceInRobux) {
        this.priceInRobux = priceInRobux;
    }

    public Integer getSales() {
        return this.sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Boolean isIsNew() {
        return this.isNew;
    }

    public Boolean getIsNew() {
        return this.isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean isIsForSale() {
        return this.isForSale;
    }

    public Boolean getIsForSale() {
        return this.isForSale;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public Boolean isIsPublicDomain() {
        return this.isPublicDomain;
    }

    public Boolean getIsPublicDomain() {
        return this.isPublicDomain;
    }

    public void setIsPublicDomain(Boolean isPublicDomain) {
        this.isPublicDomain = isPublicDomain;
    }

    public Boolean isIsLimited() {
        return this.isLimited;
    }

    public Boolean getIsLimited() {
        return this.isLimited;
    }

    public void setIsLimited(Boolean isLimited) {
        this.isLimited = isLimited;
    }

    public Boolean isIsLimitedUnique() {
        return this.isLimitedUnique;
    }

    public Boolean getIsLimitedUnique() {
        return this.isLimitedUnique;
    }

    public void setIsLimitedUnique(Boolean isLimitedUnique) {
        this.isLimitedUnique = isLimitedUnique;
    }

    public Integer getMinimumMembershipLevel() {
        return this.minimumMembershipLevel;
    }

    public void setMinimumMembershipLevel(Integer minimumMembershipLevel) {
        this.minimumMembershipLevel = minimumMembershipLevel;
    }

    public Integer getContentRatingTypeId() {
        return this.contentRatingTypeId;
    }

    public void setContentRatingTypeId(Integer contentRatingTypeId) {
        this.contentRatingTypeId = contentRatingTypeId;
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

    
}