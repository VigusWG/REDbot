package me.vigus.red.discordbot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.vigus.red.discordbot.discordarguments.robloxuserargument.robloxUserArgument;

public class RobloxUserArgumentTest {

    @Test
    public void optionAsURL(){
        long result = robloxUserArgument.fromOption("https://www.roblox.com/users/175135924/profile");
        assertEquals(175135924L, result);
    }

    @Test
    public void optionAsID(){
        long result = robloxUserArgument.fromOption("175135924");
        assertEquals(175135924L, result);
    }

    @Test
    public void optionAsDiscordMention(){ 
        //destined to fail.
        long result = robloxUserArgument.fromOption("<@373089590828859398>");
        assertEquals(175135924L, result);  
    }

    @Test
    public void optionAsUsernsme() {
        long result = robloxUserArgument.fromOption("vigus21");
        assertEquals(175135924L, result);
    }

    @Test
    public void isNumericWorksForTrue(){
        assertEquals(true, robloxUserArgument.isNumeric("322"));
    }
    
    @Test
    public void isNumericWorksForFalse() {
        assertEquals(false, robloxUserArgument.isNumeric("43fx"));
    }
}
