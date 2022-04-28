package me.vigus.red.robloxjava.entities;

import java.util.Date;

public class Group {
    private long id;
    private String name;
    private String description;
    private User owner;
    private int memberCount;
    
    private Date created;
    private Date updated;

    private Boolean isLocked;
    private Boolean publicEntryAllowed;
    private Boolean isBuildersClubOnly;

    private String shoutBody;
    private User shoutPoster;


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

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getMemberCount() {
        return this.memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
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

    public Boolean isIsLocked() {
        return this.isLocked;
    }

    public Boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
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

    public Boolean isIsBuildersClubOnly() {
        return this.isBuildersClubOnly;
    }

    public Boolean getIsBuildersClubOnly() {
        return this.isBuildersClubOnly;
    }

    public void setIsBuildersClubOnly(Boolean isBuildersClubOnly) {
        this.isBuildersClubOnly = isBuildersClubOnly;
    }

    public String getShoutBody() {
        return this.shoutBody;
    }

    public void setShoutBody(String shoutBody) {
        this.shoutBody = shoutBody;
    }

    public User getShoutPoster() {
        return this.shoutPoster;
    }

    public void setShoutPoster(User shoutPoster) {
        this.shoutPoster = shoutPoster;
    }

    public Group(long id){
        this.id = id;
    }

}
