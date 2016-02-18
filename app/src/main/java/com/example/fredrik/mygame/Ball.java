package com.example.fredrik.mygame;

import java.util.Random;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 05/02/16.
 */
public class Ball extends Sprite {

    private int state = 0; //Visible = 0, invisible = 1
    private static Image img;
    private static Ball instance = null;

    public static Ball getInstance() {
        if (Ball.instance == null) {
            img = new Image(R.drawable.ball);
            Ball.instance = new Ball();
        }
        return Ball.instance;
    }
    private Ball() {

        super(img);
    }
    public void start() {
        setPosition(200 / 2, 200 / 2);
        setSpeed(100, new Random().nextInt(200) - 100);
    }
    public void update(float dt) {
        super.update(dt);
        if (state == 0) {
            setView(img);
        }
        else {
            setView(null);
        }
    }
    public void switchState() {
        if (state == 0) {
            state = 1;
        }
        else {
            state = 0;
        }
    }
    public void setState(int state) {
        this.state = state;
    }
}
