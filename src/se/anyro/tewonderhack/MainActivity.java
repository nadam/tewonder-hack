package se.anyro.tewonderhack;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    public GameView gameView;
    public MainThread mMainThread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainThread = new MainThread();

        this.gameView = new GameView(this, mMainThread);
        setContentView(this.gameView);
    }
}