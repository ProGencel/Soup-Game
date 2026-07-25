package com.myname.game.mainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window; // Window import edildi
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.myname.game.gameScreen.GameScreen;
import com.myname.game.gameScreen.utils.Constants;
import com.myname.game.infoScreen.HowToPlayScreen;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private Game game;
    private AssetManager manager;

    public MainMenuScreen(AssetManager manager, Game game)
    {
        this.game = game;
        this.manager = manager;
        skin = manager.get("ui/skin/flat-earth-ui.json", Skin.class);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(40 * Constants.PPM, 25 * Constants.PPM));

        Gdx.input.setInputProcessor(stage);

        // Table yerine HowToPlayScreen'deki gibi Window kullanıyoruz
        Window window = new Window("", skin, "default");
        window.setFillParent(true);
        window.pad(40); // İstersen buradaki padding'i kendi zevkine göre ayarlayabilirsin
        stage.addActor(window);

        Label titleLabel = new Label("SOUP GAME", skin, "title");
        titleLabel.setAlignment(Align.center);

        TextButton playButton = new TextButton("Play", skin);
        TextButton howToPlayButton = new TextButton("How To Play", skin); // How To Play butonu eklendi
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(manager, game));
            }
        });

        // How To Play ekranına geçiş
        howToPlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // this anahtar kelimesi ile mevcut ekranı (MainMenuScreen) referans olarak gönderiyoruz
                game.setScreen(new HowToPlayScreen(game, skin, MainMenuScreen.this));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Butonları Window'a (Pencereye) yerleştiriyoruz
        window.add(titleLabel).padBottom(80).row();
        window.add(playButton).width(220).padBottom(20).row();
        window.add(howToPlayButton).width(220).padBottom(20).row(); // Araya eklendi
        window.add(exitButton).width(220);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.76862746f, 0.4509804f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        if(stage != null) stage.dispose();
        // skin.dispose(); // Eğer AssetManager kullanıyorsan skin'i burada dispose etmene gerek yok, manager üzerinden yönetmelisin.
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}
