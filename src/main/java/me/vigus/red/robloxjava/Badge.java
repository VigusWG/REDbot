package me.vigus.red.robloxjava;

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


    public Badge(Long id, String name, String description, String displayName, String displayDescription, Boolean enabled, Long iconImageId, Long displayIconImageId, LocalDateTime created, LocalDateTime updated, Long pastDayAwardedCount, Long awardedCount, int winRatePercentage, long awarderId, String awarderType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displayName = displayName;
        this.displayDescription = displayDescription;
        this.enabled = enabled;
        this.iconImageId = iconImageId;
        this.displayIconImageId = displayIconImageId;
        this.created = created;
        this.updated = updated;
        this.pastDayAwardedCount = pastDayAwardedCount;
        this.awardedCount = awardedCount;
        this.winRatePercentage = winRatePercentage;
        this.awarderId = awarderId;
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
