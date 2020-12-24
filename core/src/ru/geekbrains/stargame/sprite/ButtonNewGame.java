package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.BaseButton;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.screen.GameScreen;

public class ButtonNewGame extends BaseButton {

    public static final float HEIGHT = 0.05f;
    public static final float TOP = -0.1f;

    private final Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setTop(TOP);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}