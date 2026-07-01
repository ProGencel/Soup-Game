package com.myname.game.gameScreen.event.ItemPickUpEvent;

import com.myname.game.gameScreen.entities.StaticEntity;

public class ItemPickUpEvent {

    private StaticEntity plant;

    public ItemPickUpEvent(StaticEntity plant) {
        this.plant = plant;
    }

    public StaticEntity getPlant() {
        return plant;
    }

    public void setPlant(StaticEntity plant) {
        this.plant = plant;
    }
}
