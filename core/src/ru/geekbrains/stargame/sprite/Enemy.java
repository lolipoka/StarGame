package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;

public class Enemy extends Ship {

    private boolean shotOnArrival = false;
    private boolean warped = false;
    private final Vector2 warpSpeed = new Vector2();

    public Enemy(BulletPool bulletPool, Rect worldBounds) {
        super(bulletPool);
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        warpSpeed.set(0, - delta * 0.5f);
        warp();
        shootOnArrival();
        bulletPos.set(pos.x, pos.y - getHalfHeight());
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    private void warp() {
        if (getTop() > worldBounds.getTop()) {
            pos.add(warpSpeed);
        } else  {
            if (!warped) warped = true;
        }
    }

    private void shootOnArrival() {
        if (warped && !shotOnArrival) {
            shoot();
            shotOnArrival = true;
        }
    }

    public void set(
            TextureRegion[] regions,
            TextureRegion bulletRegion,
            Sound bulletSound,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            Vector2 v0,
            float height
    ) {
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.bulletSound = bulletSound;
        this.bulletHeight = bulletHeight;
        this.bulletV = bulletV;
        this.damage = damage;
        this.hp = hp;
        this.reloadInterval = reloadInterval;
        this.v.set(v0);
        setHeightProportion(height);
    }
}
