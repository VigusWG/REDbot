package me.vigus.red.robloxjava.entities;

public class Outfit {
    private long id;
    private String name;
    private boolean editable;

    

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEditable() {
        return this.editable;
    }

    public boolean getEditable() {
        return this.editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    

}