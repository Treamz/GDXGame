package com.zuk.treamz.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zuk.treamz.game.MainGameGdx;
import com.zuk.treamz.game.sprites.Score;


public class MenuState extends State{
    Texture background;
    Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Score score = new Score();
//            gsm.set(new PlayState(gsm,0));
            gsm.set(new PacmanState(gsm));
//            gsm.set(new Doodlejump(gsm,0));
           // gsm.set(new TestState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0, MainGameGdx.WIDTH, MainGameGdx.HEIGHT);
        sb.draw(playBtn, (camera.position.x) - (playBtn.getWidth() / 2), camera.viewportHeight/2 - playBtn.getHeight()/2);
        sb.end();

    }
}