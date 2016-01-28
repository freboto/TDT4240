package com.example.fredrik.mygame;

import java.util.ArrayList;
import java.util.Random;

import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by berg on 26/01/16.
 */
public class Helicopter {

    private ArrayList<Image> imgs;
    private Image current;
    private Sprite sprite;


    public Helicopter() {
        imgs = new ArrayList<Image>();
        sprite = new Sprite();
    }

    public void addImage(ArrayList<Image> imgs) {
        for (Image s : imgs) {
            this.imgs.add(s);
        }
    }

    public void addImage(Image sprite) {
        this.imgs.add(sprite);
    }

    public void removeSprite(Image img) {
        if (imgs.contains(img)) {
            imgs.remove(imgs);
        }
    }
    public void next() {
        if (imgs.indexOf(current) == imgs.size() - 1) {
            current = imgs.get(0);
        }
        else {
            current = imgs.get(imgs.indexOf(current) + 1);
        }
        sprite.setView(current);
    }

    public void start() {
        sprite.setView(imgs.get(0));
        sprite.setPosition(new Random().nextInt(300) + 30, new Random().nextInt(600) + 30);
        sprite.setSpeed(new Random().nextInt(240) - 120, new Random().nextInt(240) - 120);
    }

    public Sprite getSprite() { return sprite; }

}
