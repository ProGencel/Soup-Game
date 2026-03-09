package com.myname.game.gameScreen;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapAndCamManager {

    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

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

}
