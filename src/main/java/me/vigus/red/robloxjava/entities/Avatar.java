package me.vigus.red.robloxjava.entities;

import java.util.ArrayList;

import me.vigus.red.robloxjava.connection.json.AssetInformationJson;

public class Avatar {
    private Long userId;

    private Boolean defaultShirt;
    private Boolean defaultPants;

    private Float height;
    private Float width;
    private Float head;
    private Float depth;
    private Float proportion;
    private Float bodyType;

    private String playerAvatarType;

    private Integer headColorId;
    private Integer torsoColorId;
    private Integer rightArmColorId;
    private Integer leftArmColorId;
    private Integer rightLegColorId;
    private Integer leftLegColorId;

    private ArrayList<AssetInformationJson> assets = new ArrayList<>();


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean isDefaultShirt() {
        return this.defaultShirt;
    }

    public Boolean getDefaultShirt() {
        return this.defaultShirt;
    }

    public void setDefaultShirt(Boolean defaultShirt) {
        this.defaultShirt = defaultShirt;
    }

    public Boolean isDefaultPants() {
        return this.defaultPants;
    }

    public Boolean getDefaultPants() {
        return this.defaultPants;
    }

    public void setDefaultPants(Boolean defaultPants) {
        this.defaultPants = defaultPants;
    }

    public Float getHeight() {
        return this.height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return this.width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHead() {
        return this.head;
    }

    public void setHead(Float head) {
        this.head = head;
    }

    public Float getDepth() {
        return this.depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getProportion() {
        return this.proportion;
    }

    public void setProportion(Float proportion) {
        this.proportion = proportion;
    }

    public Float getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(Float bodyType) {
        this.bodyType = bodyType;
    }

    public String getPlayerAvatarType() {
        return this.playerAvatarType;
    }

    public void setPlayerAvatarType(String playerAvatarType) {
        this.playerAvatarType = playerAvatarType;
    }

    public Integer getHeadColorId() {
        return this.headColorId;
    }

    public void setHeadColorId(Integer headColorId) {
        this.headColorId = headColorId;
    }

    public Integer getTorsoColorId() {
        return this.torsoColorId;
    }

    public void setTorsoColorId(Integer torsoColorId) {
        this.torsoColorId = torsoColorId;
    }

    public Integer getRightArmColorId() {
        return this.rightArmColorId;
    }

    public void setRightArmColorId(Integer rightArmColorId) {
        this.rightArmColorId = rightArmColorId;
    }

    public Integer getLeftArmColorId() {
        return this.leftArmColorId;
    }

    public void setLeftArmColorId(Integer leftArmColorId) {
        this.leftArmColorId = leftArmColorId;
    }

    public Integer getRightLegColorId() {
        return this.rightLegColorId;
    }

    public void setRightLegColorId(Integer rightLegColorId) {
        this.rightLegColorId = rightLegColorId;
    }

    public Integer getLeftLegColorId() {
        return this.leftLegColorId;
    }

    public void setLeftLegColorId(Integer leftLegColorId) {
        this.leftLegColorId = leftLegColorId;
    }

    public ArrayList<AssetInformationJson> getAssets() {
        return this.assets;
    }

    public void setAssets(ArrayList<AssetInformationJson> assets) {
        this.assets = assets;
    }

}
