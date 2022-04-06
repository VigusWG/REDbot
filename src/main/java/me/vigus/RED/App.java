package me.vigus.red;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.vigus.red.discordbot.command.discordBot;
import me.vigus.red.robloxjava.connection.http.HTTPConnection;

public class App
{
    public static void main(String[] args) throws LoginException, InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException
    {
        discordBot.main();
        
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
    }
}