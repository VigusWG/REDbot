package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.entities.Group;
import me.vigus.red.robloxjava.entities.User;
import me.vigus.red.robloxjava.entities.UserInGroup;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class FriendGroupLink extends Command implements SlashCommand, Buttons{
    
    public FriendGroupLink(){
        name = "friendgrouplink";
        description = "Count user's friends groups and get the top ten.";
    }

    class GroupsAmmount extends Group implements Comparable<GroupsAmmount>{
        private int ammount = 1;

        public GroupsAmmount(Group group) {
            super(group.getId());
            this.setName(group.getName());
            this.setDescription(group.getDescription());
            this.setOwner(group.getOwner());
            this.setMemberCount(group.getMemberCount());
            this.setCreated(group.getCreated());
            this.setUpdated(group.getUpdated());
            this.setIsLocked(group.getIsLocked());
            this.setPublicEntryAllowed(group.isPublicEntryAllowed());
            this.setIsBuildersClubOnly(group.isPublicEntryAllowed());
            this.setShoutBody(group.getShoutBody());
            this.setShoutPoster(group.getShoutPoster());
        }

        public GroupsAmmount addOne(){
            this.ammount += 1;
            return this;
        }

        public int getAmmount(){
            return ammount;
        }

        @Override
        public int compareTo(GroupsAmmount o) {
            return o.getAmmount() - this.getAmmount();
        }
    }

    private void realExcec(InteractionHook hook, String userString) {
        Long userId = robloxUserArgument.fromOption(userString);
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

        User user;
        try {
            user = new UserBuilder(userId)
                .setFriends(true)
                .setThumbnail(true)
                .build();
        } catch (Exception e){
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error.");
            b.setDescription(e.getMessage());
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

        try {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle(String.format("List of Groups from Friends for %s",
                    user.getName()));

            b.setThumbnail(user.getThumbnail());

            b.setDescription("Most popular groups their friends are in.");

            ConcurrentHashMap<Long, GroupsAmmount> groups = new ConcurrentHashMap<>();
            ArrayList<CompletableFuture<User>> reqs = new ArrayList<>();
            for (User friend : user.getFriends()){
                CompletableFuture<User> friend2 = new UserBuilder(friend.getId())
                    .setGroups(true)
                    .rebuildAsync(friend)
                    .exceptionally(ex -> {
                        System.out.println(ex.getLocalizedMessage());
                        return null;
                    })
                    .whenComplete((req, exc) -> {
                        if (req == null){
                            return;
                        }
                        if (exc != null) {
                            System.out.println(exc.getLocalizedMessage());
                            return;
                        }
                        req.getGroups().forEach(x -> groups.merge(x.getId(), new GroupsAmmount(x), (g,h) -> g.addOne()));                       
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
            List<GroupsAmmount> topTen = groups.values().stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
            
            StringBuilder stringBuild = new StringBuilder();
            for (GroupsAmmount ent : topTen) {
               stringBuild.append(String.format("%d friends in group [%s](https://www.roblox.com/groups/%d)%n", ent.getAmmount(), ent.getName(), ent.getId()));
            }
            
            b.setDescription(stringBuild.toString());
            hook.editOriginalEmbeds(b.formattedBuild()).queue();

        } catch (Exception e){
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error.");
            b.setDescription(e.getCause().getMessage());
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
        }
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getOption()));
        return F;
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        event.deferReply().queue();
        realExcec(event.getHook(), event.getComponentId().split("_", 2)[1]);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        realExcec(event.getHook(), event.getOption("user").getAsString());
    }
}
