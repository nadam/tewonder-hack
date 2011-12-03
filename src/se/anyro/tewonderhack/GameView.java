package se.anyro.tewonderhack;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public int surfaceWidth = -1;
    public int surfaceHeight = -1;
    public boolean available = false;

    private MainThread mThread;

    public GameView(Context context, MainThread thread) {
        super(context);
        mThread = thread;
        this.getHolder().addCallback(this);

        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        available = true;
        surfaceWidth = width;
        surfaceHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        available = false;
        surfaceWidth = -1;
        surfaceHeight = -1;
        holder = null;

        mThread.stop();
    }

}
