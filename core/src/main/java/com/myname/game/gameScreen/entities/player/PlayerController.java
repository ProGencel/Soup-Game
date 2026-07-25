package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GenericEvent.GenericEvent;
import com.myname.game.gameScreen.event.ItemEvent.ItemEvent;
import com.myname.game.gameScreen.GUI.inventory.Item;
import com.myname.game.gameScreen.GUI.inventory.ItemHolder;
import com.myname.game.gameScreen.stateMachines.playerState.Direction;
import com.myname.game.gameScreen.stateMachines.playerState.PlayerState;
import com.myname.game.gameScreen.utils.Constants;

public class PlayerController extends InputAdapter {

    private Player player;
    private float soundTimer = 0f;

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
            playWalkSound(dt);
            player.setDirection(Direction.UP);
            player.getPlayerRenderer().setAnimation(player.getLastPlayerState());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            currentSpeed.y = -1;
            playWalkSound(dt);
            player.setDirection(Direction.DOWN);
            player.getPlayerRenderer().setAnimation(player.getLastPlayerState());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            currentSpeed.x = 1;
            playWalkSound(dt);
            player.setDirection(Direction.RIGHT);
            player.getPlayerRenderer().setAnimation(player.getLastPlayerState());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            currentSpeed.x = -1;
            playWalkSound(dt);
            player.setDirection(Direction.LEFT);
            player.getPlayerRenderer().setAnimation(player.getLastPlayerState());
        }

        if(currentSpeed.x != 0 || currentSpeed.y != 0)
        {
           currentSpeed.nor().scl(Constants.PLAYER_SPEED);
           player.setLastPlayerState(PlayerState.WALK);
        }
        else
        {
            player.setLastPlayerState(PlayerState.IDLE);
            player.getPlayerRenderer().setAnimation(player.getLastPlayerState());
        }

        player.getBody().setLinearVelocity(currentSpeed);

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F) {
            int vegetableId = player.getContactSystem().getNearItem();
            if(vegetableId == -1) {
                System.out.println("asda");
                return false;
            }
            Item item = null;

            if(vegetableId == Constants.POTATO_FIXTURE) {
                item = ItemHolder.getPotato();
                EventManager.fireItemEvent(new ItemEvent(item));
            }
            else if(vegetableId == Constants.BEETROOT_FIXTURE) {
                item = ItemHolder.getBeetroot();
                EventManager.fireItemEvent(new ItemEvent(item));
            }
            else if(vegetableId == Constants.CARROT_FIXTURE) {
                item = ItemHolder.getCarrot();
                EventManager.fireItemEvent(new ItemEvent(item));
            }
            else if(vegetableId == Constants.PEPPER_FIXTURE) {
                item = ItemHolder.getPepper();
                EventManager.fireItemEvent(new ItemEvent(item));
            }


            player.interactWithTarget();

            return true;
        }
        return false;
    }

    private void playWalkSound(float dt)
    {
        soundTimer += dt;
        if(soundTimer >= 0.23f)
        {
            player.getSoundSystem().playWalk();
            soundTimer = 0f;
        }
    }

}
