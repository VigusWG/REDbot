package me.vigus.red.discordbot.command.slashcommands;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.robloxjava.connection.http.HTTPConnection;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Help extends SlashCommand{

    public Help(){
        name = "help";
        description = "the help command";
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description);
    }

    @Override
    public CustomEmbedBuilder execute(SlashCommandInteractionEvent event) {
        
        CompletableFuture<HttpResponse<String>> followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        CompletableFuture<HttpResponse<String>> followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        CompletableFuture<HttpResponse<String>> friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        followers = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followers/count");
        followings = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/followings/count");
        friends = HTTPConnection.getInstance().makeRequest("https://friends.roblox.com/v1/users/23609989/friends/count");
        
        
        try {
            friends.get();
            followers.get();
            followings.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        

        String f = "Shut it fatso";
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("pLEASE WORK");
        b.setDescription(f);
        b.addDefaults();
        b.addField("filed", "value", false);
        return b;
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMenuInteraction(SelectMenuInteractionEvent event) {
        // TODO Auto-generated method stub
        
    }
}
