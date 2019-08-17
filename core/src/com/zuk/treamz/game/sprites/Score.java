package com.zuk.treamz.game.sprites;

public class Score {
    private  float score=0;
    private float height=0;
    public int getScore() {
        return (int)score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void addScoreFlappy(){
        score++;
    }
    public void  addScoreDoodle(int y){

        height = y/500;
        if(y%300==0) {
            score ++;
        }
    }
}
