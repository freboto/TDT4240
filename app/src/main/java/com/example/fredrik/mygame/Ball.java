package com.example.fredrik.mygame;

import java.util.Random;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 05/02/16.
 */
public class Ball extends Sprite {

    private Vector2 screenSize;

    public Ball(Image img, Vector2 screenSize) {
        super(img);
        this.screenSize = screenSize;

    }
    public void start() {
        setPosition(screenSize.getX() / 2, screenSize.getY() / 2);
        setSpeed(100, new Random().nextInt(200) - 100);
    }

}
