package me.vigus.red.discordbot.command.commands;

import java.util.ArrayList;

import me.vigus.red.discordbot.command.Command;
import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import me.vigus.red.discordbot.command.interfaces.SlashCommand;
import me.vigus.red.discordbot.command.interfaces.UserCommand;
import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;
import me.vigus.red.robloxjava.builders.UserBuilder;
import me.vigus.red.robloxjava.entities.User;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command.Type;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class Get extends Command implements UserCommand, SlashCommand{

    public Get(){
        name = "get";
        description = "Gets the Discord User";
    }

    @Override
    public void execute(UserContextInteractionEvent event) {
        Long userId = robloxUserArgument.fromOption(event.getTargetMember().getAsMention());
        event.replyEmbeds(doSomething(userId, event.getTargetMember()).formattedBuild()) 
            .addActionRow(
                Button.primary(String.format("check_%d", userId), "Check User").withDisabled(userId==null),
                Button.primary(String.format("favourites_%d", userId), "See User Favourite Assets").withDisabled(userId==null),
                Button.primary(String.format("friendgrouplink_%d", userId), "Correlate the Users Friends and Groups").withDisabled(userId==null)
                //Button.primary(String.format("badgegamelink_%d", userId), "Get the games the user has the most badges from").withDisabled(userId==null)
            )
            .queue();
    }

    // @Override
    // public void execute(SlashCommandInteractionEvent event) {
    //     String option =event.getOption("user").getAsString();
    //     Member mem = event.getGuild().getMemberById(option);
    //     Long userId = robloxUserArgument.fromOption(mem.getAsMention());
    //     event.replyEmbeds(doSomething(userId, mem).formattedBuild()).queue();
    // }

    private CustomEmbedBuilder doSomething(Long userId, Member member){    
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle(member.getEffectiveName());
        b.setThumbnail(member.getUser().getAvatarUrl());
        if (userId != null){
            try {
                User user = new UserBuilder(userId)
                    .setBasicUser(true)
                    .build();
                
                String desc = String.format(
                        "**%s AKA [%s](https://roblox.com/users/%d/profile) joined discord on <t:%s:F> and joined the server on <t:%s:F>.%n**",
                        member.getUser().getName(), user.getName(), userId,
                        member.getUser().getTimeCreated().toInstant().getEpochSecond(),
                        member.getTimeJoined().toInstant().getEpochSecond());
                b.setDescription(desc);
                return b;
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        String desc = String.format(
            "**%s (No roblox profile found) joined discord on <t:%s:F> and joined the server on <t:%s:F>.%n**"
        , member.getUser().getName(), member.getUser().getTimeCreated().toInstant().getEpochSecond(), member.getTimeJoined().toInstant().getEpochSecond());
        b.setDescription(desc);
        return b;
    }

    @Override
    public ArrayList<CommandData> make() {
        ArrayList<CommandData> F = new ArrayList<>();
        F.add(new CommandDataImpl(name, description)
                .addOptions(robloxUserArgument.getDiscordUserOption(true)));
        F.add(new CommandDataImpl(Type.USER, name));
        return F;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member mem = event.getOption("discorduser").getAsMember();
        Long userId = robloxUserArgument.fromOption("<@"+mem.getId()+">");
        event.replyEmbeds(doSomething(userId, mem).formattedBuild())
                .addActionRow(
                        Button.primary(String.format("check_%d", userId), "Check User").withDisabled(userId == null),
                        Button.primary(String.format("favourites_%d", userId), "See User Favourite Assets")
                                .withDisabled(userId == null),
                        Button.primary(String.format("friendgrouplink_%d", userId),
                                "Correlate the Users Friends and Groups").withDisabled(userId == null))
                        //Button.primary(String.format("badgegamelink_%d", userId),"Get the games the user has the most badges from").withDisabled(userId == null))
                .queue();
    }
    
}
