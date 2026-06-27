package com.myname.game.gameScreen.inventory;

public class Slot {

    private Item item;
    private int stackAmount = 0;

    public Item getItem() {
        return item;
    }

    public int getStackAmount() {
        return stackAmount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void increaseStackAmount() {
        this.stackAmount++;
    }
}
