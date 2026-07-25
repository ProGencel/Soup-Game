package com.myname.game.gameScreen;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myname.game.gameScreen.entities.player.Player;
import com.myname.game.gameScreen.utils.Constants;

public class MapAndCamManager {

    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;

    public MapAndCamManager(TiledMap map, SpriteBatch batch)
    {
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map,UNIT_SCALE,batch);
        camera = new OrthographicCamera();
        viewport = new FitViewport(VISIBLE_MAP_UNIT_WIDTH,VISIBLE_MAP_UNIT_HEIGHT,camera);

        this.setCamera();
    }

    public void mapRender(float dt)
    {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void camRender(float dt)
    {
        camera.position.x = player.getBody().getPosition().x;
        camera.position.y = player.getBody().getPosition().y;
        camera.update();
    }

    private void setCamera()
    {
        camera.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
    }

    public void dispose()
    {
        tiledMapRenderer.dispose();
    }

    public void resize(int width, int height)
    {
        viewport.update(width,height);
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void createMapBounds(World world, TiledMap map) {
        float ppm = Constants.PPM;
        MapProperties props = map.getProperties();
        int mapWidthInTiles = props.get("width", Integer.class);
        int mapHeightInTiles = props.get("height", Integer.class);
        int tileWidth = props.get("tilewidth", Integer.class);
        int tileHeight = props.get("tileheight", Integer.class);

        float mapWidth = (mapWidthInTiles * tileWidth) / ppm;
        float mapHeight = (mapHeightInTiles * tileHeight) / ppm;

        float wallThickness = 1f; // world unit cinsinden, ne kadar kalın istersen

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fixtureDef.shape = shape;

        // Alt duvar
        bodyDef.position.set(mapWidth / 2f, -wallThickness / 2f);
        Body bottom = world.createBody(bodyDef);
        shape.setAsBox(mapWidth / 2f, wallThickness / 2f);
        bottom.createFixture(fixtureDef);

        // Üst duvar
        bodyDef.position.set(mapWidth / 2f, mapHeight + wallThickness / 2f);
        Body top = world.createBody(bodyDef);
        top.createFixture(fixtureDef);

        // Sol duvar
        bodyDef.position.set(-wallThickness / 2f, mapHeight / 2f);
        Body left = world.createBody(bodyDef);
        shape.setAsBox(wallThickness / 2f, mapHeight / 2f);
        left.createFixture(fixtureDef);

        // Sağ duvar
        bodyDef.position.set(mapWidth + wallThickness / 2f, mapHeight / 2f);
        Body right = world.createBody(bodyDef);
        right.createFixture(fixtureDef);

        shape.dispose();
    }

}
