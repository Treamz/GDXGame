package com.zuk.treamz.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PacGoods {

    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;
    private Boolean isAlive;

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Boolean getAlive() {
        return isAlive;
    }
    public boolean collides(Rectangle player){
        if(player.x > bounds.x){
            return player.overlaps(bounds);
        }
        return false;
    }

    public PacGoods(int x, int y) {
        texture = new Texture("pacgoods.png");
        position = new Vector2(x,y);
        bounds = new Rectangle(x-16,y,16,16);
        isAlive = true;
    }
}
