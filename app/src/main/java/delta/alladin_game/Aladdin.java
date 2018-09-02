package delta.alladin_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

class Aladdin {
    private int pos_y;
    public int length;
    public Rect dst= new Rect();
    private Rect src = new Rect();
    float downVelocity = -10;
    Point point;
    int breadth;
    //Rect src;
    private Bitmap aladdin;

    Aladdin(Context context, Point point){

        pos_y = point.y/2;
        length = point.y/5;
        breadth = point.x / 10;

        this.point = point;
        dst.left = point.x/10;
        dst.right = dst.left + breadth;
        dst.top = pos_y - length/2;
        dst.bottom = pos_y + length/2;

        aladdin = BitmapFactory.decodeResource(context.getResources(),R.drawable.aladdin);

        //
        src.left = aladdin.getWidth()/3;
        src.right = aladdin.getWidth()*2/3;
        src.top = aladdin.getHeight()/10;
        src.bottom = aladdin.getHeight()*7/10;
        //
        //src = new Rect(0,0,aladdin.getWidth(),aladdin.getHeight());
    }

    public void move(Canvas canvas, float speed){

        if(pos_y>=length/2) pos_y += speed;
        if(pos_y<=length/2)pos_y=length/2;
        dst.top = pos_y - length/2;
        dst.bottom = pos_y + length/2;
        canvas.drawBitmap(aladdin,src,dst,null);

    }

    public boolean fallDown(Canvas canvas, float density){
        pos_y += downVelocity*density;
        downVelocity+=0.3*density;

        Log.d("Speed", breadth+"");

        if(dst.top>point.y){
            return true;
        }
        else
            return false;
    }
}
