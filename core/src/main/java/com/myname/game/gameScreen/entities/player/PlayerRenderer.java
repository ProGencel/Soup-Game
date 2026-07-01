package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import common.Box2DCreator;

import static com.myname.game.gameScreen.utils.Constants.*;

public class PlayerRenderer {

    private Player player;
    private TiledMapTileMapObject mapObject;

    public PlayerRenderer(Player player)
    {
        this.player = player;
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

    public void render(SpriteBatch batch)
    {
        float playerX = player.getBody().getPosition().x - UNIT_SCALE * (float) player.getTextureRegion().getRegionWidth() / 2;
        float playerY = player.getBody().getPosition().y - UNIT_SCALE * (float) player.getTextureRegion().getRegionHeight() / 2;

        batch.draw(player.getTextureRegion(),playerX,playerY,
            player.getWidth(),player.getHeight());
    }

    public TiledMapTileMapObject getMapObject() {
        return mapObject;
    }
}
