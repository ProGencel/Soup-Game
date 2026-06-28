package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Slot extends Button {

    private Item item;
    private int stackAmount = 0;

    public Slot(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.item = null;
        this.stackAmount = 0;
    }

    public Item getItem() {
        return item;
    }

    public int getStackAmount() {
        return stackAmount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void increaseStackAmount() {
        this.stackAmount++;
    }
}
