package delta.alladin_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {
    public boolean isPresent=false;
    public int breadth;
    public int pos_x;
    public int height;
    private Paint whitePaint = new Paint(), blackPaint = new Paint();
    public Rect dst = new Rect();

    Obstacle(Point point, int height){
        this.height = height;
        this.breadth = point.x/12;
        pos_x = point.x + breadth/2;

        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(Color.WHITE);

        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setColor(Color.BLACK);

        dst.top = point.y - height;
        dst.bottom = point.y;
    }
    void move(Canvas canvas, float speed){
        pos_x -= speed;

        dst.left = pos_x - breadth/2;
        dst.right = pos_x + breadth/2;

        canvas.drawRect(dst,whitePaint);
        canvas.drawRect(dst,blackPaint);
    }
}
