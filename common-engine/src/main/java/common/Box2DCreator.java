package common;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Box2DCreator {

    public enum ShapeType
    {
        Rectangle,
        Ellipse,
        Polygon
    }

    public static <T extends MapObject> Array<T> findWantedMapObjects(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
    {
        Array<T> array = new Array<>();

        MapLayer layer = Safe.safeLayer(map,mapLayer);

        for(T mapObject : layer.getObjects().getByType(clazz))
        {
            String wantedType = Safe.safeTiledClass(mapObject,wantedClass);
            if(wantedType.equals(wantedClass))
            {
                array.add(mapObject);
            }
        }

        return array;
    }

    public static <T extends MapObject> MapObject findWantedMapObject(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
    {
        MapLayer layer = Safe.safeLayer(map,mapLayer);

        for(MapObject mapObject : layer.getObjects().getByType(clazz))
        {
            String wantedType = Safe.safeTiledClass(mapObject,wantedClass);

            if(wantedType.equals(wantedClass))
            {
                return mapObject;
            }
        }

        throw new IllegalStateException("Object cannot find");
    }

    public static  Array<TiledMapTileMapObject> findWantedTiledMapObjectsButLookingTileSetProps(TiledMap map, String mapLayer, String targetClass, String wantedClass)
    {
        Array<TiledMapTileMapObject> array = new Array<>();

        MapLayer layer = Safe.safeLayer(map,mapLayer);

        for(TiledMapTileMapObject mapObject : layer.getObjects().getByType(TiledMapTileMapObject.class))
        {
            String wantedType = Safe.getSafeTileSetPropClass(mapObject,wantedClass);
            if(wantedType.equals(targetClass))
            {
                array.add(mapObject);
            }
        }

        return array;
    }

    public static  TiledMapTileMapObject findWantedTileMapObjectButLookingTileSetProps(TiledMap map, String mapLayer, String targetClass, String wantedClass)
    {
        MapLayer layer = Safe.safeLayer(map,mapLayer);

        for(TiledMapTileMapObject mapObject : layer.getObjects().getByType(TiledMapTileMapObject.class))
        {
            String wantedType = Safe.getSafeTileSetPropClass(mapObject,wantedClass);
            if(wantedType.equals(targetClass))
            {
                return mapObject;
            }
        }

        throw new IllegalStateException("Wanted Object cannot find on " + mapLayer);
    }

    public static void createTestBox(World world,float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);

        Body body = world.createBody(bodyDef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;
        shape.setAsBox(1,1);
        body.createFixture(fdef);

        shape.dispose();

    }

    public static Body createBody(BodyDef.BodyType bodyType, World world, Vector2 pos, Vector2 bodyMeasure)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(pos.x + bodyMeasure.x/2,pos.y + bodyMeasure.y/2);

        return world.createBody(bodyDef);
    }

    public static Fixture createFixture(Body body, FixtureDef fdef, ShapeType shapeType, Vector2 fixtureMeasure,Vector2 position)
    {

        Shape shape = null;

        switch (shapeType)
        {
            case Ellipse -> {
                shape = new CircleShape();
                CircleShape circleShape = (CircleShape) shape;
                circleShape.setRadius(fixtureMeasure.x/2);
                circleShape.setPosition(position);

                fdef.shape = circleShape;
            }
            case Rectangle -> {
                shape = new PolygonShape();
                PolygonShape polygonShape = (PolygonShape) shape;
                polygonShape.setAsBox(fixtureMeasure.x/2,fixtureMeasure.y/2,new Vector2(position.x,position.y),0);

                fdef.shape = polygonShape;
            }
        }

        assert shape != null : "The shape is null";
        return body.createFixture(fdef);

    }

}
