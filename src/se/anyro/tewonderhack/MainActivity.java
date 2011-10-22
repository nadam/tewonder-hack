package se.anyro.tewonderhack;

import java.util.UUID;

import se.anyro.tewonderhack.components.RenderColorRect;
import se.anyro.tewonderhack.subsystem.RenderColorRectsSystem;
import se.anyro.tewonderhack.subsystem.TouchHandler;
import android.app.Activity;
import android.os.Bundle;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;

public class MainActivity extends Activity {

    private GameView mGameView;
    private MainThread mMainThread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EntityManager entityManager = new EntityManager();
        UUID entity = entityManager.createEntity("Rect");
        entityManager.addComponent(entity, new RenderColorRect());

        mMainThread = new MainThread();

        mGameView = new GameView(this, mMainThread);

        TouchHandler touchHandler = new TouchHandler(entityManager, mGameView);
        mMainThread.addSubSystem(touchHandler);

        RenderColorRectsSystem renderColorRectsSystem = new RenderColorRectsSystem(entityManager, mGameView);

        mMainThread.addSubSystem(renderColorRectsSystem);

        setContentView(this.mGameView);
    }
}