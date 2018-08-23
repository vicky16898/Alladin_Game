package delta.alladin_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class BackGround {
    private int start_x;
    private Bitmap bitmap;

    BackGround(Context context, int res, int width, int height){
        start_x = 0;

        bitmap = BitmapFactory.decodeResource(context.getResources(),res);
        bitmap = Bitmap.createScaledBitmap(bitmap,width,height,false);

    }

    public void move(Canvas canvas, int top, float speed){
        canvas.drawBitmap(bitmap,start_x,top,null);

        if(start_x<0)
            canvas.drawBitmap(bitmap,start_x+bitmap.getWidth(),top,null);

        start_x -= speed;

        if(start_x<(-bitmap.getWidth()))
            start_x = 0;
    }


}
