package me.vigus.red.robloxjava;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {

    /*
    (things)
    private boolean groups = false;
    private boolean friends = false;
    private boolean friendCount = false;
    private boolean followers = false;
    private boolean followerCount = false;
    private boolean followings = false;
    private boolean followingsCount = false;
    private boolean basicUser = true;
    private boolean badges = false;
    private boolean avatar = false;
    private boolean thumbnail = false;
    private boolean outfits = false;
    private boolean favoriteGames = false;  
    */
    


    //basic user stuff
    private String name;
    private String descritption;
    private String displayName;
    private long id;
    private boolean isBanned;
    private LocalDateTime created;
    
    //counts
    private long friendCount;
    private long followerCount;
    private long followingCount;

    //friends
    private ArrayList<User> friends;
    private ArrayList<User> followers;       
    private ArrayList<User> followings;   

    public String getName() {
        return this.name;
    }

    public String getDescritption() {
        return this.descritption;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public long getId() {
        return this.id;
    }

    public boolean getIsBanned() {
        return this.isBanned;
    }

    public boolean isIsBanned() {
        return this.isBanned;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public long getFriendCount() {
        return this.friendCount;
    }

    public long getFollowerCount() {
        return this.followerCount;
    }

    public long getFollowingCount() {
        return this.followingCount;
    }

    public ArrayList<User> getFriends() {
        return this.friends;
    }

    public ArrayList<User> getFollowers() {
        return this.followers;
    }

    public ArrayList<User> getFollowings() {
        return this.followings;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public long getAmmountOfOutfits() {
        return this.ammountOfOutfits;
    }

    public ArrayList<Outfit> getOutfits() {
        return this.outfits;
    }

    public ArrayList<Asset> getFavoriteGames() {
        return this.favoriteGames;
    }

    public ArrayList<Badge> getBadges() {
        return this.badges;
    }
    
    private String thumbnail;
    private long ammountOfOutfits;
    private ArrayList<Outfit> outfits;
    
    private ArrayList<Asset> favoriteGames;
    private ArrayList<Badge> badges;



    public User(String name, String descritption, String displayName, long id, boolean isBanned, LocalDateTime created, long friendCount, long followerCount, long followingCount, ArrayList<User> friends, ArrayList<User> followers, ArrayList<User> followings, String thumbnail, long ammountOfOutfits, ArrayList<Outfit> outfits, ArrayList<Asset> favoriteGames, ArrayList<Badge> badges) {
        this.name = name;
        this.descritption = descritption;
        this.displayName = displayName;
        this.id = id;
        this.isBanned = isBanned;
        this.created = created;
        this.friendCount = friendCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.friends = friends;
        this.followers = followers;
        this.followings = followings;
        this.thumbnail = thumbnail;
        this.ammountOfOutfits = ammountOfOutfits;
        this.outfits = outfits;
        this.favoriteGames = favoriteGames;
        this.badges = badges;
    }

    public User getUser(UserBuilder userBuilder){
        return userBuilder.build();
    }

}
