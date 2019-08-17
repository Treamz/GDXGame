package com.zuk.treamz.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.zuk.treamz.game.MainGameGdx;
import com.zuk.treamz.game.sprites.Bird;
import com.zuk.treamz.game.sprites.Doodle;
import com.zuk.treamz.game.sprites.Platforms_dj;
import com.zuk.treamz.game.sprites.Score;
import com.zuk.treamz.game.sprites.Tube;

public class Doodlejump extends State {
    private BitmapFont text;
    private Texture gameoverImg;
    private Array<Platforms_dj> platforms;
    private Texture background;
    private Doodle doodle;
    private boolean gameover;
    private Score score;

    public Doodlejump(GameStateManager gsm,int scorer) {
        super(gsm);
        score = new Score();
        score.setScore(scorer);
        doodle = new Doodle(40, 200);


        background = new Texture("bg.png");
        gameoverImg = new Texture("gameover.png");
        text = new BitmapFont();
        platforms = new Array<Platforms_dj>();
        for(int i = 1; i <= 20; i++) {
            if(i==1){
                platforms.add(new Platforms_dj(i, MainGameGdx.WIDTH));
            }
            platforms.add(new Platforms_dj((int)platforms.get(i-1).getPosPlatform().y, 240-platforms.get(i-1).getPlatform().getWidth() / 2));

        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            if(gameover) {
                score.setScore(0);
                gsm.set(new Doodlejump(gsm,0));
            }
            if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2){
                doodle.goLeft();
                //doodle.jump();
            } else {
                //doodle.jump();
                doodle.goRight();
            }

        }

    }

    @Override
    public void update(float dt) {
        Gdx.app.log("GameScreen FPS", (1/dt) + "");

        if(((score.getScore()+1)%4)==0){
            int score1 = score.getScore();
            gsm.set(new PlayState(gsm,score1+1));
        }
        if(doodle.getPosition().y>camera.position.y) {
            score.addScoreDoodle((int)doodle.getPosition().y);
            camera.position.set(camera.viewportWidth / 2, doodle.getPosition().y, 0);

        }
        for(int i = 1; i <= 20; i++) {
            if(platforms.get(i).getPosPlatform().y<( camera.position.y -220)){
                platforms.get(i).reposition(maxPlatformY(),score.getScore());
            }
        }
        if(doodle.getPosition().y<( camera.position.y -220)){
           gameover = true;
        }
        handleInput();
        doodle.update(dt);
        for(Platforms_dj platform : platforms) {
            if(doodle.isGoDown()) {
                if (platform.collides(doodle.getBounds())) {
                    doodle.jump();
                }
            }
        }
        if(doodle.getPosition().x < - doodle.getTexture().getWidth()){
            doodle.setPosition(new Vector3(camera.viewportWidth + doodle.getTexture().getWidth() ,doodle.getPosition().y ,0));
        }
        if(doodle.getPosition().x >camera.viewportWidth + doodle.getTexture().getWidth()){
            doodle.setPosition(new Vector3(- doodle.getTexture().getWidth(),doodle.getPosition().y ,0));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(background, 0, 0, MainGameGdx.WIDTH, MainGameGdx.HEIGHT);
        for(Platforms_dj platform : platforms){
            sb.draw(platform.getPlatform(),platform.getPosPlatform().x, platform.getPosPlatform().y);
        }
        sb.draw(doodle.getTexture(), doodle.getPosition().x, doodle.getPosition().y);
        text.draw(sb, String.valueOf((int)score.getScore()), camera.position.x-15, camera.position.y+180);

        if(gameover)
            sb.draw(gameoverImg, camera.position.x - gameoverImg.getWidth() / 2, camera.position.y);

        sb.end();
    }
    public int maxPlatformY(){

        int maxX=0;
        for(int i = 1; i <= 20; i++) {
            if(platforms.get(i).getPosPlatform().y>maxX){
                maxX=(int)platforms.get(i).getPosPlatform().y;

            }
        }
        return (int) maxX;
    }
}
