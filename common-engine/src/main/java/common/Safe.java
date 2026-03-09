package common;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;

public class Safe {

    public static MapLayer safeLayer(TiledMap map, String layerName)
    {
        MapLayer layer = map.getLayers().get(layerName);
        if(layer == null)
        {
            throw new IllegalStateException(layerName+" cannot find");
        }
        else
        {
            return layer;
        }
    }

    public static String safeTiledClass(MapObject mapObject, String wantedClass)
    {
        if(mapObject.getProperties().get("type", String.class) == null)
        {
            throw new IllegalStateException(wantedClass+" cannot find");
        }
        else
        {
            return mapObject.getProperties().get("type", String.class);
        }
    }

    public static String getSafeTileSetPropClass(TiledMapTileMapObject mapObject, String wantedClass)
    {
        if(mapObject.getTile().getProperties().get("type", String.class) == null)
        {
            throw new IllegalStateException(wantedClass+" cannot find");
        }
        else
        {
            return mapObject.getTile().getProperties().get(wantedClass, String.class);
        }
    }


}
