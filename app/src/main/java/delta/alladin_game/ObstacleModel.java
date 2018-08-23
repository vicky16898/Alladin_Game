package delta.alladin_game;

import android.graphics.Canvas;
import android.graphics.Point;
import java.util.Random;

public class ObstacleModel {

    private int dist = 0, lastObstacle;
    private Random random = new Random();
    public Obstacle[] obstacles;
    private Point point;

    ObstacleModel(Point point) {
        this.point = point;
        obstacles = new Obstacle[4];

        for (int i = 0; i < 4; i++)
            obstacles[i] = new Obstacle(point, 0);
    }


    private int addObstacle() {
        int temp = findFree();
        Obstacle obstacle = new Obstacle(point, random.nextInt(point.y / 4) + point.y / 4);
        obstacles[temp] = obstacle;
        obstacles[temp].isPresent = true;
        return temp;
    }


    private int findFree() {
        for (int i = 0; i < 4; i++)
            if (!obstacles[i].isPresent) {

                return i;
            }
        return 0;
    }

    void move(Canvas canvas, float speed) {
        if (dist == 0) {
            lastObstacle = addObstacle();
            dist = random.nextInt(point.x / 4) + point.x / 4;
        }
        for (int i = 0; i < 4; i++) {
            if (obstacles[i].isPresent) {
                obstacles[i].move(canvas, speed);
                if (point.x - obstacles[lastObstacle].pos_x > dist)
                    dist = 0;
                if (obstacles[i].pos_x < -obstacles[i].breadth/2)
                        obstacles[i].isPresent = false;

            }
        }
    }
}