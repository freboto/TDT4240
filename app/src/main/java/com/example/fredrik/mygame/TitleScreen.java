package com.example.fredrik.mygame;



import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import sheep.audio.Audio;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.Keyboard;
import sheep.input.KeyboardListener;
import sheep.input.TouchListener;
import sheep.math.Vector2;
import sheep.gui.Container;

/**
 * Created by Fredrik on 21/01/16.
 */
public class TitleScreen extends State implements TouchListener {

    private Image aImage = new Image(R.drawable.heli1_east);
    private Image backgroundImage = new Image(R.drawable.background);

    private Sprite aSprite;
    private Sprite backSprite;

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

        // Prøver å få en sprite uten noe bilde til å strekke seg over hele skjermen, vet ikke hvordan, eller om det i det hele tatt er mulig.
        //upWall.setScale(screenSizeX, 5);
        //upWall.setPosition(screenSizeX / 2, 5);

    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);
        aSprite.draw(canvas);

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
        aSprite.update(dt);

    }


}
