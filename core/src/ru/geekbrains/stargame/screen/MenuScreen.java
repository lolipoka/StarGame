package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture background;
    private Vector2 currentPosition;
    private Vector2 newPosition;
    private Vector2 direction;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("background.png");
        currentPosition = new Vector2();
        newPosition = new Vector2();
        direction = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        drawBackground();

        /* Не знаю, как по-другому добиться того, чтобы координаты векторов совпадали.
           currentPosition.equals(newPosition) не работает.
           Просто сравнение координат x и y тоже не работает.
           Приведение x и y к int и сравнение тоже не работает.
        */
        boolean isInNewPosition = (Math.round(currentPosition.x) == Math.round(newPosition.x))
                && (Math.round(currentPosition.y) == Math.round(newPosition.y));
        if (!isInNewPosition) {
            currentPosition.add(direction);
        }
        drawLogo();

        batch.end();
    }

    private void drawBackground() {
        Color bgColor = batch.getColor();
        batch.setColor(bgColor.r, bgColor.g, bgColor.b, 1f);
        batch.draw(background, 0, 0);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        newPosition.set(screenX, Gdx.graphics.getHeight() - screenY);

        direction.set(newPosition);
        direction.sub(currentPosition);
        direction.scl(0.01f); // Чем больше разница, тем больше вектор (выше скорость).

        return false;
    }

    private void drawLogo() {
        Color imgColor = batch.getColor();
        batch.setColor(imgColor.r, imgColor.g, imgColor.b, 0.2f);
        batch.draw(img, currentPosition.x, currentPosition.y);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        background.dispose();
        super.dispose();
    }
}
