package delta.alladin_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.widget.Toast;

class Game extends View{

    ObstacleModel obstacleModel;
    long FRAME_RATE = 1000/60;
    Aladdin aladdin;
    int sand_speed,sky_speed,dir_aladdin=1,speed_aladdin,speed_obstacle;
    Point point;
    BackGround sand,sky;

    public Game(Context context){
        super(context);
    }

    public Game(Context context,Point point){
        super(context);
        this.point = point;

        aladdin = new Aladdin(context,point);
        sand = new BackGround(context,R.drawable.sand,point.x*3,point.y/2);
        sky = new BackGround(context,R.drawable.bg,point.x,point.y);
        obstacleModel = new ObstacleModel(point);

        sand_speed = point.x/200;
        sky_speed = point.x/500;
        speed_aladdin = 0;
        speed_obstacle = point.x/150;
    }

    @Override
    public void onDraw(Canvas canvas) {
        sky.move(canvas, 0, sky_speed);
        sand.move(canvas, point.y / 2, sand_speed);
        aladdin.move(canvas, speed_aladdin, dir_aladdin);
        obstacleModel.move(canvas, speed_obstacle);

        for (int i = 0; i < 4; i++) {
            if (obstacleModel.obstacles[i].isPresent)
                if (aladdin.dst.intersect(obstacleModel.obstacles[i].dst)) {
                    Toast.makeText(getContext(),"Game Over",Toast.LENGTH_LONG).show();
                }
                    postInvalidateDelayed(FRAME_RATE);
        }
    }

    public void changeDir() {
        if(dir_aladdin == 1)
            speed_aladdin = point.x/100;
        else
            speed_aladdin = point.x/80;

        dir_aladdin *= -1;
    }
}
