package se.anyro.tewonderhack;

import se.anyro.tewonderhack.subsystem.PositionSubSystem;
import se.anyro.tewonderhack.subsystem.RenderScaledDrawableSystem;
import se.anyro.tewonderhack.subsystem.TouchHandler;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;

public class MainActivity extends Activity {

	private GameView mGameView;
	private MainThread mMainThread;

	private RenderScaledDrawableSystem mRenderSystem;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		EntityManager entityManager = new EntityManager();
		EntityFactory factory = new EntityFactory(entityManager);
		factory.createSystemEntity();
		factory.createScalableBitmapEntity(getResources().getDrawable(R.drawable.image_200x120), 0);
		factory.createColorRect(0, 0, 0xffff0000, 1);
		factory.createColorRect(120, 10, 0xffffff00, 1);

		mMainThread = new MainThread();
		mGameView = new GameView(this, mMainThread);
		mRenderSystem = new RenderScaledDrawableSystem(entityManager, mGameView);

		mMainThread.addSubSystem(new TouchHandler(entityManager, mGameView));
		mMainThread.addSubSystem(new PositionSubSystem(entityManager, mGameView));
		mMainThread.addSubSystem(mRenderSystem);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 1, 0, "100%");
		menu.add(0, 2, 0, "200%");
		menu.add(0, 3, 0, "300%");
		menu.add(0, 4, 0, "400%");
		menu.add(0, 5, 0, "500%");
		menu.add(0, 6, 0, "600%");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		float scaleFactor = (float) item.getItemId();
		mRenderSystem.setScaleFactor(scaleFactor);
		return super.onOptionsItemSelected(item);
	}
}