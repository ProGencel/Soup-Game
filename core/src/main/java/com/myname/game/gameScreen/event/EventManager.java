package com.myname.game.gameScreen.event;

import com.badlogic.gdx.utils.Array;

public class EventManager {

    public static Array<ItemEventListener> itemEventListeners = new Array<>();

    public static void subscribeItemEvent(ItemEventListener itemEventListener) {
        itemEventListeners.add(itemEventListener);
    }

    public static void fireItemEvent(ItemEvent itemEvent) {
        for (ItemEventListener itemEventListener : itemEventListeners) {
            itemEventListener.responseItem(itemEvent);
        }
    }

}
