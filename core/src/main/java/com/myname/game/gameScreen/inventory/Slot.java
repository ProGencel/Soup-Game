package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Slot extends Button {

    private Item item;
    private int stackAmount;
    private Stack stack;
    private Image itemImage;

    private Table labelTable;
    private Label label;

    public Slot(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.item = null;
        this.stackAmount = 1;

        stack = new Stack();
        itemImage = new Image();
        labelTable = new Table();
        setLabel();

        labelTable.bottom().right();
        labelTable.add(label).padRight(10).padBottom(2);

        stack.add(itemImage);
        stack.add(labelTable);

        this.add(stack).fill().expand();
    }

    public Item getItem() {
        return item;
    }

    public int getStackAmount() {
        return stackAmount;
    }

    public void setItem(Item item) {

        if(this.item != null)
        {
            if(this.item.getID() == item.getID())
            {
                this.increaseStackAmount();
                label.setText(stackAmount);
            }
        }
        else
        {
            this.item = item;
            itemImage.setDrawable(new TextureRegionDrawable(item.getIcon()));
        }
    }

    public void setLabel()
    {
        BitmapFont bitmapFont = new BitmapFont();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font.getData().setScale(1.5f);

        label = new Label(" ",labelStyle);
    }

    public void increaseStackAmount() {
        this.stackAmount++;
    }
}
