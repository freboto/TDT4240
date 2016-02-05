package com.example.fredrik.mygame;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 05/02/16.
 */
public class Paddle extends Sprite {

    boolean player;
    Vector2 screenSize;

    public Paddle(Image img, boolean player, Vector2 screenSize) {
        super(img);
        this.player = player;
        this.screenSize = screenSize;
        if (player) {
            setPosition(5, 120);
        }
        else {
            setPosition(screenSize.getX() - 5, 120);
        }
    }
}
