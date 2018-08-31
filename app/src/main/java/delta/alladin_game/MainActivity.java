package delta.alladin_game;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    public static Game game;
    Point outSize;
    int density;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        outSize = new Point();
        density =(int) getResources().getDisplayMetrics().density;
        getWindowManager().getDefaultDisplay().getSize(outSize);

        game = new Game(this,outSize,density);
        game.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        game.changeDir();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        game.changeDir();
                        return true;
                }
                return false;
            }
        });

        setContentView(game);
    }
}
