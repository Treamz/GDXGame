package com.zuk.treamz.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PacmanMap {
    private Array<Texture> wbTexture;

    public Array<Texture> getWbTexture() {
        return wbTexture;
    }

    public Array<Vector2> getPosition() {
        return position;
    }
    private Array<Rectangle> boundsWall;

    private Array<Vector2> position;


    public PacmanMap() {
        wbTexture =  new Array<Texture>();
        position = new Array<Vector2>();
        boundsWall = new Array<Rectangle>();

//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(50,50));
//        boundsWall.add(new Rectangle(50,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(150,50));
//        boundsWall.add(new Rectangle(150,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(200,50));
//        boundsWall.add(new Rectangle(200,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(250,50));
//        boundsWall.add(new Rectangle(250,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(300,50));
//        boundsWall.add(new Rectangle(300,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(350,50));
//        boundsWall.add(new Rectangle(350,50, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(400,50));
//        boundsWall.add(new Rectangle(400,50, 50,50));
//
//        //
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(50,150));
//        boundsWall.add(new Rectangle(50,150, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(100,150));
//        boundsWall.add(new Rectangle(100,150, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(150,150));
//        boundsWall.add(new Rectangle(150,150, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(200,150));
//        boundsWall.add(new Rectangle(200,150, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(250,150));
//        boundsWall.add(new Rectangle(250,150, 50,50));
//
//        wbTexture.add(new Texture("WallBlock.png"));
//        position.add(new Vector2(300,150));
//        boundsWall.add(new Rectangle(300,150, 50,50));




    }
    public boolean collides(Rectangle player){
        Gdx.app.log(String.valueOf(boundsWall.size),"123");
        boolean bool= false;
        for(Rectangle boundsWalll : boundsWall) {
           // if (player.getX() < boundsWalll.getX()) {
             //   return true;
            //}
            //Gdx.app.log(String.valueOf(boundsWalll.getX() + "--" + boundsWalll.getY()),"Plater" + player.getX() + "--" + player.getY() );
            if(player.overlaps(boundsWalll)){
                //Gdx.app.log("tru","uuy");
                bool = true;

            }
        }
        return bool;
    }
}
