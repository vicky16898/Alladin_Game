package delta.alladin_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Bird {

    int breadth,height;

    private Paint redPaint = new Paint();
    private Rect src = new Rect();
    Rect dst = new Rect();
    long startTime;
    long currentTime;
    int currentFrame = 0;
    Bitmap[] bitmaps = new Bitmap[6];


    Bird(int height, int breadth , Bitmap[] bitmaps) {

        this.bitmaps = bitmaps;
        this.breadth = breadth;
        this.height = height;
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setARGB(255, 255, 0, 0);
        Bitmap bitmap = bitmaps[0];
        Log.d("bitmap value" , String.valueOf(bitmap));
        src.left = 0;
        src.right = bitmap.getWidth();
        src.top = 0;
        src.bottom = bitmap.getHeight();
        startTime = System.nanoTime();

    }

    void move(Canvas canvas, int x, int y) {


        currentTime = (System.nanoTime() - startTime) / 1000000;
        if (currentTime > 80) {
            currentFrame++;
            startTime = System.nanoTime();

        }
        if (currentFrame == 6) {
            currentFrame = 0;
        }
        Bitmap bm = bitmaps[currentFrame];


        dst.top = y - height;
        dst.bottom = y + height;
        dst.left = x - breadth;
        dst.right = x + breadth;
        canvas.drawBitmap(bm , src , dst , null);



    }
}
