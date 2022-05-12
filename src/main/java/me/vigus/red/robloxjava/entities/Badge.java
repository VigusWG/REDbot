package me.vigus.red.robloxjava.entities;

import java.util.Date;

import me.vigus.red.robloxjava.enums.AssetTypes;

public class Badge {

    private Long id;
    private String name;
    private String description;
    private String displayName;
    private String displayDescription;
    private Boolean enabled;
    private Long iconImageId;
    private Long displayIconImageId;
    private Date created;
    private Date updated;

    private Integer pastDayAwardedCount;
    private Integer awardedCount;
    private Float winRatePercentage;

    private long awarderId;
    private AssetTypes awarderType;


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

    public AssetTypes getAwarderType() {
        return this.awarderType;
    }

    public void setAwarderType(AssetTypes awarderType) {
        this.awarderType = awarderType;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public void setIconImageId(Long iconImageId) {
        this.iconImageId = iconImageId;
    }
    public void setDisplayIconImageId(Long displayIconImageId) {
        this.displayIconImageId = displayIconImageId;
    }

    public void setPastDayAwardedCount(Integer pastDayAwardedCount) {
        this.pastDayAwardedCount = pastDayAwardedCount;
    }
    public void setAwardedCount(Integer awardedCount) {
        this.awardedCount = awardedCount;
    }
    public void setWinRatePercentage(Float winRatePercentage) {
        this.winRatePercentage = winRatePercentage;
    }
    public void setAwarderId(long awarderId) {
        this.awarderId = awarderId;
    }



    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDisplayDescription() {
        return this.displayDescription;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Long getIconImageId() {
        return this.iconImageId;
    }

    public Long getDisplayIconImageId() {
        return this.displayIconImageId;
    }

    public Integer getPastDayAwardedCount() {
        return this.pastDayAwardedCount;
    }

    public Integer getAwardedCount() {
        return this.awardedCount;
    }

    public Float getWinRatePercentage() {
        return this.winRatePercentage;
    }

    public long getAwarderId() {
        return this.awarderId;
    }


    
}
