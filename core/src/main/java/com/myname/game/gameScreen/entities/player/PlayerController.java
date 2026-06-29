package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.myname.game.gameScreen.GameScreen;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.ItemEvent.ItemEvent;
import com.myname.game.gameScreen.inventory.Item;
import com.myname.game.gameScreen.inventory.ItemHolder;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;
import com.myname.game.gameScreen.utils.Constants;

public class PlayerController extends InputAdapter {

    private Player player;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    public void update(float dt)
    {
        Vector2 currentSpeed = new Vector2();

        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            currentSpeed.y = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            currentSpeed.y = -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            currentSpeed.x = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            currentSpeed.x = -1;
        }

        if(currentSpeed.x != 0 || currentSpeed.y != 0)
        {
           currentSpeed.nor().scl(Constants.PLAYER_SPEED);
        }

        player.getBody().setLinearVelocity(currentSpeed);

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F) {
            int vegetableId = player.getContactSystem().getNearItem();
            Item item = null;

            if(vegetableId == Constants.POTATO_FIXTURE) {
                item = ItemHolder.getPotato();
            } else if(vegetableId == Constants.BEETROOT_FIXTURE) {
                item = ItemHolder.getBeetroot();
            } else if(vegetableId == Constants.CARROT_FIXTURE) {
                item = ItemHolder.getCarrot();
            } else if(vegetableId == Constants.PEPPER_FIXTURE) {
                item = ItemHolder.getPepper();
            }

            EventManager.fireItemEvent(new ItemEvent(item));
            return true;
        }
        return false;
    }

}
