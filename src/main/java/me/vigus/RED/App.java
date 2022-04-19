package me.vigus.red;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.vigus.red.discordbot.discordBot;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import me.vigus.red.robloxjava.connection.json.UserJson;
import me.vigus.red.robloxjava.entities.User;

public class App
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
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

        //CompletableFuture<UserJson> user = UserJson.request(2147483647);
        //UserJson us = user.get();
        //System.out.println(us.getName());      


        System.out.println("\n\n-------------------------------------------\n\n");

        


        User user2 = new UserBuilder(47483647)
            .setBasicUser(true)
            .build();

        System.out.println(user2.getName());

        try {
            User user3 = new UserBuilder(2147483647)
                .setBasicUser(true)
                .build();
            System.out.println(user3.getName());
            
        } catch (ExecutionException ex){
            //ex.printStackTrace();
        }
        

    }
}