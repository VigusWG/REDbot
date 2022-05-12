package me.vigus.red;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.SerializationFeature;

import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.ThumbnailJson;
import me.vigus.red.robloxjava.connection.json.UserBadges;
import me.vigus.red.robloxjava.connection.json.UserGroupsJson;
import me.vigus.red.robloxjava.connection.structs.ThumbnailRequest;
import me.vigus.red.robloxjava.entities.Badge;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.Outfit;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
import me.vigus.red.robloxjava.enums.ThumnailBatch;
import me.vigus.red.robloxjava.enums.ThumnailFormat;
import me.vigus.red.robloxjava.enums.ThumnailSize;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class App
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        System.out.println("Started.");
            // In case you use Java 8 dates
        //UserBadges.CustomObjectMapper.getMapper().registerModule(new JavaTimeModule());
        //discordBot.main();

        
        // //System.out.println(x.get().body().toString());
        // //Void a = CompletableFuture.allOf(followers,followings,friends).join();
        // System.out.println("hey");
        // System.out.println(followers.join());

        // Thread.sleep(10000);
        // System.out.println(followers.join());
        
        // CompletableFuture<HttpResponse<String>> friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        // ObjectMapper mapper = new ObjectMapper();
        // mapper.readTree(friends.get().body());
        // System.out.println(friends.get().body());    
        // System.out.println(mapper);

        // String response = HTTPConnection.getInstance().makeRequest("https://groups.roblox.com/v1/groups/7704888").get().body();
        // ObjectMapper mapper = new ObjectMapper();
        // Group group = mapper.readValue(response, Group.class);                                                           

        // System.out.println(group.getDescription());          
        // System.out.println(group.getName());
        // System.out.println(group.getMemberCount());
        // System.out.println(group.getShout());

        // CompletableFuture<ArrayList<UserGroupsJson>> groups = UserGroupsJson.request(175135924);
        // ArrayList<UserGroupsJson> us = groups.get();
        // for (UserGroupsJson i : us){
        //     System.out.println(i.getName());
        //     System.out.println(i.getRoleName());
        // }  

        User vigus = new UserBuilder(175135924)
            .setBasicUser(true)
            .setFriends(true)
            .setThumbnail(true)
            .setGroups(true)
            .setPreviousNames(true)
            .setAvatar(true)
            .setOutfits(true)
            .setBadges(true)
            .build();

        System.out.println(vigus.getName());
        System.out.println("Is banned: " +vigus.getIsBanned());


        System.out.println(vigus.getAmmountOfOutfits());

        for (Outfit out : vigus.getOutfits()){
            System.out.println(out.getName());
        }

        System.out.println(vigus.getThumbnail());

        for (Badge bad : vigus.getBadges()){
            System.out.println(bad.getName());
        }


        // for (String name : vigus.getPreviousNames()){
        //     System.out.println(name);
        // }
        // for (UserInGroup group: vigus.getGroups()){
        //     System.out.println(group.getName() + " - " + group.getRoleName() + " - " + group.getOwner().getName());
        // }
    }
}