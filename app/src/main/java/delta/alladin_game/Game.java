package delta.alladin_game;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Rect.intersects;

class Game extends View {

    int powerTime=3000,buffer=8500,powerUpState=0;//0-null//1-present//2-obtained
    float density;
    float inc;
    ObstacleModel obstacleModel;
    BirdModel birdModel;
    long FRAME_RATE = 1000/60;
    Aladdin aladdin;
    float sand_speed, sky_speed, dir_aladdin = 1, speed_aladdin, speed_obstacle, speed_bird, acceleration=(float) 0.25, accelerationUp = (float) 0.75;
    Point point;
    BackGround sand, sky;
    boolean flag = false, intersect = false;
    public ViewDialog viewDialog;
    Paint paint;
    Context context;
    Score score;
    PowerUp powerUp;


    public Game(Context context) {
        super(context);
    }

    public Game(Context context, Point point, float density) {
        super(context);
        this.context = context;
        this.point = point;
        this.density = density;
        viewDialog = new ViewDialog();


        paint = new Paint();
        paint.setColor(Color.RED);

        this.score = new Score(point);
        aladdin = new Aladdin(context, point);
        sand = new BackGround(context, R.drawable.sand, point.x * 3, point.y / 2);
        sky = new BackGround(context, R.drawable.bg, point.x * 6 / 5, point.y);
        obstacleModel = new ObstacleModel(point, score);
        birdModel = new BirdModel(point, score);

        setSpeed();
    }

    @Override
    public void onDraw(Canvas canvas) {

        buffer-=FRAME_RATE;
        if(powerUpState==0 && buffer < 0){
            powerUp = new PowerUp(context,this,point);
            powerUpState = 1;
            buffer = 5000;
        }

        sky.move(canvas, 0, sky_speed);
        sand.move(canvas, point.y / 2, sand_speed);

        if(powerUpState == 1){
            powerUp.move(canvas);
            if(powerUp.dst.left<0)
                powerUpState = 0;
        }
        else if(powerUpState==2){
            powerTime -= FRAME_RATE;
        }
        if(powerTime<0) {
            powerUpState = 0;
            powerUp.speedUp();
            buffer =5000;
            powerTime = 3000;
        }
        if(powerUpState==1 && powerUp.dst.intersect(aladdin.dst)) {
            powerUp.slowDown(density);
            powerUpState = 2;
        }
        obstacleModel.move(canvas, speed_obstacle);
        birdModel.move(canvas, speed_bird);



        if(aladdin.dst.top>=0){
            if(dir_aladdin==1)
                speed_aladdin+=acceleration;
            else
                speed_aladdin-=accelerationUp;
        } else {
            speed_aladdin=0;
            aladdin.dst.top=0;
        }
        if (aladdin.dst.top == 0 && dir_aladdin != 1) {
            speed_aladdin=0;
        }
        if (aladdin.dst.top == 0 && dir_aladdin != -1) {
            speed_aladdin+=acceleration;
        }

        if(!intersect){

            aladdin.move(canvas, speed_aladdin);
            score.update_score(0.1);
            for (int i = 0; i < 4; i++) {
                if (obstacleModel.obstacles[i].isPresent)
                    if (intersects(aladdin.dst, obstacleModel.obstacles[i].dst)) {
                        pausegame();
                        intersect = true;
                    }
            }

//            if (Rect.intersects(aladdin.dst, birdModel.bird.dst)) {
//                pausegame();
//                intersect = true;
//            }

            if (aladdin.dst.bottom > point.y) {
                if (!flag) {
                    viewDialog.showDialog((Activity) getContext());
                    flag = true;
                    dir_aladdin = 0;
                }
            }
            }
        else {
            if(aladdin.fallDown(canvas, density)){
                if (!flag) {
                    viewDialog.showDialog((Activity) getContext());
                    flag = true;
                }
            }
        }

        score.displayScore(canvas);

        sand_speed += inc;
        sky_speed += inc;
        speed_obstacle += inc;
        speed_bird += inc;

        if (!flag)
            postInvalidateDelayed(FRAME_RATE);


    }

    public void changeDir() {
        dir_aladdin *= -1;
    }

    public void setSpeed(){
        score.score = 0;
        sand_speed = 4*density;
        sky_speed = 3*density;
        speed_aladdin = 0;
        speed_obstacle = 5*density;
        speed_bird = 9*density;
        acceleration=(float) 0.25;
        accelerationUp = (float) 0.75;
        acceleration*=density;
        accelerationUp*=density;
        inc = (float) 0.01*density;
        intersect = false;
    }

    public void pausegame(){
        sand_speed = 0;
        sky_speed = 0;
        speed_aladdin = 0;
        speed_obstacle = 0;
        speed_bird = 0;
        acceleration=(float) 0;
        accelerationUp = (float) 0;
        inc = 0;
    }

    private class ViewDialog {

        private void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.retry_dialog);
            TextView textView = dialog.findViewById(R.id.scoreView);
            textView.setText("Your score: "+score.getScore());
            Window window = dialog.getWindow();
            Button retry = dialog.findViewById(R.id.retry);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paint = new Paint();
                    paint.setColor(Color.RED);
                    dir_aladdin = 1;
                    aladdin = new Aladdin(context, point);
                    sand = new BackGround(context, R.drawable.sand, point.x * 3, point.y / 2);
                    sky = new BackGround(context, R.drawable.bg, point.x * 6 / 5, point.y);
                    obstacleModel = new ObstacleModel(point,score);
                    birdModel = new BirdModel(point,score);
                    powerUpState = 0;
                    buffer = 8500;

                    setSpeed();
                    flag = false;

                    dialog.dismiss();

                }
            });

            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

}
