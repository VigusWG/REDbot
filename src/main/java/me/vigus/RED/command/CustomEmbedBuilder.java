package me.vigus.RED.command;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;

public class CustomEmbedBuilder extends EmbedBuilder{
    public void addDefaults(){
        this.setTimestamp(Instant.now());
        this.setColor(Color.RED);
    }

    public Collection<MessageEmbed> formattedBuild(){
        Collection<MessageEmbed> embeds = new ArrayList<>();
        embeds.add(this.build());
        return embeds;
    }

    private List<String> splitUpString(String string, int length){
        List<String> descs = new ArrayList<>();
        if (string.length() >= length){
            String des = "";
            for (String sec : string.split("\n")){
                if (des.length() + sec.length() >= length){
                    descs.add(des);
                    des = sec;
                }else{
                    des += " "+sec;
                }
            }
        }else{
            descs.add(string);
        }
        return descs;
    }

    private Collection<MessageEmbed> splitUpEmbeds(){
        int needed = 1;
        List<String> descs = splitUpString(this.getDescriptionBuilder().toString(), MessageEmbed.DESCRIPTION_MAX_LENGTH);

        List<Field> fields = new ArrayList<>();

        for (int index = 0; index < this.getFields().size(); index++){
            Field field = this.getFields().get(index);

            if (field.getValue().length() >= MessageEmbed.VALUE_MAX_LENGTH){
                List<String> val = splitUpString(field.getValue(), MessageEmbed.VALUE_MAX_LENGTH);
                for (int i = 0; i < val.size(); i++){
                    String tit = i == 0 ? field.getName() : field.getName() + String.format(" %d", i);
                    fields.add(new Field(tit, val.get(0), field.isInline()));
                }
            }


        }
        return null;
    }

}
