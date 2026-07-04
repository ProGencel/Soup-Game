package com.myname.game.gameScreen.GUI.soupScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myname.game.gameScreen.GUI.SoupSlot;
import com.myname.game.gameScreen.GUI.inventory.Inventory;
import com.myname.game.gameScreen.GUI.inventory.ItemHolder;


public class SoupScreen {

    private final int SLOT_SIZE = 6;

    private final Stage stage;
    private final TextureAtlas textureAtlas;
    private final Inventory inventory;
    private final Array<SoupSlot> slotArray;

    private final Table mainTable;
    private final Table lessMainTable;
    private final Table vegeTable;

    private final Stack stack;

    private int beetrootCount = 0;
    private int carrotCount = 0;
    private int potatoCount = 0;
    private int pepperCount = 0;

    private Label beetLabel;
    private Label carrotLabel;
    private Label potatoLabel;
    private Label pepperLabel;

    public SoupScreen(TextureAtlas textureAtlas, Inventory inventory) {
        this.textureAtlas = textureAtlas;
        this.inventory = inventory;

        stage = new Stage(new ScreenViewport());

        slotArray = new Array<>(SLOT_SIZE);
        TextureRegionDrawable textureSlot = new TextureRegionDrawable(textureAtlas.findRegion("slot"));


        for(int i = 0; i < SLOT_SIZE; i++)
        {
            slotArray.add(new SoupSlot(textureSlot));
        }

        mainTable = new Table();
        lessMainTable = new Table();
        vegeTable = new Table();
        stack = new Stack();
        beetLabel = setLabel();
        carrotLabel = setLabel();
        pepperLabel = setLabel();
        potatoLabel = setLabel();
        setTables();

        onEverything();


    }

    private void setTables()
    {
        TextureRegionDrawable panelBackground = new TextureRegionDrawable(textureAtlas.findRegion("SoupManGui"));
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        lessMainTable.setBackground(panelBackground);
        setSlots();
        stack.add(lessMainTable);

        vegeTable.top().left();
        Image beetrootImage = new Image(ItemHolder.getBeetroot().getIcon());
        Image carrotImage = new Image(ItemHolder.getCarrot().getIcon());
        Image potatoImage = new Image(ItemHolder.getPotato().getIcon());
        Image pepperImage = new Image(ItemHolder.getPepper().getIcon());
        vegeTable.add(beetrootImage).pad(10).size(100,100);
        vegeTable.add(beetLabel).bottom().left().row();
        vegeTable.add(carrotImage).pad(10).size(100,100);
        vegeTable.add(carrotLabel).bottom().left().row();
        vegeTable.add(potatoImage).pad(10).size(100,100);
        vegeTable.add(potatoLabel).bottom().left().row();
        vegeTable.add(pepperImage).pad(10).size(100,100);
        vegeTable.add(pepperLabel).bottom().left().row();
        stack.add(vegeTable);


        mainTable.add(stack).size(1250,1250*0.5f);
    }

    private void onEverything()
    {
        mainTable.setVisible(true);
        mainTable.setTouchable(Touchable.disabled);
        for (int i = 0; i < 4; i++) {
            var slot = inventory.getSlotArray().get(i);

            if (slot == null || slot.getItem() == null) continue;

            int itemID = slot.getItem().getID();
            int amount = slot.getStackAmount();

            if (itemID == ItemHolder.getBeetroot().getID()) {
                beetrootCount = amount;
                beetLabel.setText(amount);
            } else if (itemID == ItemHolder.getCarrot().getID()) {
                carrotCount = amount;
                carrotLabel.setText(amount);
            } else if (itemID == ItemHolder.getPotato().getID()) {
                potatoCount = amount;
                potatoLabel.setText(amount);
            } else if (itemID == ItemHolder.getPepper().getID()) {
                pepperCount = amount;
                pepperLabel.setText(amount);
            }
        }
    }

    private void offEverything()
    {
        mainTable.setVisible(false);
        mainTable.setTouchable(Touchable.disabled);
    }

    private void setSlots()
    {
        int i = 0;
        for(SoupSlot slot : slotArray)
        {
            if(i % 3 == 0 && i != 0)
            {
                lessMainTable.row();
            }
            i++;
            lessMainTable.add(slot).size(100,100).pad(50);
        }
    }

    public void render(float delta)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.O))
        {
           if(mainTable.isVisible())
           {
               offEverything();
           }
           else
           {
               onEverything();
               Gdx.app.log("SoupScreen", "Beetroot: " + beetrootCount + ", Carrot: " + carrotCount + ", Potato: " + potatoCount + ", Pepper: " + pepperCount);

           }
        }
        stage.act(delta);
        stage.draw();
    }

    public Label setLabel()
    {
        BitmapFont bitmapFont = new BitmapFont();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = Color.BLACK;
        labelStyle.font.getData().setScale(1.5f);

        return new Label(" ",labelStyle);
    }

    public void resize(int width, int height)
    {
        stage.getViewport().update(width,height,true);
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose()
    {
        stage.dispose();
    }

}
