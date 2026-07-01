package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.gameScreen.entities.GameEntity;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.systems.ContactSystem;
import com.myname.game.gameScreen.utils.Constants;

public class Player extends GameEntity {

    private float width;
    private float height;
    private TextureRegion textureRegion;
    private Fixture mainFixture;
    private PlayerRenderer playerRenderer;
    private PlayerController playerController;
    private ContactSystem contactSystem;
    private int userNumberData;

    private StaticEntity target = null;

    public Player(TiledMap map, World world, ContactSystem contactSystem)
    {
        playerRenderer = new PlayerRenderer(this);
        playerController = new PlayerController(this);
        position = new Vector2();
        playerRenderer.setThings(map,world);
        this.contactSystem = contactSystem;
    }



    public void update(float dt)
    {
        sortY = body.getPosition().y - ((float)textureRegion.getRegionHeight()/2 * Constants.UNIT_SCALE) +
            playerRenderer.getMapObject().getTile().getProperties().get("OFFSET_Y",float.class) * Constants.UNIT_SCALE;
        playerController.update(dt);
    }

    public void interactWithTarget()
    {
        if(target != null)
        {
            target.interact();
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        playerRenderer.render(batch);
    }


    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }

    public int getUserNumberData() {
        return userNumberData;
    }

    public void setUserNumberData(int userNumberData) {
        this.userNumberData = userNumberData;
    }

    public ContactSystem getContactSystem() {
        return contactSystem;
    }

    public StaticEntity getTarget() {
        return target;
    }

    public void setTarget(StaticEntity target) {
        this.target = target;
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

    public PlayerController getPlayerController() {
        return playerController;
    }
}
