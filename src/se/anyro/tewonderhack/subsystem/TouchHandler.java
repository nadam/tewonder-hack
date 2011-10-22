package se.anyro.tewonderhack.subsystem;

import java.util.Collection;

import se.anyro.tewonderhack.components.RenderColorRect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class TouchHandler implements SubSystem {

    private volatile boolean mTouched = false;

    EntityManager mEntityManager;

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
        if (mTouched) {
            Collection<RenderColorRect> rects = mEntityManager.getAllComponentsOfType(RenderColorRect.class);
            for (RenderColorRect pos : rects) {
                ++pos.y;
            }
        }
    }
}
