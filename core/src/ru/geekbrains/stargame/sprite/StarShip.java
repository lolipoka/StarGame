package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class StarShip extends Sprite {

    private static final float V_LEN = 0.01f;
    private static final float HEIGHT = 0.19f;
    private static final float MARGIN = 0.03f;
    public static final int TILE_WIDTH = (390 / 2);
    public static final int TILE_HEIGHT = 287;

    private Vector2 touch;
    private Vector2 v;
    private Vector2 tmp;

    public StarShip(TextureAtlas atlas) {
        /* Поскольку конструктор super() должен вызываться первым,
        нет возможности избавиться от этой цепочки вызовов методов объектов.
        Во всяком случае, мне такой способ пока неизвестен.*/
        super(atlas.findRegion("main_ship").split(TILE_WIDTH, TILE_HEIGHT)[0][0]);

        touch = new Vector2();
        v = new Vector2();
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
        setLeft(-getHalfWidth());
    }

    @Override
    public void update(float delta) {
        tmp.set(touch);
        if (tmp.sub(pos).len() < V_LEN) {
            pos.set(touch);
        } else {
            pos.add(v);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}
