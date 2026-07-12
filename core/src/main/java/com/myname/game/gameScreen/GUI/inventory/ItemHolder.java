package com.myname.game.gameScreen.GUI.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemHolder {

    private static Item pepper;
    private static Item carrot;
    private static Item potato;
    private static Item beetroot;

    private static Item beetCarrSoup;
    private static Item beetPeppSoup;
    private static Item beetPotatoSoup;
    private static Item carrPeppSoup;
    private static Item carrPotatoSoup;
    private static Item peppPotatoSoup;

    private static TextureAtlas textureAtlas;

    // Be sure to construct the ItemHolder with a TextureAtlas before calling setItems() to initialize the items.
    public ItemHolder(TextureAtlas textureAtlas)
    {
        ItemHolder.textureAtlas = textureAtlas;
        setItems();
    }

    public static void setItems()
    {
        TextureRegion pepperTexture = textureAtlas.findRegion("pepper");
        TextureRegion beetrootTexture = textureAtlas.findRegion("beetroot");
        TextureRegion carrotTexture = textureAtlas.findRegion("carrot");
        TextureRegion potatoTexture = textureAtlas.findRegion("potato");

        TextureRegion beetCarrTexture = textureAtlas.findRegion("beetCarr");
        TextureRegion beetPeppTexture = textureAtlas.findRegion("beetpepp");
        TextureRegion beetPotatoTexture = textureAtlas.findRegion("beetPotato");
        TextureRegion carrPeppTexture = textureAtlas.findRegion("carrPepp");
        TextureRegion carrPotatoTexture = textureAtlas.findRegion("carrPotato");
        TextureRegion peppPotatoTexture = textureAtlas.findRegion("peppPotato");

        pepper = new Item(0, pepperTexture);
        beetroot = new Item(1, beetrootTexture);
        carrot = new Item(2, carrotTexture);
        potato = new Item(3, potatoTexture);

        beetCarrSoup = new Item(4, beetCarrTexture);
        beetPeppSoup = new Item(5, beetPeppTexture);
        beetPotatoSoup = new Item(6, beetPotatoTexture);
        carrPeppSoup = new Item(7, carrPeppTexture);
        carrPotatoSoup = new Item(8, carrPotatoTexture);
        peppPotatoSoup = new Item(9, peppPotatoTexture);
    }

    public static Item getPepper() {
        return pepper;
    }

    public static Item getBeetroot() {
        return beetroot;
    }

    public static Item getCarrot() {
        return carrot;
    }

    public static Item getPotato() {
        return potato;
    }

    public static Item getBeetCarrSoup() {
        return beetCarrSoup;
    }

    public static Item getBeetPeppSoup() {
        return beetPeppSoup;
    }

    public static Item getBeetPotatoSoup() {
        return beetPotatoSoup;
    }

    public static Item getCarrPeppSoup() {
        return carrPeppSoup;
    }

    public static Item getCarrPotatoSoup() {
        return carrPotatoSoup;
    }

    public static Item getPeppPotatoSoup() {
        return peppPotatoSoup;
    }
}
