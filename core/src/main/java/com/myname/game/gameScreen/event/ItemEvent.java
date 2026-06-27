package com.myname.game.gameScreen.event;

import com.myname.game.gameScreen.inventory.Item;

public class ItemEvent {

    private Item item;

    public ItemEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
