package me.vigus.RED.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class RobloxUser {
    
    private static OptionData x = new OptionData(OptionType.STRING, "robloxuser", "Anything", true, true);
    

    private static List<OptionData> optionData = new ArrayList<>(Arrays.asList(
        x
    ));
    
    public static List<OptionData> getOption(){
       return optionData;
    }

    public int toRobloxId(Object x){
        return 0;
    }
    
}
