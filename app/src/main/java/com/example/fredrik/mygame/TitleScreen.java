package com.example.fredrik.mygame;



import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

/**
 * Created by Fredrik on 21/01/16.
 */
public class TitleScreen extends State {

    private Activity activity;
    private Image I1 = new Image(R.drawable.chopper1);
    private Image I2 = new Image(R.drawable.chopper2);
    private Image I3 = new Image(R.drawable.chopper3);
    private Image I4 = new Image(R.drawable.chopper4);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image wallHorImage = new Image(R.drawable.wall_horizontal);
    private Image backgroundImage = new Image(R.drawable.background);

    private Sprite northWall;
    private Sprite southWall;
    private Sprite westWall;
    private Sprite eastWall;
    private Sprite backSprite;

    private Helicopter heliContainer;
    private Helicopter heliContainer2;

    private long time;

    private int screenSizeX;
    private int screenSizeY;


    public TitleScreen(Activity activity) {
        this.activity = activity;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenSizeX = displayMetrics.widthPixels;
        screenSizeY = displayMetrics.heightPixels;
        System.out.println(screenSizeX);
        System.out.println(screenSizeY);

        backSprite = new Sprite(backgroundImage);

        heliContainer = new Helicopter();
        heliContainer.addImage(I1);
        heliContainer.addImage(I2);
        heliContainer.addImage(I3);
        heliContainer.addImage(I4);
        heliContainer.start();

        heliContainer2 = new Helicopter();
        heliContainer2.addImage(I1);
        heliContainer2.addImage(I2);
        heliContainer2.addImage(I3);
        heliContainer2.addImage(I4);
        heliContainer2.start();

        westWall = new Sprite(wallVerImage);
        westWall.setPosition(5, screenSizeY/2);

        eastWall = new Sprite(wallVerImage);
        eastWall.setPosition(475, screenSizeY/2);

        northWall = new Sprite(wallHorImage);
        northWall.setPosition(screenSizeX/2, 5);

        southWall = new Sprite(wallHorImage);
        southWall.setPosition(screenSizeX/2, 760);

        westWall.update(0);
        eastWall.update(0);
        northWall.update(0);
        southWall.update(0);
        heliContainer.getSprite().update(0);
        heliContainer2.getSprite().update(0);

        time = System.currentTimeMillis();
    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);
        heliContainer.getSprite().draw(canvas);
        heliContainer2.getSprite().draw(canvas);
        westWall.draw(canvas);
        eastWall.draw(canvas);
        northWall.draw(canvas);
        southWall.draw(canvas);
    }

    public void update(float dt) {

        if (heliContainer.getSprite().collides(eastWall)) {
            heliContainer.getSprite().setSpeed(-heliContainer.getSprite().getSpeed().getX(), heliContainer.getSprite().getSpeed().getY());
            heliContainer.getSprite().setScale(-1, 1);
        }
        else if (heliContainer.getSprite().collides(westWall)) {
            heliContainer.getSprite().setSpeed(-heliContainer.getSprite().getSpeed().getX(), heliContainer.getSprite().getSpeed().getY());
            heliContainer.getSprite().setScale(1, 1);
        }
        else if (heliContainer.getSprite().collides(northWall) || heliContainer.getSprite().collides(southWall)) {
            heliContainer.getSprite().setSpeed(heliContainer.getSprite().getSpeed().getX(), -heliContainer.getSprite().getSpeed().getY());
        }
        if (heliContainer2.getSprite().collides(eastWall)) {
            heliContainer2.getSprite().setSpeed(-heliContainer2.getSprite().getSpeed().getX(), heliContainer2.getSprite().getSpeed().getY());
            heliContainer2.getSprite().setScale(-1, 1);
        }
        else if (heliContainer2.getSprite().collides(westWall)) {
            heliContainer2.getSprite().setSpeed(-heliContainer2.getSprite().getSpeed().getX(), heliContainer2.getSprite().getSpeed().getY());
            heliContainer2.getSprite().setScale(1, 1);
        }
        else if (heliContainer2.getSprite().collides(northWall) || heliContainer2.getSprite().collides(southWall)) {
            heliContainer2.getSprite().setSpeed(heliContainer2.getSprite().getSpeed().getX(), -heliContainer2.getSprite().getSpeed().getY());
        }

        heliContainer.getSprite().update(dt);
        heliContainer2.getSprite().update(dt);

        if (System.currentTimeMillis() - time >= 100) {
            heliContainer.next();
            heliContainer2.next();
            time = System.currentTimeMillis();
        }
    }
}