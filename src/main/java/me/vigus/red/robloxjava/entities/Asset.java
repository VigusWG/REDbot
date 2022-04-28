package me.vigus.red.robloxjava.entities;

import java.time.LocalDateTime;

import me.vigus.red.robloxjava.enums.AssetTypes;

public class Asset {
    private long assetId;
    private long productId;
    private String name;
    private String description;
    private AssetTypes assetType;
    private long creatorId;
    private String creatorName;
    private long iconImageAssetId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private long price;
    private long sales;
    private boolean newt;
    private boolean forSale;
    private boolean publicDomain;
    private boolean limited;
    private boolean limitedUnique;
    private long remaining;
    private long minimumMembershipLevel;
    
    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAssetType(AssetTypes assetType) {
        this.assetType = assetType;
    }
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public void setIconImageAssetId(long iconImageAssetId) {
        this.iconImageAssetId = iconImageAssetId;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public void setSales(long sales) {
        this.sales = sales;
    }
    public void setNewt(boolean newt) {
        this.newt = newt;
    }
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
    public void setPublicDomain(boolean publicDomain) {
        this.publicDomain = publicDomain;
    }
    public void setLimited(boolean limited) {
        this.limited = limited;
    }
    public void setLimitedUnique(boolean limitedUnique) {
        this.limitedUnique = limitedUnique;
    }
    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }
    public void setMinimumMembershipLevel(long minimumMembershipLevel) {
        this.minimumMembershipLevel = minimumMembershipLevel;
    }

    public long getAssetId() {
        return this.assetId;
    }

    public long getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public AssetTypes getAssetType() {
        return this.assetType;
    }

    public long getCreatorId() {
        return this.creatorId;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public long getIconImageAssetId() {
        return this.iconImageAssetId;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public long getPrice() {
        return this.price;
    }

    public long getSales() {
        return this.sales;
    }

    public boolean getNewt() {
        return this.newt;
    }

    public boolean isNewt() {
        return this.newt;
    }

    public boolean getForSale() {
        return this.forSale;
    }

    public boolean isForSale() {
        return this.forSale;
    }

    public boolean getPublicDomain() {
        return this.publicDomain;
    }

    public boolean isPublicDomain() {
        return this.publicDomain;
    }

    public boolean getLimited() {
        return this.limited;
    }

    public boolean isLimited() {
        return this.limited;
    }

    public boolean getLimitedUnique() {
        return this.limitedUnique;
    }

    public boolean isLimitedUnique() {
        return this.limitedUnique;
    }

    public long getRemaining() {
        return this.remaining;
    }

    public long getMinimumMembershipLevel() {
        return this.minimumMembershipLevel;
    }



}
