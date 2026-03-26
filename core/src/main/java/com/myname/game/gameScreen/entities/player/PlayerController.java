package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.myname.game.gameScreen.utils.Constants;

public class PlayerController {

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

}
