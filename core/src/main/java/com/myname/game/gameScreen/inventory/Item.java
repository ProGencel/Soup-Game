package com.myname.game.gameScreen.inventory;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

    private int ID;
    private TextureRegion icon;

    public Item(int ID, TextureRegion icon) {
        this.ID = ID;
        this.icon = icon;
    }

    public TextureRegion getIcon() {
        return icon;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
