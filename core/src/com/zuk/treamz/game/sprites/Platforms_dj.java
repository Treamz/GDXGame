package com.zuk.treamz.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class Platforms_dj {


    private Texture platform;
    private Rectangle boundsPlatform;
    private Vector2 posPlatform;
    private int gap = 10;
    Random random;

    public Vector2 getPosPlatform() {
        return posPlatform;
    }

    public Texture getPlatform() {
        return platform;
    }

    public Platforms_dj(int y, float x) {
        platform = new Texture("platform_dj.png");
        int posY ;
        int posX ;
        //posY = (int)( y*10 +random(y*30))  ;
        posY = random( y+30 , y + 60);
        if(y==1){
            posY = 10;
        }
        posX = random((int)x );
        posPlatform = new Vector2(posX, posY);
        boundsPlatform = new Rectangle((int)(posPlatform.x-platform.getWidth()/3.3), posPlatform.y, platform.getWidth(), platform.getHeight());

    }
    public boolean collides(Rectangle player){
        if(player.x > boundsPlatform.x){
        return player.overlaps(boundsPlatform);
        }
        return false;
    }
    public void reposition (int y ,int score){
        int posY ;
        int posX ;
        //posY = (int)( y*10 +random(y*30))  ;
        posY = random( y+30+score/4 , y + 60+score*10);
        posX = random(200);
        posPlatform.set(posX,posY);

        boundsPlatform.set((int)(posPlatform.x-platform.getWidth()/3.3), posPlatform.y, /*platform.getWidth()-*/50, platform.getHeight());

    }

}
