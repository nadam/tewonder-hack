package se.anyro.tewonderhack;

import se.anyro.tewonderhack.subsystem.PositionSubSystem;
import se.anyro.tewonderhack.subsystem.RenderColorRectsSystem;
import se.anyro.tewonderhack.subsystem.TouchHandler;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;

public class MainActivity extends Activity {

    private GameView mGameView;
    private MainThread mMainThread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EntityManager entityManager = new EntityManager();
        EntityFactory factory = new EntityFactory(entityManager);
        factory.createSystemEntity();
        factory.createColorRect(0, 0, 0xffff0000);
        factory.createColorRect(120, 10, 0xffffff00);
        mMainThread = new MainThread();

        mGameView = new GameView(this, mMainThread);

        TouchHandler touchHandler = new TouchHandler(entityManager, mGameView);
        mMainThread.addSubSystem(touchHandler);

        RenderColorRectsSystem renderColorRectsSystem = new RenderColorRectsSystem(entityManager, mGameView);

        mMainThread.addSubSystem(new PositionSubSystem(entityManager, mGameView));
        mMainThread.addSubSystem(renderColorRectsSystem);

        setContentView(this.mGameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainThread.start();
        Log.d("adam", "Activity   resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainThread.stop();
        Log.d("adam", "Activity   paused");
    }
}