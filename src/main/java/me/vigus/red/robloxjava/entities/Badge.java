package me.vigus.red.robloxjava.entities;

import java.time.LocalDateTime;

public class Badge {

    private Long id;
    private String name;
    private String description;
    private String displayName;
    private String displayDescription;
    private Boolean enabled;
    private Long iconImageId;
    private Long displayIconImageId;
    private LocalDateTime created;
    private LocalDateTime updated;

    private Long pastDayAwardedCount;
    private Long awardedCount;
    private int winRatePercentage;

    private long awarderId;
    private String awarderType;


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
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
    public void setPastDayAwardedCount(Long pastDayAwardedCount) {
        this.pastDayAwardedCount = pastDayAwardedCount;
    }
    public void setAwardedCount(Long awardedCount) {
        this.awardedCount = awardedCount;
    }
    public void setWinRatePercentage(int winRatePercentage) {
        this.winRatePercentage = winRatePercentage;
    }
    public void setAwarderId(long awarderId) {
        this.awarderId = awarderId;
    }
    public void setAwarderType(String awarderType) {
        this.awarderType = awarderType;
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

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public Long getPastDayAwardedCount() {
        return this.pastDayAwardedCount;
    }

    public Long getAwardedCount() {
        return this.awardedCount;
    }

    public int getWinRatePercentage() {
        return this.winRatePercentage;
    }

    public long getAwarderId() {
        return this.awarderId;
    }

    public String getAwarderType() {
        return this.awarderType;
    }

    
}
