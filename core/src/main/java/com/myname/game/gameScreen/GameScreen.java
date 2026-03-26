package com.myname.game.gameScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.myname.game.gameScreen.entities.HolderStatics;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.entities.player.Player;
import com.myname.game.gameScreen.systems.RenderSystem;

public class GameScreen implements Screen {

    private MapAndCamManager manager;
    private TiledMap map;
    private SpriteBatch batch;
    private PhysicWorld world;
    private RenderSystem renderSystem;

    private Player player;
    private HolderStatics holderStatics;

    public GameScreen(AssetManager assetManager)
    {
        map = assetManager.get("World/World.tmx");
        batch = new SpriteBatch();
        renderSystem = new RenderSystem();
        manager = new MapAndCamManager(map,batch);
        world = new PhysicWorld(manager);

        holderStatics = new HolderStatics(map,world.getWorld());
        player = new Player(map,world.getWorld());

        manager.setPlayer(player);

        this.addEntities();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        world.updatePhysic(delta);
        ScreenUtils.clear(Color.BLACK);

        manager.camRender(delta);
        manager.mapRender(delta);

        player.update(delta);

        batch.setProjectionMatrix(manager.getCamera().combined);
        batch.begin();

        renderSystem.draw(batch);

        batch.end();

        world.render();
    }

    private void addEntities()
    {
        renderSystem.addGameEntity(player);

        for(StaticEntity staticEntity : holderStatics.getStaticEntities())
        {
            renderSystem.addGameEntity(staticEntity);
        }

    }

    @Override
    public void resize(int width, int height) {
        manager.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        manager.dispose();
        world.dispose();
    }
}
