package com.example.fredrik.mygame;

import java.util.Random;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 05/02/16.
 */
public class Ball extends Sprite {

    private static Ball instance = null;

    public static Ball getInstance() {
        if (Ball.instance == null) {
            Ball.instance = new Ball();
        }
        return Ball.instance;
    }
    private Ball() {
        super(new Image(R.drawable.ball));
    }
    public void start() {
        setPosition(200 / 2, 200 / 2);
        setSpeed(100, new Random().nextInt(200) - 100);
    }
}
