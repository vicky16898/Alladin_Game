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

    BirdModel(Point point){
        this.point = point;
        int breadth = point.x / 12;
        int height = point.y / 20;
        bird = new Bird(height, breadth);
        x = random.nextInt(point.x * 2) + point.x*2;
        y =random.nextInt(point.y);
    }

    void move(Canvas canvas, float speed){
        if(x<point.x){
            bird.move(canvas,x,y);
        }
        if(x<0){
            x = random.nextInt(point.x * 2) + point.x*2;
            y = random.nextInt(point.y);
        }
        x -= speed;
    }
}
