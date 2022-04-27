package me.vigus.red.robloxjava;

public class Outfit {
    private long id;
    private String name;
    private boolean editable;

    public Outfit(long id, String name, boolean editable) {
        this.id = id;
        this.name = name;
        this.editable = editable;
    }


    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getEditable() {
        return this.editable;
    }

    public boolean isEditable() {
        return this.editable;
    }

}