package com.myname.game.gameScreen.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEvent;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEventListener;

public class SoundSystem implements ItemPickUpEventListener {

    private final Sound walk;
    private final Sound pickUp;

    public SoundSystem(AssetManager manager) {
        walk = manager.get("Sounds/walk.wav", Sound.class);
        pickUp = manager.get("Sounds/pickup.wav",Sound.class);

        EventManager.subscribeItemEvent(this);
    }

    public void playWalk()
    {
        walk.play();
    }

    public void playPick()
    {
        pickUp.play(10);
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
}
