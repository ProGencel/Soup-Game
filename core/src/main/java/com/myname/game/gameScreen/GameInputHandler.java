package com.myname.game.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;

public class GameInputHandler extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.E)
        {
            if(GameScreen.getGameState().equals(GameState.GAME))
            {
                EventManager.fireGameEvent(new GameEvent(GameState.INVENTORY));
                return true;
            }
            else if(GameScreen.getGameState().equals(GameState.INVENTORY))
            {
                EventManager.fireGameEvent(new GameEvent(GameState.GAME));
                return true;
            }
        }
        return false;
    }
}
