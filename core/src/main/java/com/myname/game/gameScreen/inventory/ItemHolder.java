package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemHolder {

    private static Item pepper;
    private static Item carrot;
    private static Item potato;
    private static Item beetroot;

    private static TextureAtlas textureAtlas;

    //Be sure to construct the ItemHolder with a TextureAtlas before calling setItems() to initialize the items.
    public ItemHolder(TextureAtlas textureAtlas)
    {
        this.textureAtlas = textureAtlas;
        setItems();
    }

    public static void setItems()
    {
        TextureRegion pepperTexture = textureAtlas.findRegion("pepper");
        TextureRegion beetrootTexture = textureAtlas.findRegion("beetroot");
        TextureRegion carrotTexture = textureAtlas.findRegion("carrot");
        TextureRegion potatoTexture = textureAtlas.findRegion("potato");

        pepper = new Item(0,pepperTexture);
        beetroot = new Item(1,beetrootTexture);
        carrot = new Item(2,carrotTexture);
        potato = new Item(3,potatoTexture);
    }

    public static Item getBeetroot() {
        return beetroot;
    }

    public static Item getCarrot() {
        return carrot;
    }

    public static Item getPepper() {
        return pepper;
    }

    public static Item getPotato() {
        return potato;
    }
}
