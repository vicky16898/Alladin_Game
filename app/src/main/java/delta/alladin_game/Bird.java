package delta.alladin_game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bird {
    private int breadth,height;
    private Paint redPaint = new Paint();
    Rect dst = new Rect();

    Bird(int height,int breadth){
        this.breadth = breadth;
        this.height = height;
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setARGB(255,255,0,0);
    }

    void move(Canvas canvas,int x,int y){
        dst.top = y - height;
        dst.bottom = y + height;
        dst.left = x - breadth;
        dst.right = x + breadth;

        canvas.drawRect(dst,redPaint);
    }
}
