package delta.alladin_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

class Game extends View{

    Aladdin aladdin;
    int sand_speed,sky_speed,dir_aladdin=1,speed_aladdin;
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

        sand_speed = point.x/200;
        sky_speed = point.x/500;
        speed_aladdin = 0;
    }

    @Override
    public void onDraw(Canvas canvas){
        sky.move(canvas,0,sky_speed);
        sand.move(canvas,point.y/2 ,sand_speed);
        aladdin.move(canvas,speed_aladdin,dir_aladdin);

        postInvalidateDelayed(20);
    }

    public void changeDir() {
        if(dir_aladdin == 1)
            speed_aladdin = point.x/75;
        else
            speed_aladdin = point.x/50;

        dir_aladdin *= -1;
    }
}
