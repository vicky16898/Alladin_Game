package delta.alladin_game;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.Random;

public class BirdModel {
    private int x;
    private int y;
    private Point point;
    public Bird bird;
    private Random random = new Random();
    Score score;

    BirdModel(Point point, Score score){
        this.point = point;
        this.score = score;
        int breadth = point.x / 12;
        int height = point.y / 20;
        bird = new Bird(height, breadth);
        x = random.nextInt(point.x * 2) + point.x*2;
        y = random.nextInt(point.y/2);
    }

    void move(Canvas canvas, float speed){
        if( x < point.x ){
            bird.move(canvas,x,y);
        }
        if( x+bird.breadth < 0 ){
            x = random.nextInt(point.x * 2) + point.x*2;
            y = random.nextInt(point.y/2);
        }
        x -= speed;
    }
}
