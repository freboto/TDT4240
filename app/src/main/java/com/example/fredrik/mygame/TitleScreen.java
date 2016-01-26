package com.example.fredrik.mygame;



import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;
import sheep.math.Vector2;

/**
 * Created by Fredrik on 21/01/16.
 */
public class TitleScreen extends State implements TouchListener {

    private Image aImage = new Image(R.drawable.heli1_east);
    private Image backgroundImage = new Image(R.drawable.background);

    private Sprite aSprite;
    private Sprite backSprite;
    private TextButton coords;

    private Activity activity;
    private int screenSizeX;
    private int screenSizeY;
    private float heliX = 0;
    private float heliY = 0;


    public TitleScreen(Activity activity) {
        this.activity = activity;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenSizeX = displayMetrics.widthPixels;
        screenSizeY = displayMetrics.heightPixels;
        System.out.println(screenSizeX);
        System.out.println(screenSizeY);


        backSprite = new Sprite(backgroundImage);
        aSprite = new Sprite(aImage);

        aSprite.setPosition(120, 120);
        aSprite.setSpeed(0, 0);

        coords = new TextButton(20, 20, getTextCoords(aSprite.getX(), aSprite.getY()));
    }

    private String getTextCoords(float x, float y) {
        return Float.toString(Math.round(x)) + " : " + Float.toString(Math.round(y));
    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);
        aSprite.draw(canvas);
        coords.draw(canvas);
    }

    public boolean onTouchMove(MotionEvent event) {
        float clickX = event.getX();
        float clickY = event.getY();
        Vector2 heliPosition = aSprite.getPosition();
        heliX = heliPosition.getX();
        heliY = heliPosition.getY();

        float x = clickX - heliX;
        float y = clickY - heliY;
        float xSpeed = 0;
        float ySpeed = 0;
        if (x != 0) {
            aSprite.setXSpeed(x / 2);
        }
        if (y != 0) {
            aSprite.setYSpeed(y / 2);
        }
        System.out.println(heliY);
        return false;
    }

    public void update(float dt) {
        //if (aSprite.collides(upWall)) {
            //aSprite.setSpeed(0, 0);
        //    System.out.println("Collision");
        //}
        heliX = aSprite.getX();
        heliY = aSprite.getY();
        if (aSprite.getSpeed().getX() < 0) {
            aSprite.setScale(-1, 1);
        }
        else {
            aSprite.setScale(1, 1);
        }
        if (heliX <= 0 || heliX >= screenSizeX || heliY <= 0 || heliY >= screenSizeY) {
            aSprite.setPosition(120, 120);
        }
        coords.setLabel(getTextCoords(aSprite.getX(), aSprite.getY()));
        aSprite.update(dt);

    }


}
