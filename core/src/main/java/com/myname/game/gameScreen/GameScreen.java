package com.myname.game.gameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.myname.game.gameScreen.entities.HolderStatics;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.entities.player.Player;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.GameStateEvent.GameEvent;
import com.myname.game.gameScreen.event.GameStateEvent.GameEventListener;
import com.myname.game.gameScreen.inventory.Inventory;
import com.myname.game.gameScreen.stateMachines.gameState.GameState;
import com.myname.game.gameScreen.systems.ContactSystem;
import com.myname.game.gameScreen.systems.RenderSystem;

public class GameScreen implements Screen, GameEventListener {

    private MapAndCamManager manager;
    private TiledMap map;
    private SpriteBatch batch;
    private PhysicWorld world;
    private RenderSystem renderSystem;
    private ContactSystem contactSystem;

    private Player player;
    private HolderStatics holderStatics;

    private Inventory inventory;

    private InputMultiplexer inputMultiplexer;

    private static GameState gameState = GameState.GAME;

    public GameScreen(AssetManager assetManager)
    {

        EventManager.subscribeGameEvent(this);

        inventory = new Inventory(assetManager.get("AfterAtlas/SoupGameAtlas.atlas"));
        inputMultiplexer = new InputMultiplexer();

        map = assetManager.get("World/World.tmx");
        batch = new SpriteBatch();
        renderSystem = new RenderSystem();
        manager = new MapAndCamManager(map,batch);
        world = new PhysicWorld(manager);
        contactSystem = new ContactSystem(world.getWorld());

        holderStatics = new HolderStatics(map,world.getWorld());
        player = new Player(map,world.getWorld(),contactSystem);

        manager.setPlayer(player);

        setInputMultis();

        this.addEntities();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(gameState.equals(GameState.GAME))
        {
            world.updatePhysic(delta);
        }

        ScreenUtils.clear(Color.BLACK);

        manager.camRender(delta);
        manager.mapRender(delta);

        if(gameState.equals(GameState.GAME))
        {
            player.update(delta);
        }

        batch.setProjectionMatrix(manager.getCamera().combined);
        batch.begin();
        renderSystem.draw(batch);
        batch.end();

        world.render();

        inventory.getScene().render(delta);

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
        inventory.getScene().resize(width, height);
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
        inventory.getScene().dispose();
    }

    public static GameState getGameState() {
        return gameState;
    }

    private void setInputMultis()
    {
        inputMultiplexer.addProcessor(new GameInputHandler());
        inputMultiplexer.addProcessor(inventory.getStage());
        inputMultiplexer.addProcessor(player.getPlayerController());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void responseGameEvent(GameEvent gameEvent) {
        gameState = gameEvent.getGameState();
    }
}
