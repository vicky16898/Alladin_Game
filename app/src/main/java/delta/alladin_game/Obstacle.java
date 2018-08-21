package delta.alladin_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {
    Point point;
    Context context;
    int breadth , pos_x;
    Paint whitePaint = new Paint(), blackPaint = new Paint();
    Rect dst = new Rect();

    public void Obstacle(Context context, Point point,int height){
        this.point = point;
        this.context = context;

        this.breadth = point.x/12;
        pos_x = point.x + breadth/2;

        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(Color.WHITE);

        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setColor(Color.BLACK);

        dst.top = point.y - height;
        dst.top = point.y;
    }
    void move(Canvas canvas,int speed){
        pos_x -= speed;

        dst.left = pos_x - breadth/2;
        dst.right = pos_x + breadth/2;

        canvas.drawRect(dst,whitePaint);
        canvas.drawRect(dst,blackPaint);
    }
}
