package com.myname.game.infoScreen; // Kendi paket adinla degistirmeyi unutma

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HowToPlayScreen implements Screen {

    private final Game game;
    private final Screen previousScreen;
    private Stage stage;

    public HowToPlayScreen(Game game, Skin skin, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;

        stage = new Stage(new ScreenViewport());

        // Skin içerisindeki "title" stilini kullanarak yazıları kocaman yapıyoruz
        Label.LabelStyle hugeStyle = skin.get("title", Label.LabelStyle.class);

        // Ana tablo yerine Window kullanıyoruz (Başlığı boş bıraktık çünkü kendi büyük başlığımızı ekleyeceğiz)
        Window window = new Window("", skin, "default");
        window.setFillParent(true); // Tüm ekranı kaplasın
        window.pad(40); // Kenarlardan boşluk

        // --- ELEMANLARI OLUŞTURMA ---

        Label titleLabel = new Label("HOW TO PLAY", hugeStyle);
        titleLabel.setAlignment(Align.center);

        Label moveLabel = new Label("W A S D - Move", hugeStyle);
        moveLabel.setAlignment(Align.center);

        Label invLabel = new Label("E - Open Inventory", hugeStyle);
        invLabel.setAlignment(Align.center);

        Label interactLabel = new Label("F - Interact", hugeStyle);
        interactLabel.setAlignment(Align.center);

        // Amacımız kısmı (Ekrana sığması için setWrap kullandık)
        Label goalLabel = new Label("GOAL:\nCollect plants to brew 6 different\nsoups and serve them to customer!", hugeStyle);
        goalLabel.setWrap(true);
        goalLabel.setAlignment(Align.center);

        // Geri Dön Butonu
        TextButton backButton = new TextButton("BACK TO MENU", skin, "default");
        backButton.getLabel().setFontScale(1.2f); // Buton yazısını biraz daha belirgin yapıyoruz

        // Butona tıklama olayı
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(previousScreen);
                dispose();
            }
        });

        // --- WINDOW'A (PENCEREYE) YERLEŞTİRME ---
        // expand() ve fillX() ile ekranın dikey ve yatay boşluklarını dolduruyoruz
        window.add(titleLabel).expand().fillX().center().row();
        window.add(moveLabel).expand().fillX().center().row();
        window.add(invLabel).expand().fillX().center().row();
        window.add(interactLabel).expand().fillX().center().row();

        // Goal yazısı daha büyük bir alanı kaplasın
        window.add(goalLabel).expand().fillX().center().padTop(20).padBottom(20).row();

        // Buton boyutlandırması
        window.add(backButton).width(400).height(100).expand().center();

        // Window'u sahneye ekle
        stage.addActor(window);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Arka planı skin içindeki turuncu "window" rengine (r: 0.768, g: 0.450, b: 0) boyuyoruz
        Gdx.gl.glClearColor(0.76862746f, 0.4509804f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
    }
}
