package se.anyro.tewonderhack.subsystem;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class TouchHandler implements SubSystem {

    private class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            return false;
        }
        
    }
    
    public TouchHandler(View view) {
        TouchListener touchListener = new TouchListener();
        view.setOnTouchListener(touchListener);
    }
    
    @Override
    public void processOneGameTick(long lastFrameTime) {
        
    }

    
}
