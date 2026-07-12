package com.myname.game.gameScreen.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.myname.game.gameScreen.GUI.inventory.Item;

public class SoupSlot extends Button {

    private Item item;
    private int stackAmount;
    private Stack stack;
    private Image itemImage;

    private Table labelTable;
    private Label label;

    private TextureRegionDrawable darkOverlayDrawable;
    private Image overlay;

    private boolean isCraftable = false;

    public SoupSlot(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.item = null;
        this.stackAmount = 1;

        stack = new Stack();
        itemImage = new Image();
        labelTable = new Table();
        setLabel();

        labelTable.bottom().right();
        labelTable.add(label).padRight(10).padBottom(2);

        setDarkOverlay();

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

    public void setItemImage(TextureRegion itemImage) {
        this.itemImage.setDrawable(new TextureRegionDrawable(itemImage));
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

    public void setOverlay(boolean visible)
    {
        if(darkOverlayDrawable != null)
        {
            overlay.setVisible(visible);
        }
    }

    private void setDarkOverlay()
    {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.6f);
        pixmap.fill();
        Texture darkTexture = new Texture(pixmap);
        pixmap.dispose();
        darkOverlayDrawable = new TextureRegionDrawable(new TextureRegion(darkTexture));
        overlay = new Image(darkOverlayDrawable);
        overlay.setFillParent(true);
        overlay.setVisible(false);

        stack.add(overlay);

    }

    public void increaseStackAmount() {
        this.stackAmount++;
    }

    public void setStackAmount(int stackAmount) {
        this.stackAmount = stackAmount;

    }

    public boolean isCraftable() {
        return isCraftable;
    }

    public void setCraftable(boolean craftable) {
        isCraftable = craftable;
    }
}
