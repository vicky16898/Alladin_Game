package delta.alladin_game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

public class PowerUp {
    float x,y;
    Bitmap coin;
    Game game;
    Rect src,dst;
    int endTime,time;
    Point point;
    Random random = new Random();
    int distance;
    float sand_speed,sky_speed,obstacle_speed,bird_speed;
    PowerUp(Context context,Game game, Point point){
        this.point = point;
        x = point.x;
        y = random.nextInt(point.y/2);
        this.game =game;
        sand_speed = game.sand_speed;
        sky_speed = game.sky_speed;
        obstacle_speed = game.speed_obstacle;
        bird_speed = game.speed_bird;


        coin = BitmapFactory.decodeResource(context.getResources(),R.drawable.coin1);
        src = new Rect(0,0,coin.getWidth(),coin.getHeight());

    }
    public void move(Canvas canvas){
        x = x-obstacle_speed;
        dst = new Rect((int)x,(int)y,(int)x+point.x/20,(int)y+point.y/10);
        canvas.drawBitmap(coin,src,dst,null);
    }
    public void slowDown( float density){
        game.sand_speed = 4*density;
        game.sky_speed = 3*density;
        game.speed_bird = 9*density;
        game.speed_obstacle = 5*density;
    }

    public void speedUp() {
        game.sand_speed = sand_speed;
        game.sky_speed = sky_speed;
        game.speed_bird = bird_speed;
        game.speed_obstacle = obstacle_speed;
    }
}
