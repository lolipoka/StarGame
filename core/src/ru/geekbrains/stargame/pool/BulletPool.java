package ru.geekbrains.stargame.pool;
import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    public Bullet newObject() {
        return new Bullet();
    }
}
