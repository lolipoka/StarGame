package ru.geekbrains.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.sprite.Bullet;

public abstract class Ship extends Sprite {

    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected Vector2 v;
    protected Vector2 v0;

    protected Rect worldBounds;

    protected float reloadInterval;
    private float reloadTimer;

    private final BulletPool bulletPool;

    public Ship(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            shoot();
        }
    }

    protected void shoot() {
        bulletSound.play(0.5f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
        reloadTimer = 0f;
    }
}
