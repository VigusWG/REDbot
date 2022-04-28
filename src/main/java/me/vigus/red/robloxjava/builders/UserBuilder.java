package me.vigus.red.robloxjava.builders;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import me.vigus.red.robloxjava.connection.json.FollowerCount;
import me.vigus.red.robloxjava.connection.json.FollowingCount;
import me.vigus.red.robloxjava.connection.json.FriendCount;
import me.vigus.red.robloxjava.connection.json.UserGroupsJson;
import me.vigus.red.robloxjava.connection.json.UserJson;
import me.vigus.red.robloxjava.connection.json.UserUsernames;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;

public class UserBuilder {

    private long userId;

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
    private boolean previousNames = false;


    public UserBuilder(long userId){
        this.userId = userId;
    }


    public boolean isPreviousNames() {
        return this.previousNames;
    }

    public boolean getPreviousNames() {
        return this.previousNames;
    }

    public UserBuilder setPreviousNames(boolean previousNames) {
        this.previousNames = previousNames;
        return this;
    }


    public boolean isGroups() {
        return this.groups;
    }

    public boolean getGroups() {
        return this.groups;
    }

    public UserBuilder setGroups(boolean groups) {
        this.groups = groups;
        return this;
    }

    public boolean isFriends() {
        return this.friends;
    }

    public boolean getFriends() {
        return this.friends;
    }

    public UserBuilder setFriends(boolean friends) {
        this.friends = friends;
        return this;
    }

    public boolean isFriendCount() {
        return this.friendCount;
    }

    public boolean getFriendCount() {
        return this.friendCount;
    }

    public UserBuilder setFriendCount(boolean friendCount) {
        this.friendCount = friendCount;
        return this;
    }

    public boolean isFollowers() {
        return this.followers;
    }

    public boolean getFollowers() {
        return this.followers;
    }

    public UserBuilder setFollowers(boolean followers) {
        this.followers = followers;
        return this;
    }

    public boolean isFollowerCount() {
        return this.followerCount;
    }

    public boolean getFollowerCount() {
        return this.followerCount;
    }

    public UserBuilder setFollowerCount(boolean followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    public boolean isFollowings() {
        return this.followings;
    }

    public boolean getFollowings() {
        return this.followings;
    }

    public UserBuilder setFollowings(boolean followings) {
        this.followings = followings;
        return this;
    }

    public boolean isFollowingsCount() {
        return this.followingsCount;
    }

    public boolean getFollowingsCount() {
        return this.followingsCount;
    }

    public UserBuilder setFollowingsCount(boolean followingsCount) {
        this.followingsCount = followingsCount;
        return this;
    }

    public boolean isBasicUser() {
        return this.basicUser;
    }

    public boolean getBasicUser() {
        return this.basicUser;
    }

    public UserBuilder setBasicUser(boolean basicUser) {
        this.basicUser = basicUser;
        return this;
    }

    public boolean isBadges() {
        return this.badges;
    }

    public boolean getBadges() {
        return this.badges;
    }

    public UserBuilder setBadges(boolean badges) {
        this.badges = badges;
        return this;
    }

    public boolean isAvatar() {
        return this.avatar;
    }

    public boolean getAvatar() {
        return this.avatar;
    }

    public UserBuilder setAvatar(boolean avatar) {
        this.avatar = avatar;
        return this;
    }

    public boolean isThumbnail() {
        return this.thumbnail;
    }

    public boolean getThumbnail() {
        return this.thumbnail;
    }

    public UserBuilder setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public boolean isOutfits() {
        return this.outfits;
    }

    public boolean getOutfits() {
        return this.outfits;
    }

    public UserBuilder setOutfits(boolean outfits) {
        this.outfits = outfits;
        return this;
    }

    public boolean isFavoriteGames() {
        return this.favoriteGames;
    }

    public boolean getFavoriteGames() {
        return this.favoriteGames;
    }

    public UserBuilder setFavoriteGames(boolean favoriteGames) {
        this.favoriteGames = favoriteGames;
        return this;
    }

    @SuppressWarnings("rawtypes")
    public CompletableFuture<User> buildAsync(){
        //do some http shit surely? 
        // nah probably in the actually class right?
        // ok changed my mind again

        //so http stuff here

        ArrayList<CompletableFuture> completables = new ArrayList<>();
        CompletableFuture<User> completableFuture = new CompletableFuture<>();

        User user = new User(this.userId);

        if (this.getGroups()){
            completables.add(UserGroupsJson.request(this.userId)
                .exceptionally(ex -> {
                    completableFuture.completeExceptionally(ex);
                    throw new CompletionException(ex);
                }).whenComplete((request, exception) -> {
                    ArrayList fut = new ArrayList<>();
                    for (UserGroupsJson i: request){
                        UserInGroup userInGroup = new UserInGroup(i.getId());
                        userInGroup.setName(i.getName());
                        userInGroup.setDescription(i.getDescription());
                        userInGroup.setIsBuildersClubOnly(i.isIsBuildersClubOnly());
                        userInGroup.setIsLocked(i.isLocked());
                        userInGroup.setPublicEntryAllowed(i.isPublicEntryAllowed());
                        userInGroup.setCreated(i.getCreated());
                        userInGroup.setCreated(i.getUpdated());

                        if (i.getShoutUserId() != null){
                            User shoutUser = new User(i.getShoutUserId());
                            shoutUser.setName(i.getShoutUsername());
                            shoutUser.setDisplayName(i.getShoutDisplayName());
                            userInGroup.setShoutPoster(shoutUser);
                        }

                        User owner = new User(i.getOwnerUserId());
                        owner.setName(i.getOwnerUsername());
                        owner.setDisplayName(i.getOwnerDisplayName());
                        userInGroup.setOwner(owner);

                        userInGroup.setRoleId(i.getRoleId());
                        userInGroup.setRoleRank(i.getRoleRank());
                        userInGroup.setRoleName(i.getRoleName());

                        fut.add(userInGroup);
                    }
                    user.setGroups(fut);
                }));
        }

        if (this.getPreviousNames()){
            completables.add(UserUsernames.request(this.userId)
                .exceptionally(ex -> {
                    completableFuture.completeExceptionally(ex);
                    throw new CompletionException(ex);
                }).whenComplete((request, exception) -> {
                    user.setPreviousNames(request.getNames());             
                }));
        }

        if (this.getFriends()){
            //
        } else if (this.getFriendCount()){
            completables.add(FriendCount.request(this.userId)
            .exceptionally(ex -> {
                completableFuture.completeExceptionally(ex);
                throw new CompletionException(ex);
            }).whenComplete((request, exception) -> {
                user.setFriendCount(request.getAmmount());             
            }));
        }

        if (this.getFollowers()){
            //
        } else if (this.getFollowerCount()){
            completables.add(FollowerCount.request(this.userId)
            .exceptionally(ex -> {
                completableFuture.completeExceptionally(ex);
                throw new CompletionException(ex);
            }).whenComplete((request, exception) -> {
                user.setFollowerCount(request.getAmmount());             
            }));
        }

        if (this.getFollowings()){
            //
        } else if (this.getFollowingsCount()){
            completables.add(FollowingCount.request(this.userId)
            .exceptionally(ex -> {
                completableFuture.completeExceptionally(ex);
                throw new CompletionException(ex);
            }).whenComplete((request, exception) -> {
                user.setFollowingCount(request.getAmmount());             
            }));
        }

        if (this.getBasicUser()){
            completables.add(UserJson.request(this.userId)
                .exceptionally(ex -> {
                    completableFuture.completeExceptionally(ex);
                    throw new CompletionException(ex);
                }).whenComplete((request, exception) -> {

                    user.setName(request.getName());             
                    user.setDescritption(request.getDescription());
                    user.setCreated(request.getCreated());
                    user.setDisplayName(request.getDisplayName());
                    user.setIsBanned(request.getIsBanned());
                }));
        }

        if (this.getBadges()){
            //
        }

        if (this.getAvatar()){
            //
        }

        if (this.getThumbnail()){
            //
        }

        if (this.getOutfits()){
            //
        }

        if (this.getFavoriteGames()){
            //
        }

        
        CompletableFuture.allOf(completables.toArray(new CompletableFuture[0])).thenRun(() -> completableFuture.complete(user));
        return completableFuture;
    }

    public User build() throws InterruptedException, ExecutionException {
        return this.buildAsync().get();
    }

}
