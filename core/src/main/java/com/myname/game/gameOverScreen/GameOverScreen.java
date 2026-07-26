package com.myname.game.gameOverScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myname.game.gameScreen.GameScreen;
import com.myname.game.gameScreen.utils.Constants;

public class GameOverScreen implements Screen {

    private Stage stage;
    private AssetManager manager;
    private Skin skin;
    private Game game;
    private float totalTime;

    public GameOverScreen(AssetManager manager, Game game,float totalTime) {
        this.manager = manager;
        this.game = game;
        this.totalTime = totalTime;
        skin = manager.get("ui/skin/flat-earth-ui.json", Skin.class);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(40* Constants.PPM, 25*Constants.PPM));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(skin.getDrawable("window-w"));
        stage.addActor(table);

        Label gameOverLabel = new Label("Soups made successfully !", skin, "title");
        Label timeLabel = new Label(formatTime(totalTime), skin);

        TextButton restartButton = new TextButton("Restart", skin);
        TextButton menuButton = new TextButton("Exit", skin);

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(manager,game));
            }
        });

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(gameOverLabel).padBottom(20).row();
        table.add(timeLabel).padBottom(40).row();
        table.add(restartButton).width(200).padBottom(20).row();
        table.add(menuButton).width(200);
    }

    private String formatTime(float seconds) {
        int minutes = (int) (seconds / 60);
        int secs = (int) (seconds % 60);
        return String.format("Time: %02d:%02d", minutes, secs);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.76862746f,0.4509804f,0,1);
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
        stage.dispose();
        skin.dispose();
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


}
