package com.myname.game.gameScreen.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import common.Box2DCreator;

public class HolderStatics {

    private Array<StaticEntity> staticEntityArray;

    public HolderStatics(TiledMap map, World world)
    {
        staticEntityArray = new Array<>();
        setEntities(map,world);
    }

    public void draw(SpriteBatch batch)
    {
        for(StaticEntity entity : staticEntityArray)
        {
            entity.draw(batch);
        }
    }

    private void setEntities(TiledMap map, World world)
    {
        Array<TiledMapTileMapObject> array =
            Box2DCreator.findWantedTiledMapObjectsButLookingTileSetProps(map,"Objects","Static","type");

        for(TiledMapTileMapObject mapObject :  array)
        {
            StaticEntity staticEntity = new StaticEntity(mapObject,world);
            staticEntityArray.add(staticEntity);
        }
    }

}
