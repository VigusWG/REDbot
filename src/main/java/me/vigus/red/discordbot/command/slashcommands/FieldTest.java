package me.vigus.red.discordbot.command.slashcommands;

import me.vigus.red.discordbot.command.CustomEmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class FieldTest extends SlashCommand {

    public FieldTest(){
        name = "fieldtest";
        description = "the fieldTest command";
    }

    @Override
    public CommandData make() {
        return new CommandDataImpl(name, description);
    }

    @Override
    public CustomEmbedBuilder execute(SlashCommandInteractionEvent event) {
        try{
            wait(5000);
        } catch (InterruptedException e){
            System.out.println(e);
        }
        String lorem = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur rhoncus, risus at dignissim pulvinar, velit ligula ornare lacus, nec sagittis urna mauris vel dui. Vivamus porta nulla et blandit porta. Sed egestas nec mauris vel accumsan. Nulla varius congue sagittis. Etiam dapibus congue tortor, eget luctus urna aliquam eget. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed ac convallis metus. Etiam at lacinia est. Suspendisse elementum egestas diam, ac scelerisque erat rutrum sed. In tempus felis vel ante dignissim ullamcorper. Nam ultricies venenatis odio. Mauris interdum lorem mauris, in finibus nisl finibus ut. Nunc suscipit velit vitae dolor luctus vestibulum. Vestibulum hendrerit urna velit, sit amet ultrices odio feugiat rhoncus. Nunc convallis laoreet erat a elementum. Suspendisse potenti.
        Nullam eget quam leo. Pellentesque vitae scelerisque turpis, sed cursus quam. Phasellus vel elit tempus, pulvinar ex ut, scelerisque ligula. Duis consectetur sollicitudin lacus. In scelerisque tellus in nunc dignissim, fringilla vehicula elit elementum. Maecenas luctus nunc eget diam vulputate aliquam. Sed semper bibendum pretium. Aenean eleifend consectetur lectus nec hendrerit. Integer vel fermentum magna. Quisque eu odio sit amet ex consequat posuere non ac augue. Nunc a tempus sem, quis hendrerit orci. Sed euismod lobortis commodo. Nam nec enim vitae sem facilisis bibendum et sed dui. Nulla magna metus, scelerisque non rhoncus ac, lobortis ac est. Maecenas at ornare lectus, non volutpat magna.     
        Duis sit amet maximus neque, sit amet scelerisque orci. In diam magna, tristique sit amet tempor vel, rutrum eget nulla. Suspendisse vehicula eleifend est, nec porttitor elit rhoncus et. Pellentesque quis quam accumsan, hendrerit metus at, aliquam sem. Nullam tincidunt accumsan quam vel vehicula. Proin ut risus vestibulum, imperdiet orci mollis, finibus felis. Donec rhoncus massa nec enim dapibus, a hendrerit arcu hendrerit. Fusce consectetur massa vel elit sit.""";

        // I realise this is an odd test lol
        CustomEmbedBuilder b = new CustomEmbedBuilder();
        b.setTitle("field test");
        b.addField("sdrg", lorem, false);
        b.setDescription(String.format("hello i am a description under 6000 chars!%nisValidLength() returns %b", b.isValidLength()));
        b.setThumbnail("https://www.pinclipart.com/picdir/middle/447-4473590_random-png-transparent-background-imagenes-random-png-clipart.png");
        b.setImage("https://www.pinclipart.com/picdir/middle/447-4473590_random-png-transparent-background-imagenes-random-png-clipart.png");
        b.setAuthor("Vigus");
        return b;
    }

    @Override
    public void onButtonPressed(ButtonInteractionEvent event) {
        
    }

    @Override
    public void onMenuInteraction(SelectMenuInteractionEvent event) {
        
    }
    

}
