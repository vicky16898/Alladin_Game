package delta.alladin_game;

public class Score {

    public double score;

    public Score(){
        this.score = 0;
    }

    public void update_score(double point){
        this.score+=point;
    }

    public int getScore(){
        return (int) score;
    }
}
