package com.zuk.treamz.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zuk.treamz.game.sprites.Bird;
import com.zuk.treamz.game.sprites.Score;
import com.zuk.treamz.game.sprites.Tube;


public class PlayState extends State {

    private static final int GROUND_Y_OFFSET = -30;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background;
    private BitmapFont text;
    private Texture ground;
    private Texture gameoverImg;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Array<Tube> tubes;
    private ShapeRenderer sr;
    private Score score;

    private boolean gameover;

    public PlayState(GameStateManager gsm,int scorer){
        super(gsm);
        score = new Score();
        score.setScore(scorer);

        bird = new Bird(40, 200);
        background = new Texture("bg.png");
        text = new BitmapFont();
        ground = new Texture("ground.png");
        gameoverImg = new Texture("gameover.png");

        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        gameover = false;
    }
    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            if(gameover) {
                score.setScore(0);
                gsm.set(new PlayState(gsm, 0));
            }
            else
                bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        if(((score.getScore()+1)%11)==0){
            int score1 = score.getScore();
            gsm.set(new Doodlejump(gsm,score1+1));
        }
        Gdx.app.log("GameScreen FPS", (1/dt) + "");

        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.set(bird.getX() + 80, camera.viewportHeight / 2, 0);
        for(Tube tube : tubes){
            if(camera.position.x - camera.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                //
                //score.addScoreFlappy();

                tube.reposition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                tube.setScored();
            }
            if(bird.getX()>(tube.getPosTopTube().x + Tube.TUBE_WIDTH/2)){
                if(!tube.getScored()){
                    score.addScoreFlappy();
                }
                tube.setUnscored();
            }

            if(tube.collides(bird.getBounds())){
                bird.colliding = true;
                gameover = true;
            }
        }
        if(bird.getY() <= ground.getHeight() + GROUND_Y_OFFSET){
            gameover = true;
            bird.colliding = true;
        }
        camera.update();
    }

    public void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        //System.out.println("X: " + groundPos1.x + " Y: " + groundPos1.y);
        for(Tube tube : tubes){
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(bird.getTexture(), bird.getX(), bird.getY());
        text.draw(sb, String.valueOf((int)score.getScore()), camera.position.x-15, camera.position.y+180);

        if(gameover)
            sb.draw(gameoverImg, camera.position.x - gameoverImg.getWidth() / 2, camera.position.y);
        sb.end();
    }


}
