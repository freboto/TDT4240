package com.example.fredrik.mygame;

import java.util.ArrayList;

import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 26/01/16.
 */
public class Helicopter implements SpriteContainer {

    private ArrayList<Sprite> sprites;
    private int current;


    public Helicopter() {
        sprites = new ArrayList<Sprite>();
        current = 0;
    }

    public void addSprites(ArrayList<Sprite> sprites) {
        for (Sprite s : sprites) {
            sprites.add(s);
        }
    }

    @Override
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        if (sprites.contains(sprite)) {
            sprites.remove(sprite);
        }
    }
    public Sprite next() {
        Sprite previous = sprites.get(current);
        Vector2 speed = previous.getSpeed();
        Vector2 position = previous.getPosition();
        Vector2 scale = previous.getScale();
        if (current == sprites.size() - 1) {
            current = 0;
        }
        else {
            current += 1;
        }
        Sprite next = sprites.get(current);
        next.setSpeed(speed);
        next.setPosition(position);
        next.setScale(scale);
        return next;
    }
    public Sprite getCurrent() { return sprites.get(current); }
    public ArrayList<Sprite> getSprites() { return sprites; }
}
