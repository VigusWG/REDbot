package me.vigus.red.robloxjava.entities;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import me.vigus.red.discordbot.discordBot;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.structs.CustomObjectMapper;

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
    private Long id;
    private boolean isBanned;
    
    private boolean invLocked;

    private Date created;

    private ArrayList<String> previousNames;

    
    //counts
    private Integer friendCount;
    private Integer followerCount;
    private Integer followingCount;

    //friends
    private ArrayList<User> friends;
    private ArrayList<User> followers;       
    private ArrayList<User> followings;   

    private String thumbnail;
    private Integer ammountOfOutfits;
    private ArrayList<Outfit> outfits = new ArrayList<>();
    
    private ArrayList<Asset> favoriteGames = new ArrayList<>();
    private ArrayList<UserInGroup> groups = new ArrayList<>();

    private Avatar avatar;
        
    public ArrayList<String> getPreviousNames() {
        return this.previousNames;
    }


    public boolean isInvLocked() {
        return this.invLocked;
    }

    public boolean getInvLocked() {
        return this.invLocked;
    }

    public void setInvLocked(boolean invLocked) {
        this.invLocked = invLocked;
    }


    public Avatar getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setPreviousNames(ArrayList<String> previousNames) {
        this.previousNames = previousNames;
    }

    public ArrayList<UserInGroup> getGroups() {
        return this.groups;
    }

    public void setGroups(ArrayList<UserInGroup> groups) {
        this.groups = groups;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }
    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }
    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }
    public void setFollowings(ArrayList<User> followings) {
        this.followings = followings;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public void setAmmountOfOutfits(Integer ammountOfOutfits) {
        this.ammountOfOutfits = ammountOfOutfits;
    }
    public void setOutfits(ArrayList<Outfit> outfits) {
        this.outfits = outfits;
    }
    public void setFavoriteGames(ArrayList<Asset> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }
    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }
    private ArrayList<Badge> badges;

    public String getName() {
        return this.name;
    }

    public String getDescritption() {
        return this.descritption;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getIsBanned() {
        return this.isBanned;
    }

    public boolean isIsBanned() {
        return this.isBanned;
    }

    public Date getCreated() {
        return this.created;
    }

    public Integer getFriendCount() {
        return this.friendCount;
    }

    public Integer getFollowerCount() {
        return this.followerCount;
    }

    public Integer getFollowingCount() {
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

    public Integer getAmmountOfOutfits() {
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
    
    public String getProfileURL(){
        return String.format("https://www.roblox.com/users/%d/profile", this.id);
    }

    public User(long id) {
        this.id = id;
    }

    public User create(UserBuilder userBuilder) throws InterruptedException, ExecutionException{
        return userBuilder.build();
    }

    public static CompletableFuture<Long> idFromUsername(String username) throws InterruptedException{
        return HTTPConnection.getInstance().makeRequest(String.format("https://www.roblox.com/users/profile?username=%s", username))
            .thenApply(response -> {
                if (response.statusCode() != 302){
                    return null;
                }
                Optional<String> location = response.headers().firstValue("Location");
                if (!location.isPresent()){
                    return null;
                }
                return robloxUserArgument.fromURL(response.uri().getHost() + location.get());
            });
    }

    public static CompletableFuture<Long> idFromDiscord(Long disordId) throws InterruptedException {
        return HTTPConnection.getInstance()
                .makeRequest(String.format("https://verify.eryn.io/api/user/%s", disordId))
                .thenApply(response2 -> {
                    try {
                        JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response2.body());
                        if (jsonNode.get("status").textValue().equals("ok")) {
                            return jsonNode.get("robloxId").longValue();
                        }
                    } catch (JsonProcessingException e) {
                        //e.printStackTrace();
                    }
                    //Fucking shit code but idc im bored af
                    try {
                        if (discordBot.getBloxToken() == null){
                            return null;
                        }
                        
                        HttpRequest killMe = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create(String.format("https://v3.blox.link/developer/discord/%s",
                                            disordId)))
                            .header("api-key", discordBot.getBloxToken())
                            .build();

                        return HTTPConnection.getClient().sendAsync(killMe, HttpResponse.BodyHandlers
                                    .ofString())
                                .thenApply(response -> {
                                    if (response.statusCode() == 200){
                                        try {
                                            JsonNode jsonNode = CustomObjectMapper.getMapper().readTree(response.body());
                                            if (jsonNode.get("success").booleanValue()) {
                                                return Long.valueOf(jsonNode.get("user").get("robloxId").asText());
                                            }
                                        } catch (JsonProcessingException e) {
                                            e.printStackTrace();
                                            return null;
                                        }
                                    }
                                    return null;
                        }).get();
                    
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

            });
                // I HAVE NO FUCKING CLUE WHAT THIS IS, IM TOO TIRED FOR THIS SHIT
            }
}
