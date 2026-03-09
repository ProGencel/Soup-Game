package com.myname.game.gameScreen.entities;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import common.*;


public class StaticEntity extends GameEntity{

    private float width, height;
    private TextureRegion texture;
    private Fixture fixture;

    public StaticEntity(TiledMapTileMapObject mapObject, World world)
    {
        setMosOfTheThings(mapObject);
        setBody(world);
        setHitboxes(mapObject);
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(texture,position.x,position.y,width,height);
    }

    private void setHitboxes(TiledMapTileMapObject mapObject)
    {
        for(RectangleMapObject rectangleMapObject : mapObject.getTile().getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = new Rectangle(rectangleMapObject.getRectangle());

            float rX = rec.x * UNIT_SCALE;
            float rY = rec.y * UNIT_SCALE;
            float rWidth = rec.width * UNIT_SCALE;
            float rHeight = rec.height * UNIT_SCALE;

            float centerX = (rX + (rWidth / 2f)) - body.getPosition().x;
            float centerY = (rY + (rHeight / 2f)) - body.getPosition().y;

            FixtureDef fdef = new FixtureDef();
            fixture = Box2DCreator.createFixture(body, fdef, Box2DCreator.ShapeType.Rectangle,
                new Vector2(rWidth, rHeight), new Vector2(centerX, centerY));
        }
    }

    private void setMosOfTheThings(TiledMapTileMapObject mapObject)
    {
        position = new Vector2();
        texture = mapObject.getTextureRegion();
        position.x = mapObject.getX() * UNIT_SCALE;
        position.y = mapObject.getY() * UNIT_SCALE;
        width = texture.getRegionWidth() * UNIT_SCALE;
        height = texture.getRegionHeight() * UNIT_SCALE;
    }

    private void setBody(World world)
    {
        body = Box2DCreator.createBody(BodyDef.BodyType.StaticBody,world,position,new Vector2(width,height));
    }

}
