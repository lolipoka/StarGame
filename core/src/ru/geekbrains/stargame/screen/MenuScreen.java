package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.02f;

    private Texture bg;
    private Background background;

    private Texture img;
    private Logo logo;

    private Vector2 pos;

    @Override
    public void show() {
        super.show();

        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        img = new Texture("badlogic.jpg");
        logo = new Logo(img);

        pos = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        logo.move(pos);

        Gdx.gl.glClearColor(0.55f, 0.23f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        background.draw(batch);

        logo.pos.set(pos);
        logo.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.setDestination(touch);
        logo.setSpeed(logo.getDestination().cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
