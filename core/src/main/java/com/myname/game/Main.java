package com.myname.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myname.game.gameOverScreen.GameOverScreen;
import com.myname.game.gameScreen.GameScreen;
import com.myname.game.mainMenuScreen.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private AssetManager assetManager;
    private TmxMapLoader mapLoader;

    @Override
    public void create() {

        Gdx.app.setLogLevel(Application.LOG_DEBUG); //yayinlarken NONE yap

        mapLoader = new TmxMapLoader();

        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class,mapLoader);
        assetManager.load("World/World.tmx", TiledMap.class);
        assetManager.load("AfterAtlas/SoupGameAtlas.atlas", TextureAtlas.class);
        assetManager.load("ui/skin/flat-earth-ui.json", Skin.class);
        assetManager.finishLoading();

        //this.setScreen(new GameScreen(assetManager));
        this.setScreen(new MainMenuScreen(assetManager,this));

    }
}
