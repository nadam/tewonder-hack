package se.anyro.tewonderhack;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	public int surfaceWidth 			= -1;
	public int surfaceHeight 			= -1;
	public boolean available 			= false;
	public SurfaceHolder holder 	= null;
	
	public GameView(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		
		this.setKeepScreenOn(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d("tommy", "Surface created");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		available = true;
		surfaceWidth = width;
		surfaceHeight = height;
		this.holder = holder;
		
		Log.d("tommy", "Surface changed w=" + width + ", h=" + height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		available = false;
		surfaceWidth = -1;
		surfaceHeight = -1;
		holder = null;
		
		Log.d("tommy", "Surface destroyed");
	}

}
