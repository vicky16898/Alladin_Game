package delta.alladin_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.widget.Toast;

class Game extends View{

    float density,inc;
    ObstacleModel obstacleModel;
    BirdModel birdModel;
    long FRAME_RATE = 1000/60;
    Aladdin aladdin;
    float sand_speed,sky_speed,dir_aladdin=1,speed_aladdin,speed_obstacle,speed_bird;
    Point point;
    BackGround sand,sky;

    public Game(Context context){
        super(context);
    }

    public Game(Context context,Point point,int density){
        super(context);
        this.point = point;
        this.density = density;

        aladdin = new Aladdin(context,point);
        sand = new BackGround(context,R.drawable.sand,point.x*3,point.y/2);
        sky = new BackGround(context,R.drawable.bg,point.x*6/5,point.y);
        obstacleModel = new ObstacleModel(point);
        birdModel = new BirdModel(point);

        sand_speed = (point.x/200)/density;
        sky_speed = (point.x/500)/density;
        speed_aladdin = 0;
        speed_obstacle = (point.x/150)/density;
        speed_bird = (point.x/100)/density;
        inc = (float)(point.x/density)/500000;
    }

    @Override
    public void onDraw(Canvas canvas) {
        sky.move(canvas, 0, sky_speed);

        sand.move(canvas, point.y / 2, sand_speed);
        aladdin.move(canvas, speed_aladdin, dir_aladdin);
        obstacleModel.move(canvas, speed_obstacle);
        birdModel.move(canvas,speed_bird);

        for (int i = 0; i < 4; i++) {
            if (obstacleModel.obstacles[i].isPresent)
                if (aladdin.dst.intersect(obstacleModel.obstacles[i].dst)) {
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                }
        }

        if(birdModel.bird.dst.intersect(aladdin.dst))
            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();

        if(aladdin.dst.top<-aladdin.length/2 || aladdin.dst.bottom>point.y+aladdin.length/2) {
            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
            dir_aladdin = 0;
        }

        sand_speed += inc;
        sky_speed += inc;
        speed_obstacle += inc;
        speed_bird += inc;

        postInvalidateDelayed(FRAME_RATE);
    }

    public void changeDir() {
        if(dir_aladdin == 1)
            speed_aladdin = (point.x/80)/(int)density;
        else
            speed_aladdin = (point.x/80)/(int)density;

        dir_aladdin *= -1;
    }
}
