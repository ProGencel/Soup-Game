package com.myname.game.gameScreen.GUI.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.myname.game.gameScreen.GUI.Slot;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GenericEvent.GenericEvent;
import com.myname.game.gameScreen.event.GenericEvent.GenericEventListener;
import com.myname.game.gameScreen.event.ItemEvent.ItemEvent;
import com.myname.game.gameScreen.event.ItemEvent.ItemEventListener;
import com.myname.game.gameScreen.event.SlotEvent.SlotEvent;
import com.myname.game.gameScreen.event.SlotEvent.SlotEventListener;

import static com.myname.game.gameScreen.utils.Constants.*;

public class Inventory implements ItemEventListener, SlotEventListener, GenericEventListener {

    private Array<Slot> slotArray;

    private InventoryScene scene;

    private int soup0 = 0;
    private int soup1 = 0;
    private int soup2 = 0;
    private int soup3 = 0;
    private int soup4 = 0;
    private int soup5 = 0;

    public Inventory(TextureAtlas textureAtlas)
    {
        slotArray = new Array<>(SLOT_SIZE);

        TextureRegionDrawable textureSlot = new TextureRegionDrawable(textureAtlas.findRegion("slot"));

        for(int i = 0; i < SLOT_SIZE; i++)
        {
            slotArray.add(new Slot(textureSlot));
        }

        scene = new InventoryScene(this,textureAtlas);

        EventManager.subscribeItemEvent(this);
        EventManager.subscribeSlotEvent(this);
        EventManager.subscribeGenericEvent(this);
    }

    private void setItemToSlot(Item item)
    {
        for(Slot slot : slotArray)
        {
            if(slot.getItem() != null && slot.getItem().getID() == item.getID())
            {
                slot.setItem(item);
                return;
            }
        }

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
        if(item != null)
        {
            this.setItemToSlot(item);
        }
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

    @Override
    public void responseSlotEvent(SlotEvent event) {

        int soupID = event.getSoupSlot().getID();

        int firstItemID = -1;
        int secondItemID = -1;

        switch (soupID) {
            case 0:
                firstItemID = ItemHolder.getCarrot().getID();
                secondItemID = ItemHolder.getBeetroot().getID();
                soup0++;
                break;

            case 1:
                firstItemID = ItemHolder.getBeetroot().getID();
                secondItemID = ItemHolder.getPepper().getID();
                soup1++;
                break;

            case 2:
                firstItemID = ItemHolder.getBeetroot().getID();
                secondItemID = ItemHolder.getPotato().getID();
                soup2++;
                break;

            case 3:
                firstItemID = ItemHolder.getCarrot().getID();
                secondItemID = ItemHolder.getPepper().getID();
                soup3++;
                break;

            case 4:
                firstItemID = ItemHolder.getCarrot().getID();
                secondItemID = ItemHolder.getPotato().getID();
                soup4++;
                break;

            case 5:
                firstItemID = ItemHolder.getPepper().getID();
                secondItemID = ItemHolder.getPotato().getID();
                soup5++;
                break;

            default:
                return;
        }

        for (Slot slot : slotArray) {
            if (slot.getItem() != null && slot.getItem().getID() == firstItemID) {
                slot.decreaseItem();
                break;
            }
        }

        for (Slot slot : slotArray) {
            if (slot.getItem() != null && slot.getItem().getID() == secondItemID) {
                slot.decreaseItem();
                break;
            }
        }

        Item item = switch (soupID) {
            case 0 -> ItemHolder.getBeetCarrSoup();
            case 1 -> ItemHolder.getBeetPeppSoup();
            case 2 -> ItemHolder.getBeetPotatoSoup();
            case 3 -> ItemHolder.getCarrPeppSoup();
            case 4 -> ItemHolder.getCarrPotatoSoup();
            case 5 -> ItemHolder.getPeppPotatoSoup();
            default -> null;
        };

        if(item != null)
        {
            setItemToSlot(item);
        }
    }

    @Override
    public void responseGenericEvent(GenericEvent event) {
        if(event.getEventName().equals("SAMURAI"))
        {
            Gdx.app.log("Inventory","Event name :" + event.getEventName());
            if(soup0 > 0 && soup1 > 0 && soup2 > 0 && soup3 > 0 && soup4 > 0 && soup5 > 0)
            {Gdx.app.log("Inventory","soups done");
                EventManager.fireGenericEvent(new GenericEvent("GAME_OVER"));
            }
        }
    }
}
