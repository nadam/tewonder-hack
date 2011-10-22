package se.anyro.tewonderhack.subsystem;

import java.util.List;

import se.anyro.tewonderhack.GameView;
import se.anyro.tewonderhack.components.RenderColorRect;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class RenderColorRectsSystem implements SubSystem {

	private EntityManager em;
	private GameView view;

	public RenderColorRectsSystem(EntityManager em, GameView view) {
		this.em = em;
		this.view = view;
	}

	@Override
	public void processOneGameTick(long lastFrameTime) {

		if (!view.available)
			return;

		Canvas canvas = view.holder.lockCanvas();
		if (canvas == null)
			return;

		List<RenderColorRect> renderables = em.getAllComponentsOfType(RenderColorRect.class);

		Paint paint = new Paint();
		paint.setColor(0xff0000);

		canvas.drawColor(0x000000);

		for (RenderColorRect comp : renderables) {
			canvas.drawRect(comp.x, comp.y, (comp.x + comp.width), (comp.y + comp.height), paint);
		}

		view.holder.unlockCanvasAndPost(canvas);
	}
}
