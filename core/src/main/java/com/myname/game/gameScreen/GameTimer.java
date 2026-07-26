package com.myname.game.gameScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameTimer {

    private final Stage stage;
    private final Table table;
    private final Label timeLabel;

    private float elapsedTime = 0f;
    private boolean running = false;

    public GameTimer(Skin skin) {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setFillParent(true);
        table.top().left(); // sol üst köşe
        table.pad(10);

        timeLabel = new Label("00:00", skin);
        table.add(timeLabel);

        stage.addActor(table);
    }

    public void start() {
        running = true;
        elapsedTime = 0f;
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        elapsedTime = 0f;
        updateLabel();
    }

    public void update(float delta) {
        if (running) {
            elapsedTime += delta;
            updateLabel();
        }
    }

    private void updateLabel() {
        int minutes = (int) (elapsedTime / 60);
        int seconds = (int) (elapsedTime % 60);
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void render() {
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }
}
