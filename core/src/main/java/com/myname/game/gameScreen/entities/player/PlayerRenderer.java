package com.myname.game.gameScreen.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import common.Box2DCreator;

import static com.myname.game.gameScreen.utils.Constants.UNIT_SCALE;

public class PlayerRenderer {

    private Player player;

    public PlayerRenderer(Player player)
    {
        this.player = player;
    }

    public void setThings(TiledMap map, World world)
    {
        TiledMapTileMapObject mapObject = Box2DCreator.findWantedTileMapObjectButLookingTileSetProps(map,"Objects","Player","type");

        player.setTextureRegion(mapObject.getTextureRegion());

        float y = mapObject.getY();
        y *= UNIT_SCALE;
        player.setPosition(new Vector2(mapObject.getX()*UNIT_SCALE,y));
        player.setWidth(mapObject.getTextureRegion().getRegionWidth() * UNIT_SCALE);
        player.setHeight(mapObject.getTextureRegion().getRegionHeight() * UNIT_SCALE);

        EllipseMapObject ellipseMapObject = mapObject.getTile().getObjects().getByType(EllipseMapObject.class).get(0);
        Ellipse ellipse = ellipseMapObject.getEllipse();


        player.setBody(Box2DCreator.createBody(BodyDef.BodyType.DynamicBody,world,
            new Vector2(player.getPosition().x,player.getPosition().y),new Vector2(player.getWidth(),player.getHeight())));

        setFixtures(ellipse);
    }

    private void setFixtures(Ellipse ellipse)
    {
        FixtureDef fdef = new FixtureDef();

        float eX = ellipse.x * UNIT_SCALE;
        float eY = ellipse.y * UNIT_SCALE;
        float eWidth = ellipse.width * UNIT_SCALE;
        float eHeight = ellipse.height * UNIT_SCALE;

        float centerX = eX + (eWidth / 2f) - player.getWidth()/2;
        float centerY = eY + (eHeight / 2f) - player.getHeight()/2;

        Box2DCreator.createFixture(player.getBody(),fdef, Box2DCreator.ShapeType.Ellipse,
            new Vector2(eWidth,0),new Vector2(centerX,centerY));
    }

    public void render(float dt, SpriteBatch batch)
    {
        batch.draw(player.getTextureRegion(),player.getPosition().x,player.getPosition().y,player.getWidth(),player.getHeight());
    }

}
