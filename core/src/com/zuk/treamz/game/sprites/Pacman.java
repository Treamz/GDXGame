package com.zuk.treamz.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pacman {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Animation pacmanAnimation;
    private TiledMapTileLayer collisionLayer;
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    private int direction=0;

    public int getNewdirection() {
        return newdirection;
    }

    public void setNewdirection(int newdirection) {
        this.newdirection = newdirection;
    }

    private  int newdirection=0;
    private  int colisionside = 0;

    public TextureRegion getTexture(){
        return pacmanAnimation.getFrame();
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    private Rectangle bounds;

    public Pacman(TiledMapTileLayer layer) {
      collisionLayer = layer;
        texture = new Texture("pacmann.png");
        position = new Vector2(256,80);
        velocity = new Vector2(); velocity.add(0, 0);
        pacmanAnimation = new Animation(new TextureRegion(texture), 3, 2f);
        bounds = new Rectangle(256, 80, 32, 16);
    }
    public void update(float dt){
        pacmanAnimation.update(dt);

        boolean collisionX = false, collisionY = false;
        float oldX = getPosition().x, oldY = getPosition().y, tileWidth = this.collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        velocity.scl(dt);
        if(true) {
            position.add((velocity.x *dt), velocity.y*dt);

        }

        velocity.scl(1 / dt);
        updateBounds();
        if(direction == 1) {velocity.set(0,2000); Gdx.app.log("UP", String.valueOf("up"));}
        if(direction == 2) {velocity.set(2000,0);Gdx.app.log("R", String.valueOf("R"));}
        if(direction == 3) {velocity.set(0,-2000);Gdx.app.log("D", String.valueOf("D"));}
        if(direction == 4) {velocity.set(-2000,0);Gdx.app.log("L", String.valueOf("L"));}
//        switch (direction) {
//            case (1):
//                velocity.set(0,300);
//                break;
//            case (2):
//                velocity.set(300,0);
//                break;
//            case (3):
//                velocity.set(0,-300);
//                break;
//            case (4):
//                velocity.set(-300,0);
//                break;
//
//            default:
//                velocity.set(400,0);
//                break;
//        }
        if(velocity.y > 0) {
            collisionY = collisionLayer.getCell((int)( (getPosition().x +1))/ (int) tileWidth, (int) (getPosition().y +15) / (int) tileHeight).getTile()
                    .getProperties().containsKey("blocked");
            if(!collisionY){
                collisionY = collisionLayer.getCell((int)( (getPosition().x+15) )/ (int) tileWidth, (int) (getPosition().y +15) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked");

            }
            if(collisionY){
            colisionside = 1;}
        }
        if (velocity.y < 0) {
            collisionY = collisionLayer.getCell(((int) (getPosition().x+1 )/ (int) tileWidth), (int) ((getPosition().y+1) / (int) tileWidth)).getTile()
                    .getProperties().containsKey("blocked");
            if(!collisionY){
                collisionY = collisionLayer.getCell(((int) (getPosition().x +15)/ (int) tileWidth), (int) ((getPosition().y +1)/ (int) tileWidth)).getTile()
                        .getProperties().containsKey("blocked");

            }
            if(collisionY){
                colisionside = 3;}
        }
        if(velocity.x > 0) {
            collisionX = collisionLayer.getCell(((int) getPosition().x +15) / (int) tileWidth, (int) (getPosition().y+1 ) / (int) tileWidth).getTile()
                    .getProperties().containsKey("blocked");
            if (!collisionX){
                collisionX = collisionLayer.getCell(((int) getPosition().x +15) / (int) tileWidth, (int) (getPosition().y +15) / (int) tileWidth).getTile()
                        .getProperties().containsKey("blocked");

            }
            if(collisionX){
                colisionside=2;
            }
        }
        if (velocity.x < 0) {
            collisionX = collisionLayer.getCell(((int) (getPosition().x +1)/ (int) tileWidth), (int) ((getPosition().y +1)/ (int) tileWidth)).getTile()
                    .getProperties().containsKey("blocked");
            if (!collisionX){
                collisionX = collisionLayer.getCell(((int) (getPosition().x +1)/ (int) tileWidth), (int) ((getPosition().y+15)/ (int) tileWidth)).getTile()
                        .getProperties().containsKey("blocked");
            }
            if(collisionX){
                colisionside=4;
            }
        }
//        Gdx.app.log("Collisionx", String.valueOf(collisionX));
//        Gdx.app.log("CollisionY", String.valueOf(collisionY));
//        Gdx.app.log("CollisionSide", String.valueOf(colisionside));
//
//        Gdx.app.log("Collisionx", String.valueOf(position.x));
//        Gdx.app.log("CollisionY", String.valueOf(position.y));

//            if(!collisionX){
//                collisionX = collisionLayer.getCell((int) getPosition().x / (int)tileWidth, ((int) getPosition().y / (int)tileHeight / 2) / (int) tileHeight).getTile()
//                        .getProperties().containsKey("blocked"); Gdx.app.log("Collision2", String.valueOf(collisionX));}
//
//            if(!collisionX){
//                collisionX = collisionLayer.getCell((int) getPosition().x / (int)tileWidth, ((int) getPosition().y / (int) tileHeight)).getTile()
//                        .getProperties().containsKey("blocked");Gdx.app.log("Collision3", String.valueOf(collisionX));
//            }
//       // }

//         if(velocity.x > 0) {
//            collisionX = collisionLayer.getCell( ((int)getPosition().x + (int)getTexture().getWidth()) / (int)tileWidth, (int) (getPosition().y + getTexture().getHeight()) / (int)tileWidth).getTile()
//                    .getProperties().containsKey("blocked");
//            if(collisionX) {
//                collisionX = collisionLayer.getCell(((int) getTexture().getWidth() + (int) getPosition().x)/ (int) tileWidth,  ((int) getPosition().y + (int) getTexture().getWidth() / 2)/ (int)tileHeight).getTile()
//                        .getProperties().containsKey("blocked");
//            }
//
//            if(collisionX) {
//                collisionX = collisionLayer.getCell(((int) getTexture().getWidth() + (int) getPosition().x)/ (int) tileWidth,  (int) getPosition().y / (int)tileHeight).getTile()
//                        .getProperties().containsKey("blocked");
//            }
//        }

        //if (velocity.y < 0) {
//            collisionY = collisionLayer.getCell(((int) getPosition().x / (int)tileWidth), (int) (getPosition().y / (int)tileWidth)).getTile()
//                    .getProperties().containsKey("blocked");

//            if (collisionY) {
//                collisionY = collisionLayer.getCell(((int) (getPosition().x + getTexture().getWidth() / 2 ) / (int)tileWidth), (int) (getPosition().y / (int)tileHeight)).getTile()
//                        .getProperties().containsKey("blocked");
//            }
//            if (collisionY) {
//                collisionY = collisionLayer.getCell(((int) getPosition().x / (int)tileWidth), (int) (getPosition().y / (int)tileWidth)).getTile()
//                        .getProperties().containsKey("blocked");
//            }


       // }

       // if(velocity.y > 0) {
//            collisionY = collisionLayer.getCell(((int) getPosition().x / (int)tileWidth), (int) ((getPosition().y + getTexture().getHeight() / 2) / (int)tileHeight)).getTile()
//                    .getProperties().containsKey("blocked");
//
//            if (collisionY) {
//                collisionY = collisionLayer.getCell(((int) (getPosition().x + getTexture().getWidth() / 2 ) / ((int)tileWidth)), (int) (getPosition().y + getTexture().getHeight() / 2) / (int)tileHeight).getTile()
//                        .getProperties().containsKey("blocked");
//            }
//            if (collisionY) {
//                collisionY = collisionLayer.getCell(((int) (getPosition().x + getTexture().getWidth() ) / ((int)tileWidth)), (int) (getPosition().y + getTexture().getHeight()) / (int)tileHeight).getTile()
//                        .getProperties().containsKey("blocked");
//            }
        //}

        if(collisionX) {

            if(position.y%16>8&&position.y!=0){
                position.y = position.y +(16 -position.y%16);
            }
            if(position.y%16<8){
                position.y = position.y -position.y%16;
            }
            if(position.x%16>8&&position.x!=0){
                position.x = position.x +(16 -position.x%16);
            }
            if(position.x%16<8){
                position.x = position.x -position.x%16;
            }


                direction=0;
                velocity.x=0;
                //position.set(oldX, position.y);



        }
        if(collisionY) {
            if(position.x%16>8&&position.x!=0){
                position.x = position.x +(16 -position.x%16);
            }
            if(position.x%16<8){
                position.x = position.x -position.x%16;
            }
            if(position.y%16>8&&position.y!=0){
                position.y = position.y +(16 -position.y%16);
            }
            if(position.y%16<8){
                position.y = position.y -position.y%16;
            }

                direction=0;
                velocity.y=0;
                //position.set(position.x, position.y);



        }
        if(!collisionX|!collisionY){
            if(newdirection==1){
                if(  !collisionLayer.getCell((int)( (getPosition().x+15 ))/ (int) tileWidth, (int) (getPosition().y +17) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked")&&(!collisionLayer.getCell((int)( (getPosition().x+1))/ (int) tileWidth, (int) (getPosition().y +17) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked"))){
                    direction=newdirection;
                }


            }
            if(newdirection==3){
                if(  !collisionLayer.getCell((int)( (getPosition().x+15 ))/ (int) tileWidth, (int) (getPosition().y -3) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked")&&( !collisionLayer.getCell((int)( (getPosition().x+1 ))/ (int) tileWidth, (int) (getPosition().y -3) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked"))){
                    direction=newdirection;
                }

            }
            if(newdirection==2){
                if(  !collisionLayer.getCell((int)( (getPosition().x+20 ))/ (int) tileWidth, (int) (getPosition().y +1) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked")&(!collisionLayer.getCell((int)( (getPosition().x+20 ))/ (int) tileWidth, (int) (getPosition().y +15) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked"))){
                    direction=newdirection;
                }

            }
            if(newdirection==4){
                if(  !collisionLayer.getCell((int)( (getPosition().x-10 ))/ (int) tileWidth, (int) (getPosition().y +1) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked")&(!collisionLayer.getCell((int)( (getPosition().x-10 ))/ (int) tileWidth, (int) (getPosition().y +15) / (int) tileHeight).getTile()
                        .getProperties().containsKey("blocked"))){
                    direction=newdirection;
                }

            }
           // direction= newdirection;
        }

    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void updateBounds(){
        bounds.setPosition(position.x-16, position.y);
    }

}
