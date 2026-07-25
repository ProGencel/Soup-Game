package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.myname.game.gameScreen.stateMachines.playerState.PlayerState;
import com.myname.game.gameScreen.utils.Constants;
import common.Box2DCreator;

import static com.myname.game.gameScreen.utils.Constants.*;

public class PlayerRenderer {

    private final Player player;
    private TiledMapTileMapObject mapObject;
    private float stateTime = 0f;

    private Array<TextureAtlas.AtlasRegion> rightIdle;
    private Array<TextureAtlas.AtlasRegion> leftIdle ;
    private Array<TextureAtlas.AtlasRegion> upIdle;
    private Array<TextureAtlas.AtlasRegion> downIdle;
    private Array<TextureAtlas.AtlasRegion> rightWalk;
    private Array<TextureAtlas.AtlasRegion> leftWalk;
    private Array<TextureAtlas.AtlasRegion> upWalk;
    private Array<TextureAtlas.AtlasRegion> downWalk;

    private Animation<TextureRegion> rightIdleA;
    private Animation<TextureRegion> leftIdleA ;
    private Animation<TextureRegion> upIdleA;
    private Animation<TextureRegion> downIdleA;
    private Animation<TextureRegion> rightWalkA;
    private Animation<TextureRegion> leftWalkA;
    private Animation<TextureRegion> upWalkA;
    private Animation<TextureRegion> downWalkA;

    private Animation<TextureRegion> playerAnimation;


    public PlayerRenderer(Player player)
    {
        this.player = player;
        TextureAtlas atlas = player.getAssetManager().get("AfterAtlas/SoupGameAtlas.atlas");

        rightIdle = atlas.findRegions("idle_right");
        leftIdle = atlas.findRegions("idle_left");
        upIdle = atlas.findRegions("idle_up");
        downIdle = atlas.findRegions("idle_down");
        rightWalk = atlas.findRegions("run_right");
        leftWalk = atlas.findRegions("run_left");
        upWalk = atlas.findRegions("run_up");
        downWalk = atlas.findRegions("run_down");


        rightIdleA = new Animation<>(IDLE_ANIMATION_DURATION, rightIdle, Animation.PlayMode.LOOP);
        leftIdleA  = new Animation<>(IDLE_ANIMATION_DURATION, leftIdle, Animation.PlayMode.LOOP);
        upIdleA    = new Animation<>(IDLE_ANIMATION_DURATION, upIdle, Animation.PlayMode.LOOP);
        downIdleA  = new Animation<>(IDLE_ANIMATION_DURATION, downIdle, Animation.PlayMode.LOOP);
        playerAnimation = rightIdleA;
        rightWalkA = new Animation<>(WALK_ANIMATION_DURATION, rightWalk, Animation.PlayMode.LOOP);
        leftWalkA  = new Animation<>(WALK_ANIMATION_DURATION, leftWalk, Animation.PlayMode.LOOP);
        upWalkA    = new Animation<>(WALK_ANIMATION_DURATION, upWalk, Animation.PlayMode.LOOP);
        downWalkA  = new Animation<>(WALK_ANIMATION_DURATION, downWalk, Animation.PlayMode.LOOP);
    }

    public void setThings(TiledMap map, World world)
    {
        mapObject = Box2DCreator.findWantedTileMapObjectButLookingTileSetProps(map,"Objects","Player","type");

        player.setTextureRegion(mapObject.getTextureRegion());

        float y = mapObject.getY() * UNIT_SCALE;
        float x = mapObject.getX() * UNIT_SCALE;

        player.setPosition(new Vector2(x,y));
        player.setWidth(mapObject.getTextureRegion().getRegionWidth() * UNIT_SCALE);
        player.setHeight(mapObject.getTextureRegion().getRegionHeight() * UNIT_SCALE);

        EllipseMapObject ellipseMapObject = mapObject.getTile().getObjects().getByType(EllipseMapObject.class).get(0);
        Ellipse ellipse = ellipseMapObject.getEllipse();

        EllipseMapObject ellipseMapObjectSensor = mapObject.getTile().getObjects().getByType(EllipseMapObject.class).get(1);
        Ellipse ellipseSensor = ellipseMapObjectSensor.getEllipse();


        player.setBody(Box2DCreator.createBody(BodyDef.BodyType.DynamicBody,world,
            new Vector2(player.getPosition().x,player.getPosition().y),new Vector2(player.getWidth(),player.getHeight())));

        setFixtures(ellipse,ellipseSensor);
    }

    private void setFixtures(Ellipse ellipse,Ellipse ellipseSensor)
    {
        FixtureDef fdef = new FixtureDef();

        float eX = ellipse.x * UNIT_SCALE;
        float eY = ellipse.y * UNIT_SCALE;
        float eWidth = ellipse.width * UNIT_SCALE;
        float eHeight = ellipse.height * UNIT_SCALE;

        float centerX = eX + (eWidth / 2f) - player.getWidth()/2;
        float centerY = eY + (eHeight / 2f) - player.getHeight()/2;

        Fixture mainFixture = Box2DCreator.createFixture(player.getBody(),fdef, Box2DCreator.ShapeType.Ellipse,
            new Vector2(eWidth,0),new Vector2(centerX,centerY));
        mainFixture.setUserData(PLAYER_FIXTURE);

        FixtureDef fdef1 = new FixtureDef();
        fdef1.isSensor = true;

        Fixture sensorFixture = Box2DCreator.createFixture(player.getBody(), fdef1, Box2DCreator.ShapeType.Ellipse,
            new Vector2(ellipseSensor.width * UNIT_SCALE,0),new Vector2(ellipseSensor.x,ellipseSensor.y));
        sensorFixture.setUserData(player);
        player.setUserNumberData(PLAYER_SENSOR_FIXTURE);

    }

    public void setAnimation(PlayerState playerState)
    {
        switch (player.getDirection())
        {
            case RIGHT -> {
                playerAnimation = playerState.equals(PlayerState.WALK) ? rightWalkA : rightIdleA;
            }
            case LEFT -> {
                playerAnimation = playerState.equals(PlayerState.WALK) ? leftWalkA : leftIdleA;
            }
            case UP -> {
                playerAnimation = playerState.equals(PlayerState.WALK) ? upWalkA : upIdleA;
            }
            case DOWN -> {
                playerAnimation = playerState.equals(PlayerState.WALK) ? downWalkA : downIdleA;
            }
        }
        player.setLastPlayerState(playerState);
    }


    public void render(SpriteBatch batch)
    {
        stateTime += Gdx.graphics.getDeltaTime();

        float playerX = player.getBody().getPosition().x - UNIT_SCALE * (float) player. getTextureRegion().getRegionWidth() / 2;
        float playerY = player.getBody().getPosition().y - UNIT_SCALE * (float) player.getTextureRegion().getRegionHeight() / 2;

//        int mapWidthInTiles = player.getMap().getProperties().get("width", Integer.class);
//        int mapHeightInTiles = player.getMap().getProperties().get("height", Integer.class);
//
//        playerX = MathUtils.clamp(playerX,0,mapWidthInTiles - player.getWidth());
//        playerY = MathUtils.clamp(playerY,0,mapHeightInTiles - player.getHeight());

        TextureRegion textureRegion = playerAnimation.getKeyFrame(stateTime,true);

        batch.draw(textureRegion,playerX,playerY,
            player.getWidth(),player.getHeight());
    }

    public TiledMapTileMapObject getMapObject() {
        return mapObject;
    }
}
