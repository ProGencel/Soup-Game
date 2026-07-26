package com.myname.game.gameScreen.GUI.inventory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.GameStateEvent.GameEventListener;
import com.myname.game.gameScreen.event.GenericEvent.GenericEvent;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;

public class InventoryScene implements GameEventListener {

    private final Inventory inventory;
    private final Stage stage;
    private final Skin skin;

    private final Table mainTable;
    private final Table slotTable;

    private TextButton returnToMenuButton;

    private Image dimOverlay;

    public InventoryScene(Inventory inventory, TextureAtlas textureAtlas,Skin skin) {
        this.inventory = inventory;
        ItemHolder itemHolder = new ItemHolder(textureAtlas); // bu konuyu arastir daha adam gibi tasarim yap

        this.skin = skin;

        stage = new Stage(new ScreenViewport());
        mainTable = new Table();
        slotTable = new Table();

        //stage.setDebugAll(true);

        setupDimOverlay();
        setMainTable();

        EventManager.subscribeGameEvent(this);
    }

    private void setMainTable()
    {
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        mainTable.setVisible(false);
        mainTable.setTouchable(Touchable.disabled);
        setSlotTable();
        setReturnButton();
    }

    private void setSlotTable()
    {
        for(int i = 0; i < inventory.getSlotArray().size; i++)
        {
            slotTable.add(inventory.getSlotArray().get(i)).size(100).padBottom(250);
        }

        mainTable.add(slotTable);
    }

    private void onEverything()
    {
        mainTable.setVisible(true);
        mainTable.setTouchable(Touchable.enabled);
        dimOverlay.setVisible(true);
    }

    private void offEverything()
    {
        mainTable.setVisible(false);
        mainTable.setTouchable(Touchable.disabled);
        dimOverlay.setVisible(false);
    }

    public Stage getStage() {
        return stage;
    }

    public void render(float delta)
    {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void responseGameEvent(GameEvent gameEvent) {
        if(gameEvent.getGameState().equals(GameState.GAME))
        {
            offEverything();
        }
        if(gameEvent.getGameState().equals(GameState.INVENTORY))
        {
            onEverything();
        }
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

    private void setReturnButton()
    {
        returnToMenuButton = new TextButton("Ana Menuye Don", skin);
        returnToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EventManager.fireGenericEvent(new GenericEvent("MAIN_MENU"));
            }
        });

        mainTable.row();
        mainTable.add(returnToMenuButton).padTop(20);
    }

    public void dispose() {
        stage.dispose();
    }
}
