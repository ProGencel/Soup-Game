package com.myname.game.gameScreen.entities.player;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.gameScreen.entities.GameEntity;

public class Player extends GameEntity {

    private float width;
    private float height;
    private TextureRegion textureRegion;
    private Fixture mainFixture;
    private PlayerRenderer playerRenderer;

    public Player(TiledMap map, World world)
    {
        playerRenderer = new PlayerRenderer(this);
        position = new Vector2();
        playerRenderer.setThings(map,world);
    }

    public void render(float dt, SpriteBatch batch)
    {
        playerRenderer.render(dt,batch);
    }


    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Fixture getMainFixture() {
        return mainFixture;
    }

    public void setMainFixture(Fixture mainFixture) {
        this.mainFixture = mainFixture;
    }

    public PlayerRenderer getPlayerRenderer() {
        return playerRenderer;
    }

    public void setPlayerRenderer(PlayerRenderer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }
}
