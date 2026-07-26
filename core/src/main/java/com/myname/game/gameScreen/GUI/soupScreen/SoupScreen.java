package com.myname.game.gameScreen.GUI.soupScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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
import com.myname.game.gameScreen.GUI.Slot;
import com.myname.game.gameScreen.GUI.SoupSlot;
import com.myname.game.gameScreen.GUI.inventory.Inventory;
import com.myname.game.gameScreen.GUI.inventory.ItemHolder;
import com.myname.game.gameScreen.GameScreen;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.GameStateEvent.GameEventListener;
import com.myname.game.gameScreen.event.GenericEvent.GenericEvent;
import com.myname.game.gameScreen.event.GenericEvent.GenericEventListener;
import com.myname.game.gameScreen.event.SlotEvent.SlotEvent;
import com.myname.game.gameScreen.event.SlotEvent.SlotEventListener;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;


public class SoupScreen implements GameEventListener, SlotEventListener {

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

    private Image dimOverlay;

    public SoupScreen(TextureAtlas textureAtlas, Inventory inventory) {
        this.textureAtlas = textureAtlas;
        this.inventory = inventory;

        EventManager.subscribeGameEvent(this);
        EventManager.subscribeSlotEvent(this);

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
        setupDimOverlay();
        setTables();
        offEverything();

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
        mainTable.setTouchable(Touchable.enabled);
        dimOverlay.setVisible(true);
        updateVegetableCounts();
    }

    private void updateVegetableCounts() {
        beetrootCount = 0;
        carrotCount = 0;
        potatoCount = 0;
        pepperCount = 0;

        for (int i = 0; i < inventory.getSlotArray().size; i++) {
            Slot slot = inventory.getSlotArray().get(i);
            if (slot == null || slot.getItem() == null) continue;

            int itemID = slot.getItem().getID();
            int amount = slot.getStackAmount();

            if (itemID == ItemHolder.getBeetroot().getID()) beetrootCount = amount;
            else if (itemID == ItemHolder.getCarrot().getID()) carrotCount = amount;
            else if (itemID == ItemHolder.getPotato().getID()) potatoCount = amount;
            else if (itemID == ItemHolder.getPepper().getID()) pepperCount = amount;
        }

        beetLabel.setText(beetrootCount);
        carrotLabel.setText(carrotCount);
        potatoLabel.setText(potatoCount);
        pepperLabel.setText(pepperCount);

        setCraftables();
        setDarks();
    }

    private void interact()
    {
        if(mainTable.isVisible())
        {
            offEverything();
        }
        else
        {
            onEverything();
        }
    }

    private void offEverything()
    {
        mainTable.setVisible(false);
        mainTable.setTouchable(Touchable.disabled);
        dimOverlay.setVisible(false);
        GameScreen.setGameState(GameState.GAME);
    }

    private void setSlots()
    {
        slotArray.get(0).setItemImage(textureAtlas.findRegion("beetCarr"));
        slotArray.get(1).setItemImage(textureAtlas.findRegion("beetpepp"));
        slotArray.get(2).setItemImage(textureAtlas.findRegion("beetPotato"));
        slotArray.get(3).setItemImage(textureAtlas.findRegion("carrPepp"));
        slotArray.get(4).setItemImage(textureAtlas.findRegion("carrPotato"));
        slotArray.get(5).setItemImage(textureAtlas.findRegion("peppPotato"));

        // Her slotun gerektirdiği iki item'ı tanımlıyoruz
        Image[][] requirements = new Image[][] {
            { new Image(ItemHolder.getBeetroot().getIcon()), new Image(ItemHolder.getCarrot().getIcon()) },
            { new Image(ItemHolder.getBeetroot().getIcon()), new Image(ItemHolder.getPepper().getIcon()) },
            { new Image(ItemHolder.getBeetroot().getIcon()), new Image(ItemHolder.getPotato().getIcon()) },
            { new Image(ItemHolder.getCarrot().getIcon()),   new Image(ItemHolder.getPepper().getIcon()) },
            { new Image(ItemHolder.getCarrot().getIcon()),   new Image(ItemHolder.getPotato().getIcon()) },
            { new Image(ItemHolder.getPepper().getIcon()),   new Image(ItemHolder.getPotato().getIcon()) },
        };

        int i = 0;
        for(SoupSlot slot : slotArray)
        {
            slot.setID(i);

            Table requirementRow = new Table();
            requirementRow.add(requirements[i][0]).size(30, 30).padRight(4);
            requirementRow.add(requirements[i][1]).size(30, 30);

            Table slotWithRequirements = new Table();
            slotWithRequirements.add(requirementRow).padBottom(4).row();
            slotWithRequirements.add(slot).size(100, 100);

            if(i % 3 == 0 && i != 0)
            {
                lessMainTable.row();
            }
            i++;
            lessMainTable.add(slotWithRequirements).pad(50);
        }
    }

    private void setDarks()
    {
        for (int i = 0; i < slotArray.size; i++) {
            slotArray.get(i).setOverlay(!slotArray.get(i).isCraftable());
        }
    }

    private void setCraftables()
    {
        for(int i = 0;i<slotArray.size;i++)
        {
            slotArray.get(i).setCraftable(true);
        }
        if(beetrootCount < 1)
        {
            slotArray.get(0).setCraftable(false);
            slotArray.get(1).setCraftable(false);
            slotArray.get(2).setCraftable(false);
        }
        if(carrotCount < 1)
        {
            slotArray.get(0).setCraftable(false);
            slotArray.get(3).setCraftable(false);
            slotArray.get(4).setCraftable(false);
        }
        if(pepperCount < 1)
        {
            slotArray.get(1).setCraftable(false);
            slotArray.get(3).setCraftable(false);
            slotArray.get(5).setCraftable(false);
        }
        if(potatoCount < 1)
        {
            slotArray.get(2).setCraftable(false);
            slotArray.get(4).setCraftable(false);
            slotArray.get(5).setCraftable(false);
        }
    }

    public void render(float delta)
    {
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

        return new Label("0",labelStyle);
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

    private void setupDimOverlay() {

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.6f);
        pixmap.fill();

        Texture dimTexture = new Texture(pixmap);
        pixmap.dispose();

        dimOverlay = new Image(dimTexture);
        dimOverlay.setFillParent(true);

        stage.addActor(dimOverlay);
        dimOverlay.setVisible(false);

    }

    @Override
    public void responseSlotEvent(SlotEvent event) {
        updateVegetableCounts();
    }

    @Override
    public void responseGameEvent(GameEvent gameEvent) {
        if(gameEvent.getGameState().equals(GameState.SOUP))
        {
            interact();
        }
    }
}
