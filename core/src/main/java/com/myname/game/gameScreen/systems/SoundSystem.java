package com.myname.game.gameScreen.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class SoundSystem {

    private AssetManager manager;
    private Sound walk;
    private Sound pickUp;

    public SoundSystem(AssetManager manager) {
        this.manager = manager;
        walk = manager.get("Sounds/walk.wav", Sound.class);
        pickUp = manager.get("Sounds/pickup.wav",Sound.class);
    }

    public void playWalk()
    {
        walk.play();
    }

    public void playPick()
    {
        pickUp.play();
    }

    public void dispose()
    {
        walk.dispose();
        pickUp.dispose();
    }

}
