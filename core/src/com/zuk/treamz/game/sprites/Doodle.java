package com.zuk.treamz.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Doodle {
    private Vector3 position;
    private Vector3 velocity;

    public boolean isGoDown() {
        if(velocity.y<0){
            return true;
        }
        else {
            return false;
        }
        
    }

    private Vector3 velocity_1;
    private Texture texture;

    public Rectangle getBounds() {
        return bounds;
    }
    

    private Rectangle bounds;
    private int MOVEMENT = 0;



    private static final int GRAVITY = -10;

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0);
        velocity_1.add(0, GRAVITY, 0);
        //float dt1= dt;
        velocity_1.scl(dt);
        velocity.scl(dt);
        if(true) {
            position.add((velocity_1.x * 30*dt), velocity.y, 0);

        }

        velocity.scl(1 / dt);
        updateBounds();
    }

    public Doodle(int x, int y){
        position = new Vector3(x, y, 0);
        texture = new Texture("doodle.png");
        velocity = new Vector3(0, 0, 0);
        velocity_1 = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }
    public void goLeft(){
        velocity_1.x = -500;
    }
    public void goRight(){
       velocity_1.x = 500;
    }
    public void jump(){
        velocity.y = 300;
    }

    public void updateBounds(){
        bounds.setPosition(position.x, position.y);
    }

}
