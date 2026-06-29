package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.GameStateEvent.GameEventListener;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;

public class InventoryScene implements GameEventListener {

    private Inventory inventory;

    private Stage stage;
    private Table mainTable;
    private Table slotTable;

    private Image dimOverlay;

    public InventoryScene(Inventory inventory) {
        this.inventory = inventory;

        stage = new Stage(new ScreenViewport());
        mainTable = new Table();
        slotTable = new Table();

        stage.setDebugAll(true);

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

    // Inventory sınıfında, Stage kurulumunda
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

    public void dispose() {
        stage.dispose();
    }
}
