package me.vigus.red.robloxjava.entities;

import java.time.LocalDateTime;
import java.util.Date;

import me.vigus.red.robloxjava.enums.AssetTypes;

public class Asset {
    private Number assetId;
    private Long productId;
    private String name;
    private String description;
    private AssetTypes assetType;
    private Long creatorId;
    private String creatorName;
    private Long iconImageAssetId;
    private Date created;
    private Date updated;
    private Long price;
    private Long sales;
    private Boolean newt;
    private Boolean forSale;
    private Boolean publicDomain;
    private Boolean limited;
    private Boolean limitedUnique;
    private Long remaining;
    private Long minimumMembershipLevel;
    private String absoluteURL;


    public Number getAssetId() {
        return this.assetId;
    }

    public void setAssetId(Number assetId) {
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

    public AssetTypes getAssetType() {
        return this.assetType;
    }

    public void setAssetType(AssetTypes assetType) {
        this.assetType = assetType;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getIconImageAssetId() {
        return this.iconImageAssetId;
    }

    public void setIconImageAssetId(Long iconImageAssetId) {
        this.iconImageAssetId = iconImageAssetId;
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

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSales() {
        return this.sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Boolean isNewt() {
        return this.newt;
    }

    public Boolean getNewt() {
        return this.newt;
    }

    public void setNewt(Boolean newt) {
        this.newt = newt;
    }

    public Boolean isForSale() {
        return this.forSale;
    }

    public Boolean getForSale() {
        return this.forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    public Boolean isPublicDomain() {
        return this.publicDomain;
    }

    public Boolean getPublicDomain() {
        return this.publicDomain;
    }

    public void setPublicDomain(Boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    public Boolean isLimited() {
        return this.limited;
    }

    public Boolean getLimited() {
        return this.limited;
    }

    public void setLimited(Boolean limited) {
        this.limited = limited;
    }

    public Boolean isLimitedUnique() {
        return this.limitedUnique;
    }

    public Boolean getLimitedUnique() {
        return this.limitedUnique;
    }

    public void setLimitedUnique(Boolean limitedUnique) {
        this.limitedUnique = limitedUnique;
    }

    public Long getRemaining() {
        return this.remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }

    public Long getMinimumMembershipLevel() {
        return this.minimumMembershipLevel;
    }

    public void setMinimumMembershipLevel(Long minimumMembershipLevel) {
        this.minimumMembershipLevel = minimumMembershipLevel;
    }

    public String getAbsoluteURL() {
        return this.absoluteURL;
    }

    public void setAbsoluteURL(String absoluteURL) {
        this.absoluteURL = absoluteURL;
    }
    

}
