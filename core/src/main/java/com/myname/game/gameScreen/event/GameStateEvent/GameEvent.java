package com.myname.game.gameScreen.event.GameStateEvent;

import com.myname.game.gameScreen.stateMachines.gameState.GameState;

public class GameEvent {
    private GameState gameState;

    public GameEvent(GameState gameState) {

        this.gameState = gameState;

    }

    public GameState getGameState() {
        return gameState;
    }

}
