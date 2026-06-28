package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemEvent;
import com.myname.game.gameScreen.event.ItemEventListener;

import static com.myname.game.gameScreen.utils.Constants.*;

public class Inventory implements ItemEventListener {

    private Array<Slot> slotArray;

    private InventoryScene scene;

    public Inventory(TextureAtlas textureAtlas)
    {
        slotArray = new Array<>(SLOT_SIZE);

        TextureRegionDrawable textureSlot = new TextureRegionDrawable(textureAtlas.findRegion("slot"));

        for(int i = 0; i < SLOT_SIZE; i++)
        {
            slotArray.add(new Slot(textureSlot));
        }

        scene = new InventoryScene(this);

        EventManager.subscribeItemEvent(this);
    }

    private void setItemToSlot(Item item)
    {
        for(Slot slot : slotArray)
        {
            if(slot.getItem() == null)
            {
                slot.setItem(item);
                return;
            }
        }
    }


    @Override
    public void responseItem(ItemEvent itemEvent) {
        Item item = itemEvent.getItem();
        this.setItemToSlot(item);
    }

    public Stage getStage() {
        return scene.getStage();
    }

    public Array<Slot> getSlotArray() {
        return slotArray;
    }

    public InventoryScene getScene() {
        return scene;
    }
}
