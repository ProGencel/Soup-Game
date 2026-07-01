package com.myname.game.gameScreen.event;

import com.badlogic.gdx.utils.Array;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.GameStateEvent.GameEventListener;
import com.myname.game.gameScreen.event.ItemEvent.ItemEvent;
import com.myname.game.gameScreen.event.ItemEvent.ItemEventListener;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEvent;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEventListener;

public class EventManager {

    public static Array<ItemEventListener> itemEventListeners = new Array<>();
    public static Array<GameEventListener> gameEventListeners = new Array<>();
    public static Array<ItemPickUpEventListener> itemPickUpEventListeners = new Array<>();

    public static void subscribeItemEvent(ItemEventListener itemEventListener) {
        itemEventListeners.add(itemEventListener);
    }

    public static void fireItemEvent(ItemEvent itemEvent) {
        for (ItemEventListener itemEventListener : itemEventListeners) {
            itemEventListener.responseItem(itemEvent);
        }
    }

    public static void subscribeItemEvent(ItemPickUpEventListener itemEventListener) {
        itemPickUpEventListeners.add(itemEventListener);
    }

    public static void fireItemPickUpEvent(ItemPickUpEvent itemEvent) {
        for (ItemPickUpEventListener itemEventListener : itemPickUpEventListeners) {
            itemEventListener.responseToItemPickUpEvent(itemEvent);
        }
    }

    public static void subscribeGameEvent(GameEventListener gameEventListener) {
        gameEventListeners.add(gameEventListener);
    }

    public static void fireGameEvent(GameEvent gameEvent) {
        for (GameEventListener gameEventListener : gameEventListeners) {
            gameEventListener.responseGameEvent(gameEvent);
        }
    }

}
