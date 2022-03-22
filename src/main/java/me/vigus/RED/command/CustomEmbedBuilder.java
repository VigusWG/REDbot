package me.vigus.RED.command;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import okhttp3.internal.connection.StreamAllocation;

public class CustomEmbedBuilder extends EmbedBuilder{

            
    protected final List<MessageEmbed.Field> fields = new LinkedList<>();

    public void addDefaults(){
        this.setTimestamp(Instant.now());
        this.setColor(Color.RED);
    }

    public Collection<MessageEmbed> formattedBuild(){
        Collection<MessageEmbed> embeds = new ArrayList<>();

        for (CustomEmbedBuilder newEmbed : this.splitUpEmbeds()){
            newEmbed.addDefaults();
            System.out.println(newEmbed.getFields());
            embeds.add(newEmbed.build());
        }
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

    private Collection<CustomEmbedBuilder> splitUpEmbeds(){
        List<CustomEmbedBuilder> embeds = new ArrayList<>();

        if (this.isValidLength()){
            embeds.add(this);
            return embeds;
        }

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
            }else{
                fields.add(field);
            }
        }


        boolean keepEmbedding = true;



        while (keepEmbedding){
            //oooh how very dangerous
            CustomEmbedBuilder nextCustomEmbedBuilder = new CustomEmbedBuilder();
            nextCustomEmbedBuilder.copyFrom(this);
            nextCustomEmbedBuilder.setDescription("");

            nextCustomEmbedBuilder.clearFields();
            if (!(descs.isEmpty())){
                nextCustomEmbedBuilder.setDescription(descs.get(0));
                descs.remove(0);
            }

            while (true){
                nextCustomEmbedBuilder.addField(fields.get(0));
                if (!(nextCustomEmbedBuilder.isValidLength())){
                    nextCustomEmbedBuilder.getFields().remove(fields.get(0));
                    break;
                }else{
                    fields.remove(0);
                }
            }
            

            if (descs.isEmpty() && fields.isEmpty()){
                keepEmbedding = false;
            }

            embeds.add(nextCustomEmbedBuilder);
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

        if (this.getFields().size() > 25) {return false;}
        for (Field field : this.getFields()){
            if (field.getName().length() > MessageEmbed.TITLE_MAX_LENGTH){return false;}
            if (field.getValue().length() > MessageEmbed.VALUE_MAX_LENGTH){return false;}
        }


        return true;
    }

    /**
     * Adds a Split up Field to the embed. 
     *
     * <p><b><a href="https://raw.githubusercontent.com/DV8FromTheWorld/JDA/assets/assets/docs/embeds/07-addField.png">Example of Inline</a></b>
     * <p><b><a href="https://raw.githubusercontent.com/DV8FromTheWorld/JDA/assets/assets/docs/embeds/08-addField.png">Example if Non-inline</a></b>
     *
     * @param  name
     *         the name of the Field, displayed in bold above the {@code value}.
     * @param  value
     *         the contents of the field.
     * @param  inline
     *         whether or not this field should display inline.
     *
     * @see MessageEmbed#addField
     * @return the builder after the field has been added
     */
    public EmbedBuilder addSplitField(String name, String value, boolean inline){

        if (value.length() >= MessageEmbed.VALUE_MAX_LENGTH){

            List<String> val = splitUpString(value, MessageEmbed.VALUE_MAX_LENGTH);

            for (int i = 0; i < val.size(); i++){
                String tit = i == 0 ? name : name + String.format(" %d", i);
                this.addField(new Field(tit, val.get(0), inline));
            }

        }else{
            this.addField(name, value, inline);
        }
        
        return this;
    }



}
