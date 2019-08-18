package com.zuk.treamz.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zuk.treamz.game.Controller.SimpleDirectionGestureDetector;
import com.zuk.treamz.game.sprites.PacGoods;
import com.zuk.treamz.game.sprites.Pacman;
import com.zuk.treamz.game.sprites.PacmanEnemi;
import com.zuk.treamz.game.sprites.PacmanMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;

public class PacmanState extends State {

    private PacmanMap pacmanmap;
    private BitmapFont text;
    private Pacman pacman;
    private PacmanEnemi enemi;
    private TiledMap tileMap, collisionMap;
    private OrthogonalTiledMapRenderer tileMapRenderer, collisionMapRenderer;
    private TiledMapTileLayer collisionLayer;
    private Array<PacGoods> pacgoods;
    private boolean gameover;
    private Texture gameoverImg;
    private TextureAtlas atlas;
    private float fps = 0;
    private Texture texture;
    private Map<String,TextureRegion> textureRegion = new HashMap<String,TextureRegion>();
    public PacmanState(GameStateManager gsm) {
        super(gsm);
        texture = new Texture("pacman.png");
        atlas = new TextureAtlas();
        pacmanmap = new PacmanMap();
        TextureRegion tmt[][]= TextureRegion.split(texture,16,16) ;
       // textureRegion.put("bb",tmt[0][0]);
        //textureRegion.put("bb",tmt[0][1]);
//        textureRegion.put("bb",tmt[0][2]);
        atlas.addRegion("ff",texture,0,16,160,160);
        atlas.addRegion("ff",texture,16,16,16,16);
        atlas.addRegion("ff",texture,32,16,16,16);

        atlas.findRegions("ff").add( atlas.addRegion("ff",texture,0,16,16,16));
        gameoverImg = new Texture("gameover.png");
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.textureMagFilter = Texture.TextureFilter.Nearest;
        params.textureMinFilter = Texture.TextureFilter.Nearest;
        tileMap = new TmxMapLoader().load("mappac.tmx",params);
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);
        collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get(0);
        pacman = new Pacman(collisionLayer);
        enemi = new PacmanEnemi(collisionLayer,pacman);
        text = new BitmapFont();
        camera.setToOrtho(false,240,400);

        pacgoods = new Array<PacGoods>();

        for(int i = 0;i<32;i++){
            for(int j =0;j<32;j++){
                if(!collisionLayer.getCell((int)(i), (int) (j)).getTile().getProperties().containsKey("blocked")){
                    pacgoods.add(new PacGoods(i*16,j*16));
                    Gdx.app.log("ZUKCOUNT",String.valueOf(pacgoods.size));
                }
            }
        }
        SpriteBatch sb = new SpriteBatch();
        sb.begin();
        for(int i =0 ;i<100;i++){
            sb.draw(pacgoods.get(i).getTexture(), pacgoods.get(i).getPosition().x, pacgoods.get(i).getPosition().y);
        }
        sb.end();


        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                pacman.setNewdirection(1);
                Gdx.app.log("GO UP", "UP");
            }

            @Override
            public void onRight() {
                pacman.setNewdirection(2);
            }

            @Override
            public void onLeft() {
                pacman.setNewdirection(4);
            }

            @Override
            public void onDown() {
                pacman.setNewdirection(3);
            }
        }));
    }

    @Override
    public void handleInput() {



    }

    @Override
    public void update(float dt) {
        handleInput();
        pacman.update(dt);
        enemi.update(dt);
        enemi.updatePacman(pacman);
        Gdx.app.log("GameScreen FPS", (1/dt) + "");
        fps=1/dt;

//        sb.begin();
//        for(int i =0 ;i<100;i++){
//            sb.draw(pacgoods.get(i).getTexture(), pacgoods.get(i).getPosition().x, pacgoods.get(i).getPosition().y);
//        }
//        sb.end();
//        for (Vector2 block:pacmanmap.getPosition()){
//        if(pacman.getPosition().x>150){
//            pacman.setVelocity(new Vector2(-(int)(pacman.getVelocity().x),0));
//        }
//        }
//
//        if(Gdx.input.isTouched()) {
//            camera.translate(-Gdx.input.getDeltaX(),-Gdx.input.getDeltaY());
//        }
        if(pacman.getPosition().y>200&&pacman.getPosition().y<312){
            camera.position.y=( (int)(pacman.getPosition().y*10000))/(float)10000;
        }
        if(pacman.getPosition().x>120&&pacman.getPosition().x<392){
            camera.position.x=( (int)(pacman.getPosition().x*10000))/(float)10000;
        }
        int i =0 ;
        if((pacman.getPosition().x<(enemi.getPosition().x+5))&&(pacman.getPosition().x>(enemi.getPosition().x-5))){
            if((pacman.getPosition().y<(enemi.getPosition().y+5))&&(pacman.getPosition().y>(enemi.getPosition().y-5))){

            gameover=true;
            }
            }
        for (PacGoods goods:pacgoods){
//        if (goods.collides(pacman.getBounds())) {
//            goods.setAlive(false);
//        }
            if(((int)pacman.getPosition().x<(int)(goods.getPosition().x+4))&&((int)pacman.getPosition().x>(int)(goods.getPosition().x-4))) {
                if (((int) pacman.getPosition().y < (int) (goods.getPosition().y + 4)) && ((int) pacman.getPosition().y > (int) (goods.getPosition().y - 4))) {
                    goods.setAlive(false);
                    pacgoods.removeIndex(i);

                }
            }
            i++;
        }
       camera.update();
        if (pacmanmap.collides(pacman.getBounds())) {
            pacman.setVelocity(new Vector2(0, 0));
            float posx =0;
            float posy = 0;
            Gdx.app.log("fff", String.valueOf(pacman.getPosition().x%50));
            if(pacman.getPosition().x%50<10&pacman.getPosition().x%50!=0){
                posx =pacman.getPosition().x-pacman.getPosition().x%50;
                pacman.setPosition(new Vector2(posx,pacman.getPosition().y));

            }
            if(pacman.getPosition().x%50>10){
                posx =pacman.getPosition().x+(50-pacman.getPosition().x%50);
                pacman.setPosition(new Vector2(posx,pacman.getPosition().y));

            }
            if(pacman.getPosition().y%50>10){
                posy =pacman.getPosition().y+(50-pacman.getPosition().y%50);
                pacman.setPosition(new Vector2(pacman.getPosition().x,posy));

            }
            if(pacman.getPosition().y%50<10&pacman.getPosition().y%50!=0){
                posy =pacman.getPosition().y-pacman.getPosition().y%50;
                pacman.setPosition(new Vector2(pacman.getPosition().x,posy));

            }
            //pacman.setPosition(new Vector2(posx,posy));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        tileMapRenderer.setView(camera);
        tileMapRenderer.render();
        sb.begin();
        //sb.draw(atlas.findRegion("jh"),10,10);
        Gdx.gl.glClearColor(99/255f, 67/255f, 0/255f, 1);
        sb.draw(pacman.getTexture(),pacman.getPosition().x,pacman.getPosition().y);
        //sb.draw(texture,pacman.getPosition().x,pacman.getPosition().y);
        sb.draw(enemi.getTexture(),enemi.getPosition().x,enemi.getPosition().y);

        Random random = new Random();
        text.draw(sb, String.valueOf((float) fps), camera.position.x-15, camera.position.y+180);
        //if(random.nextInt(2)==1){
            for(int i =0 ;i<pacgoods.size;i++){
                if((pacgoods.get(i).getPosition().x<(camera.position.x+130))&&(pacgoods.get(i).getPosition().x > (camera.position.x -130))) {
                    if((pacgoods.get(i).getPosition().y<(camera.position.y+200))&&(pacgoods.get(i).getPosition().y > (camera.position.y -220))) {
                        sb.draw(pacgoods.get(i).getTexture(), pacgoods.get(i).getPosition().x, pacgoods.get(i).getPosition().y);
                    }
                }
            }
//        for(PacGoods good: pacgoods) {
//            if(good.getAlive()) {
//                sb.draw(good.getTexture(), good.getPosition().x, good.getPosition().y);
//            }
//        }}
//        int i = 0;
//        for(Texture pacmanMapT : pacmanmap.getWbTexture()) {
//            sb.draw(pacmanMapT, pacmanmap.getPosition().get(i).x, pacmanmap.getPosition().get(i).y);
//            i++;
//        }
        //
        if(gameover)
            sb.draw(gameoverImg, camera.position.x - gameoverImg.getWidth() / 2, camera.position.y);
        sb.end();

        sleep(30);
    }

    private long diff, start = System.currentTimeMillis();

    public void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();
        }
    }

}
