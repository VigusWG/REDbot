package me.vigus.red.discordbot.discordarguments.robloxuserargument;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.vigus.red.robloxjava.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class robloxUserArgument{

    private static final Pattern urlPattern = Pattern.compile("roblox.com/users/[0-9]*", Pattern.CASE_INSENSITIVE);
    
    public static OptionData getOption(boolean isRequired) {
        return new OptionData(OptionType.STRING, "user", "A discord user mention, roblox username, roblox id or roblox link", isRequired, false);
    }

    public static OptionData getDiscordUserOption(boolean isRequired) {
        return new OptionData(OptionType.USER, "discorduser",
                "A discord user mention", isRequired, false);
    }


    public static boolean isNumeric(String str) {
        // RIGHT OFF OF STACK OVERFLOW LETS FUCKEN GOOOOO
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Long fromURL(String lString){
        Matcher match = urlPattern.matcher(lString);
        if (match.find()) {
            String x = match.group();
            String a = x.substring(x.lastIndexOf("/") + 1);
            return Long.parseLong(a);
        }
        return null;
    }

    public static Long fromOption(String option){
        option = option.strip();
        
        if (isNumeric(option)){
            return Long.parseLong(option);
        }
    
        if (option.startsWith("<@") && option.endsWith(">")){
            Long id = option.charAt(2) == '!' ?  Long.parseLong(option.substring(3, option.length()-1)) : Long.parseLong(option.substring(2, option.length()-1));
            try {
                return User.idFromDiscord(id).get();
            } catch (InterruptedException | ExecutionException e) {
                // Should't fail tbh
                e.printStackTrace();
                return null;
            }
        }
        Long l = fromURL(option);
        if (l != null){
            return l;
        }
        try {
            return User.idFromUsername(option).get();
            
        } catch (InterruptedException | ExecutionException e) {
            //Should't fail tbh
            e.printStackTrace();
            return null;
        }
        

    }

    public static OptionData getOption() {
        return getOption(true);
    }
}