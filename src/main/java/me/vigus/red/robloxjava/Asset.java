package me.vigus.red.robloxjava;

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
    

    public Asset(long assetId, long productId, String name, String description, AssetTypes assetType, long creatorId, String creatorName, long iconImageAssetId, LocalDateTime created, LocalDateTime updated, long price, long sales, boolean newt, boolean forSale, boolean publicDomain, boolean limited, boolean limitedUnique, long remaining, long minimumMembershipLevel) {
        this.assetId = assetId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.assetType = assetType;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.iconImageAssetId = iconImageAssetId;
        this.created = created;
        this.updated = updated;
        this.price = price;
        this.sales = sales;
        this.newt = newt;
        this.forSale = forSale;
        this.publicDomain = publicDomain;
        this.limited = limited;
        this.limitedUnique = limitedUnique;
        this.remaining = remaining;
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
