package com.example.fredrik.mygame;



import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

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
    private Image wallVerImage2 = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.background);
    private Sprite westWall;
    private Sprite eastWall;
    private Sprite backSprite;
    private Helicopter heliContainer;
    private int screenSizeX;
    private int screenSizeY;


    public TitleScreen(Activity activity) {
        this.activity = activity;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenSizeX = displayMetrics.widthPixels;
        screenSizeY = displayMetrics.heightPixels;

        backSprite = new Sprite(backgroundImage);
        Sprite heli1 = new Sprite(I1);
        Sprite heli2 = new Sprite(I2);
        Sprite heli3 = new Sprite(I3);
        Sprite heli4 = new Sprite(I4);

        heliContainer = new Helicopter();
        heliContainer.addSprite(heli1);
        heliContainer.addSprite(heli2);
        heliContainer.addSprite(heli3);
        heliContainer.addSprite(heli4);

        heliContainer.getCurrent().setPosition(screenSizeX/2, screenSizeY/2);
        heliContainer.getCurrent().setSpeed(40, 0);
        heliContainer.getCurrent().setScale(1, 1);

        westWall = new Sprite(wallVerImage);
        westWall.setPosition(4, 220);

        eastWall = new Sprite(wallVerImage2);
        eastWall.setPosition(476, 220);

        startTimer();
    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);
        heliContainer.getCurrent().draw(canvas);
        westWall.draw(canvas);
        eastWall.draw(canvas);
    }

    public void update(float dt) {
        Sprite current = heliContainer.getCurrent();
        if(current.collides(eastWall))
        {
            System.out.println("crash east border!");
            current.setSpeed(-current.getSpeed().getX(), current.getSpeed().getY());
            current.setScale(-1, 1);
        }

        else if(current.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            current.setSpeed(-current.getSpeed().getX(), current.getSpeed().getY());
            current.setScale(1, 1);
        }

        westWall.update(dt);
        eastWall.update(dt);
        current.update(dt);
    }

    private void startTimer() {
        Timer timer = new Timer ();
        TimerTask switchHeli = new TimerTask () {
            @Override
            public void run () {
                heliContainer.next();
            }
        };
        timer.schedule(switchHeli, 0l, 100);
    }
}