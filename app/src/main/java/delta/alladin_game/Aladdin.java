package delta.alladin_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

class Aladdin {
    private int pos_y;
    public int length;
    public Rect dst= new Rect();
    private Rect src = new Rect();
    //Rect src;
    private Bitmap aladdin;

    Aladdin(Context context, Point point){

        pos_y = point.y/2;
        length = point.y/5;
        int breadth = point.x / 10;

        dst.left = point.x/10;
        dst.right = dst.left + breadth;



        aladdin = BitmapFactory.decodeResource(context.getResources(),R.drawable.aladdin);

        //
        src.left = aladdin.getWidth()/3;
        src.right = aladdin.getWidth()*2/3;
        src.top = aladdin.getHeight()/10;
        src.bottom = aladdin.getHeight()*7/10;
        //
        //src = new Rect(0,0,aladdin.getWidth(),aladdin.getHeight());
    }

    public void move(Canvas canvas, float speed, float dir){
        pos_y += speed*dir;

        dst.top = pos_y - length/2;
        dst.bottom = pos_y + length/2;

        canvas.drawBitmap(aladdin,src,dst,null);
    }
}
