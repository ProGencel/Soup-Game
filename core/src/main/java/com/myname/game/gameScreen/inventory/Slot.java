package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Slot extends Button {

    private Item item;
    private int stackAmount;
    private Stack stack;
    private Image itemImage;

    public Slot(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.item = null;
        this.stackAmount = 0;

        stack = new Stack();
        itemImage = new Image();
        stack.add(itemImage);

        this.add(stack).fill().expand();
    }

    public Item getItem() {
        return item;
    }

    public int getStackAmount() {
        return stackAmount;
    }

    public void setItem(Item item) {
        this.item = item;
        itemImage.setDrawable(new TextureRegionDrawable(item.getIcon()));
    }

    public void increaseStackAmount() {
        this.stackAmount++;
    }
}
