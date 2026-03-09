package com.myname.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.myname.game.gameScreen.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private AssetManager assetManager;
    private TmxMapLoader mapLoader;

    @Override
    public void create() {

        mapLoader = new TmxMapLoader();

        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class,mapLoader);
        assetManager.load("World/World.tmx", TiledMap.class);
        assetManager.load("AfterAtlas/SoupGameAtlas.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        this.setScreen(new GameScreen(assetManager));

    }
}
