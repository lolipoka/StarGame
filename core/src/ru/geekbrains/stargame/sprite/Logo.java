package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;

public class Logo extends Sprite {

    private Vector2 destination = new Vector2();
    private Vector2 speed = new Vector2();
    private final Vector2 move = new Vector2();

    public Logo(Texture region) {
        super(new TextureRegion(region));

        setHeightProportion(0.2f);

    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }
    public Vector2 getDestination() {
        return destination;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void move(Vector2 position) {
        move.set(destination);
        if (move.sub(position).len() > speed.len()) {
            position.add(speed);
        } else {
            position.set(destination);
        }
    }
}
