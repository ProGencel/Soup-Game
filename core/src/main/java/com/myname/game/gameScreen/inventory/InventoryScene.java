package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myname.game.gameScreen.utils.Constants;

public class InventoryScene {

    private Inventory inventory;

    private Stage stage;
    private Table mainTable;
    private Table slotTable;

    public InventoryScene(Inventory inventory) {
        this.inventory = inventory;

        stage = new Stage(new ScreenViewport());
        mainTable = new Table();
        slotTable = new Table();

        stage.setDebugAll(true);
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

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
}
