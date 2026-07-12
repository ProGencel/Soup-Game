package com.myname.game.gameScreen.event.SlotEvent;

import com.myname.game.gameScreen.GUI.SoupSlot;

public class SlotEvent {

    private SoupSlot soupSlot;

    public SlotEvent(SoupSlot soupSlot) {
        this.soupSlot = soupSlot;
    }

    public SoupSlot getSoupSlot() {
        return soupSlot;
    }
}
