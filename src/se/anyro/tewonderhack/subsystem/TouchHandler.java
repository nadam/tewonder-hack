package se.anyro.tewonderhack.subsystem;

import java.util.Collection;

import se.anyro.tewonderhack.components.TouchComponent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class TouchHandler implements SubSystem {

    private volatile boolean mTouched = false;

    private EntityManager mEntityManager;

    private class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouched = true;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouched = false;
                return true;
            }
            return false;
        }

    }

    public TouchHandler(EntityManager entityManager, View view) {
        mEntityManager = entityManager;
        TouchListener touchListener = new TouchListener();
        view.setOnTouchListener(touchListener);
    }

    @Override
    public void processOneGameTick(long lastFrameTime) {

        Collection<TouchComponent> touchComponents = mEntityManager.getAllComponentsOfType(TouchComponent.class);

        for (TouchComponent touch : touchComponents) {
            touch.isTouching = mTouched;
        }
    }
}
