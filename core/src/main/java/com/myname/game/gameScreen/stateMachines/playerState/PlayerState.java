package com.myname.game.gameScreen.stateMachines.playerState;

import com.myname.game.gameScreen.entities.player.Player;

public enum PlayerState implements PlayerStateInt{
    WALK {
        @Override
        public void enter(Player player) {
            player.getPlayerRenderer().setAnimation(this);
        }

        @Override
        public void exit(Player player) {

        }

        @Override
        public void update(Player player) {

        }
    },
    IDLE {
        @Override
        public void enter(Player player) {
            player.getPlayerRenderer().setAnimation(this);
        }

        @Override
        public void exit(Player player) {

        }

        @Override
        public void update(Player player) {

        }
    }

}
