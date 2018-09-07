package delta.alladin_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.Random;

public class BirdModel {
    private int x;
    private int y;
    private Point point;
    public Bird bird;
    private Random random = new Random();

    Score score;


    Bitmap[] birdAssets;
    Context context;

    BirdModel(Point point, Context context, Score score) {
        this.context = context;
        birdAssets = new Bitmap[6];
        birdAssets[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_4);
        birdAssets[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_3);
        birdAssets[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_2);
        birdAssets[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_1);
        birdAssets[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_2);
        birdAssets[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_3);

        this.point = point;
        this.score = score;
        int breadth = point.x / 12;
        int height = point.y / 14;
        bird = new Bird(height, breadth, birdAssets);
        x = random.nextInt(point.x * 2) + point.x * 2;
        y = random.nextInt(point.y / 2);
    }

    void move(Canvas canvas, float speed) {
        if (x < point.x) {
            bird.move(canvas, x, y);
        }
        if (x + bird.breadth < 0) {
            x = random.nextInt(point.x * 2) + point.x * 2;
            y = random.nextInt(point.y / 2);
        }
        x -= speed;
    }
}
