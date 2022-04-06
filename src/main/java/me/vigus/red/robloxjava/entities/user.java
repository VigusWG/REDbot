package me.vigus.red.robloxjava.entities;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String name;
    private Boolean isBanned;
    private String displayName;
    private LocalDateTime created;

    private List<UserJoinedGroup> groups;
    private List<Badge> badges;

    public User(int userId){
        name = "harry";       
    
    }

    public String getName() {
        return this.name;
    }

    public Boolean isBanned() {
        return this.isBanned;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public List<UserJoinedGroup> getGroups() {
        return this.groups;
    }

    public List<Badge> getBadges() {
        return this.badges;
    }
}
