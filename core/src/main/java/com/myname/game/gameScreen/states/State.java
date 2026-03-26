package com.myname.game.gameScreen.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface State {

    enum enumState
    {
        WALK,
        RUN
    }

    enumState getEnumState();

    Animation<TextureRegion> getAnimation();

    public void exit();

    public void enter();

}
