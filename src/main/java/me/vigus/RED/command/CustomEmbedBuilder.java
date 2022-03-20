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

        List<MessageEmbed> embeds = new ArrayList<>();
        CustomEmbedBuilder currentEmbed = this;

        Boolean keepEmbedding = true;
        int embedNo = 0;

        while (keepEmbedding){
            //oooh how very dangerous
            CustomEmbedBuilder nextCustomEmbedBuilder = new CustomEmbedBuilder();
            nextCustomEmbedBuilder.copyFrom(currentEmbed);

            nextCustomEmbedBuilder.clearFields();
            nextCustomEmbedBuilder.setDescription(descs.get(0));
        }
        while (!(currentEmbed.isValidLength())){
            //oooh how very dangerous
            CustomEmbedBuilder nextCustomEmbedBuilder = new CustomEmbedBuilder();
            nextCustomEmbedBuilder.copyFrom(currentEmbed);

            nextCustomEmbedBuilder.clearFields();
            // continue next time see white board for mor einfo
            
        }

        return embeds;
    }


    /**
     * Checks whether the constructed {@link net.dv8tion.jda.api.entities.MessageEmbed MessageEmbed}
     * is within the limits for a bot account. UPDATED FOR FIELDS AND DESCRIPTIONS
     *
     * @return True, if all is cool
     * @apiNote Title, footer and author lengths cannot be verified.
     */
    @Override
    public boolean isValidLength(){
        
        if (this.length() > MessageEmbed.EMBED_MAX_LENGTH_BOT) {return false;}
        if (this.getDescriptionBuilder().length() > MessageEmbed.DESCRIPTION_MAX_LENGTH) {return false;}

        for (Field field : this.getFields()){
            if (field.getName().length() > MessageEmbed.TITLE_MAX_LENGTH){return false;}
            if (field.getValue().length() > MessageEmbed.VALUE_MAX_LENGTH){return false;}
        }
        return true;
    }

}
