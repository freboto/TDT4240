package com.example.fredrik.mygame;



import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

/**
 * Created by Fredrik on 21/01/16.
 */
public class TitleScreen extends State {

    private Image aImage = new Image(R.drawable.heli1_east);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image wallVerImage2 = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.background);
    private Sprite aSprite;
    private Sprite westWall;
    private Sprite eastWall;
    private Sprite backSprite;


    public TitleScreen() {
        backSprite = new Sprite(backgroundImage);
        aSprite = new Sprite(aImage);

        westWall = new Sprite(wallVerImage);
        westWall.setPosition(4, 220);

        eastWall = new Sprite(wallVerImage2);
        eastWall.setPosition(476, 220);

        aSprite.setPosition(200, 120);
        aSprite.setSpeed(-40, 0); // it should move right direction, but since collides bug, it will move (-40,0),  If we input (-40,0), it move (40,0), after collides, helicopter is disappeared. bug?

    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);

        westWall.draw(canvas);
        eastWall.draw(canvas);
        aSprite.draw(canvas);
    }

    public void update(float dt) {

        if(aSprite.collides(eastWall))
        {
            System.out.println("crash east border!");
            aSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
            aSprite.setScale(-1, 1);
        }

        else if(aSprite.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            aSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
            aSprite.setScale(1, 1);
        }

        westWall.update(dt);
        eastWall.update(dt);
        aSprite.update(dt);

    }


}
