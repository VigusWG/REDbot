package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class FriendGroupLink extends Command implements SlashCommand{

    public FriendGroupLink(){
        name = "friendgrouplink";
        description = "pls make this";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        Long userId = robloxUserArgument.fromOption(event.getOption("user").getAsString());
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

        User user;
        try {
            user = new UserBuilder(userId)
                .setFriends(true)
                .setThumbnail(true)
                .build();
        } catch (Exception e){
            System.out.println(e);
            return;
        }

        try {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle(user.getName());
            b.setThumbnail(user.getThumbnail());

            b.setDescription("Most popular groups their friends are in.");

            ConcurrentHashMap<String, Integer> groupNumbers = new ConcurrentHashMap<>();
            ArrayList<CompletableFuture<User>> reqs = new ArrayList<>();
            for (User friend : user.getFriends().stream().limit(160).toList()){
                
                //just to test
            

                //User friend = user.getFriends().get(i); //vs code was buggen when i did it the normal way
                CompletableFuture<User> friend2 = new UserBuilder(friend.getId())
                    .setGroups(true)
                    .rebuildAsync(friend)
                    .exceptionally(ex -> {System.out.println(ex.getLocalizedMessage()); return null;})
                    .whenComplete((req, exc) -> {
                        if (req == null){
                            return;
                        }
                        if (exc != null) {
                            System.out.println(exc.getMessage());
                            return;
                        }
                        req.getGroups().forEach(x -> groupNumbers.merge(x.getName(), 1, Integer::sum));                       
                    });

                reqs.add(friend2);
            }
            //System.out.println(reqs);

            //reqs.forEach(CompletableFuture::join);
            // while (!CompletableFuture.allOf(reqs.toArray(new CompletableFuture[0])).isDone()){
                
            //     //System.out.println(CompletableFuture.allOf(reqs.toArray(new CompletableFuture[0])).isDone());
            // }
            //Thread.sleep(15000);
            CompletableFuture.allOf(reqs.toArray(new CompletableFuture[0])).join();
            //System.out.println(reqs);
            

            // CompletableFuture.allOf(reqs.toArray(new CompletableFuture[0])).wait(); //wait for all to be got.
            // reqs.forEach(t -> {
            //     try {
            //         t.get();
            //     } catch (InterruptedException | ExecutionException e) {
            //         ;
            //     }
            // });
            Map<String, Integer> topTen = groupNumbers.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(10)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); //no fucking clue what this is about: i stole it
            
            System.out.println(topTen);

            for (Entry<String, Integer> ent : topTen.entrySet()) {
               b.addField(ent.getKey(), ent.getValue().toString(), false);
            }
            
                // Map<Integer, String> sortedMap = groupNumbers.entrySet().stream()
            //         .sorted(Entry.comparingByValue())
            //         .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
            //                 (e1, e2) -> e1, LinkedHashMap::new));
            event.getHook().editOriginalEmbeds(b.formattedBuild()).queue();

        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            ;
        }
        
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption());
    }
    
}
