package com.example.fredrik.mygame;



import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.Random;

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

    private Image wallHorImage = new Image(R.drawable.wall_horizontal);

    private Sprite northWall;
    private Sprite southWall;

    private Ball ball;
    private Paddle player;
    private Paddle cpu;

    private int screenSizeX;
    private int screenSizeY;

    private TextButton points;

    private int cpuPoints = 0;
    private int playerPoints = 0;


    public TitleScreen(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenSizeX = displayMetrics.widthPixels;
        screenSizeY = displayMetrics.heightPixels;

        northWall = new Sprite(wallHorImage);
        northWall.setPosition(screenSizeX/2, 5);

        southWall = new Sprite(wallHorImage);
        southWall.setPosition(screenSizeX / 2, 440);

        points = new TextButton(screenSizeX / 2, 50, "0  :  0");

        ball = new Ball(new Image(R.drawable.ball), new Vector2(screenSizeX, screenSizeY));
        player = new Paddle(new Image(R.drawable.paddle), true, new Vector2(screenSizeX, screenSizeY));
        cpu = new Paddle(new Image(R.drawable.paddle), false, new Vector2(screenSizeX, screenSizeY));
        ball.start();

        northWall.update(0);
        southWall.update(0);
        ball.update(0);
        player.update(0);
        cpu.update(0);
    }
    @Override
    public boolean onTouchMove(MotionEvent event) {
        if(event.getY() > player.getPosition().getY()) {
            player.setSpeed(0, 80);
        }
        else if (event.getY() < player.getPosition().getY()) {
            player.setSpeed(0, -80);
        }
        return true;
    }

    public void draw(android.graphics.Canvas canvas){
        canvas.drawColor(Color.BLACK);
        northWall.draw(canvas);
        southWall.draw(canvas);
        ball.draw(canvas);
        player.draw(canvas);
        cpu.draw(canvas);
        points.draw(canvas);
    }

    public void update(float dt) {
        ball.update(dt);
        player.update(dt);
        cpu.update(dt);
        aiMakeMove();
        if (ball.collides(cpu)) {
            ball.setPosition(ball.getPosition().getX() - 10, ball.getPosition().getY());
            ball.setSpeed(- ball.getSpeed().getX() - 30, ball.getSpeed().getY());
        }
        else if (ball.collides(player)) {
            ball.setPosition(ball.getPosition().getX() + 10, ball.getPosition().getY());
            ball.setSpeed(- ball.getSpeed().getX() + 30 , ball.getSpeed().getY());
        }
        else if (ball.collides(northWall)) {
            ball.setPosition(ball.getPosition().getX(), ball.getPosition().getY() + 10);
            ball.setSpeed(ball.getSpeed().getX(), - ball.getSpeed().getY() + 30);
        }
        else if (ball.collides(southWall)) {
            ball.setPosition(ball.getPosition().getX(), ball.getPosition().getY() - 10);
            ball.setSpeed(ball.getSpeed().getX(), - ball.getSpeed().getY() - 30);
        }
        if (player.collides(northWall) || player.collides(southWall)) {
            player.setSpeed(0, 0);
        }
        if (ball.getPosition().getX() < 0) {
            win("cpu");
        }
        else if (ball.getPosition().getX() > screenSizeX) {
            win("player");
        }

    }
    private void win(String player) {
        if (player == "cpu") {
            cpuPoints += 1;
            points.setLabel(Integer.toString(playerPoints) + "  :  " + Integer.toString(cpuPoints));
        }
        else {
            playerPoints += 1;
            points.setLabel(Integer.toString(playerPoints) + "  :  " + Integer.toString(cpuPoints));
        }
        restart();
    }
    private void aiMakeMove() {
        float ballPos = ball.getPosition().getY();
        if (ballPos > cpu.getPosition().getY()) {
            cpu.setSpeed(0, 200);
        }
        else {
            cpu.setSpeed(0, -200);
        }
    }
    public void restart() {
        ball.start();
    }
}