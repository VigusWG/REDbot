package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.Buttons;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.connection.json.AssetInformationJson;
import me.vigus.red.robloxjava.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class BadgeGameLink extends Command implements SlashCommand, Buttons {

    public BadgeGameLink(){
        name = "badgegamelink";
        description = "Correlate between which games all the badges are coming from.";
    }

    class GameAmmount implements Comparable{
        private int ammount = 1;
        private long id;
        public AssetInformationJson game;
        
        public GameAmmount(long id){
            this.id = id;
        }

        public GameAmmount addOne(){
            ammount++;
            return this;
        }

        public int getAmmount(){
            return ammount;
        }

        public CompletableFuture<AssetInformationJson> getGame() throws InterruptedException{
            return AssetInformationJson.request(this.id).whenComplete((req, exc) -> this.game = req);
        }

        @Override
        public int compareTo(Object o) {
            return ((GameAmmount) o).getAmmount() - this.getAmmount();
        }
    }

    public void realExcec(InteractionHook hook, String userArg) {
        Long userId = robloxUserArgument.fromOption(userArg);
        if (userId == null) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error. Invalid User.");
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            return;
        }

        try {
            User user = new UserBuilder(userId)
                .setThumbnail(true)
                .setBadges(true)
                .build();
            
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle(String.format("List of Badges from Games for %s", user.getName()));
            b.setThumbnail(user.getThumbnail());
            
            HashMap<Long, GameAmmount> games = new HashMap<>();
            if (user.getBadges().size() >= 1400) {
                b.setTitle("Error!");
                b.setDescription("The user has over 1400 badges and we can't count that high.");
                hook.editOriginalEmbeds(b.formattedBuild()).queue();
                return;
            }
            user.getBadges().forEach(x -> games.merge(x.getAwarderId(), new GameAmmount(x.getAwarderId()), (y,o)-> y.addOne()));
            List<GameAmmount> topTen = games.values().stream()
                    .sorted()
                    .limit(10)
                    .collect(Collectors.toList());

            CompletableFuture.allOf(topTen.stream().map(t -> {
                try {
                    return t.getGame();
                } catch (InterruptedException e) {
                    AssetInformationJson a =  new AssetInformationJson();
                    a.setName("Error");
                    return a;
                }
            }).collect(Collectors.toList()).toArray(
                    new CompletableFuture[0])).join();

            StringBuilder desc = new StringBuilder();
            for (GameAmmount g :topTen){
                desc.append(String.format("%n%d badges from [%s](https://www.roblox.com/games/%d)", g.getAmmount(), g.game.getName(), g.game.getAssetId()));
            }
            b.setDescription(desc.toString());
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            
        
        
        } catch (InterruptedException | ExecutionException e) {
            CustomEmbedBuilder b = new CustomEmbedBuilder();
            b.setTitle("Error when getting user.");
            hook.editOriginalEmbeds(b.formattedBuild()).queue();
            return;
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