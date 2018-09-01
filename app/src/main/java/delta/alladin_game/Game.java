package delta.alladin_game;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

class Game extends View {

    float density;
    float inc;
    ObstacleModel obstacleModel;
    BirdModel birdModel;
    long FRAME_RATE = 1000 / 60;
    Aladdin aladdin;
    float sand_speed, sky_speed, dir_aladdin = 1, speed_aladdin, speed_obstacle, speed_bird, acceleration=(float) 0.1, accelerationUp = (float) 0.5;
    Point point;
    BackGround sand, sky;
    boolean flag = false;
    public ViewDialog viewDialog;
    Paint paint;
    Context context;


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

        aladdin = new Aladdin(context, point);
        sand = new BackGround(context, R.drawable.sand, point.x * 3, point.y / 2);
        sky = new BackGround(context, R.drawable.bg, point.x * 6 / 5, point.y);
        obstacleModel = new ObstacleModel(point);
        birdModel = new BirdModel(point);

        setSpeed();
    }

    @Override
    public void onDraw(Canvas canvas) {

        sky.move(canvas, 0, sky_speed);

        sand.move(canvas, point.y / 2, sand_speed);
        aladdin.move(canvas, speed_aladdin);

            if(dir_aladdin==1)
                speed_aladdin+=acceleration;
            else
                speed_aladdin-=accelerationUp;

        obstacleModel.move(canvas, speed_obstacle);
        birdModel.move(canvas, speed_bird);

        for (int i = 0; i < 4; i++) {
            if (obstacleModel.obstacles[i].isPresent)
                if (aladdin.dst.intersect(obstacleModel.obstacles[i].dst)) {

                    if (!flag) {

                        viewDialog.showDialog((Activity) getContext());
                        flag = true;
                    }
                }
        }

        if (birdModel.bird.dst.intersect(aladdin.dst)) {
            if (!flag) {
                viewDialog.showDialog((Activity) getContext());
                flag = true;
            }
        }

        if (aladdin.dst.top < 0 || aladdin.dst.bottom > point.y) {
            if (!flag) {
                viewDialog.showDialog((Activity) getContext());
                flag = true;
                dir_aladdin = 0;
            }
        }

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
        sand_speed = 8*density;
        sky_speed = 4*density;
        speed_aladdin = 0*density;
        speed_obstacle = 8*density;
        speed_bird = 16*density;
        acceleration*=density;
        accelerationUp*=density;
        inc = (float) 0.01*density;
    }

    private class ViewDialog {

        private void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.retry_dialog);
            Window window = dialog.getWindow();
            Button retry = (Button) dialog.findViewById(R.id.retry);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paint = new Paint();
                    paint.setColor(Color.RED);
                    dir_aladdin = 1;
                    aladdin = new Aladdin(context, point);
                    sand = new BackGround(context, R.drawable.sand, point.x * 3, point.y / 2);
                    sky = new BackGround(context, R.drawable.bg, point.x * 6 / 5, point.y);
                    obstacleModel = new ObstacleModel(point);
                    birdModel = new BirdModel(point);

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
