package com.myname.game.gameScreen.stateMachines.playerState;

import com.myname.game.gameScreen.entities.player.Player;

public interface PlayerStateInt {

    void enter(Player player);
    void exit(Player player);
    void update(Player player);
}
