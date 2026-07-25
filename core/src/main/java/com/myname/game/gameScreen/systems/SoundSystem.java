package com.myname.game.gameScreen.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEvent;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEventListener;
import com.myname.game.gameScreen.event.SlotEvent.SlotEvent;
import com.myname.game.gameScreen.event.SlotEvent.SlotEventListener;

public class SoundSystem implements ItemPickUpEventListener, SlotEventListener {

    private final Sound walk;
    private final Sound pickUp;

    public SoundSystem(AssetManager manager) {
        walk = manager.get("Sounds/walk.wav", Sound.class);
        pickUp = manager.get("Sounds/pickup.wav",Sound.class);

        EventManager.subscribeItemEvent(this);
        EventManager.subscribeSlotEvent(this);
    }

    public void playWalk()
    {
        walk.play(0.1f);
    }

    public void playPick()
    {
        pickUp.play(0.3f);
    }

    public void dispose()
    {
        walk.dispose();
        pickUp.dispose();
    }

    @Override
    public void responseToItemPickUpEvent(ItemPickUpEvent event) {
        playPick();
    }

    @Override
    public void responseSlotEvent(SlotEvent event) {
        playPick();
    }
}
