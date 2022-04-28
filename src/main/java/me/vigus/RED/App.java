package me.vigus.red;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.sound.midi.Track;

import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.UserGroupsJson;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
public class App
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        System.out.println("Started.");
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

        User vigus = new UserBuilder(1999999999)
            .setBasicUser(true)
            //.setGroups(true)
            //.setPreviousNames(true)
            .build();

        System.out.println(vigus.getName());
        System.out.println("Is banned: " +vigus.getIsBanned());
        
        // for (String name : vigus.getPreviousNames()){
        //     System.out.println(name);
        // }
        // for (UserInGroup group: vigus.getGroups()){
        //     System.out.println(group.getName() + " - " + group.getRoleName() + " - " + group.getOwner().getName());
        // }
    }
}