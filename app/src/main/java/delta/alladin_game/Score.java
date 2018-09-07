package delta.alladin_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class Score {

    public double score;
    Point point;
    Paint paint;
    RectF scoreRect;

    public Score(Point point){
        this.score = 0;
        this.point = point;
        scoreRect = new RectF();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void update_score(double point){
        this.score+=point;
    }

    public String getScore(){
        return String.format("%02d", (int)score);
    }

    public void displayScore(Canvas canvas){
        canvas.drawText(""+String.format("%02d", (int)score),point.x*14/15, point.y/10,paint);
    }
}
