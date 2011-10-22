package se.anyro.tewonderhack.subsystem;

import java.util.Collection;

import se.anyro.tewonderhack.GameView;
import se.anyro.tewonderhack.components.RenderColorRect;
import android.graphics.Canvas;

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

		Collection<RenderColorRect> renderables = em.getAllComponentsOfType(RenderColorRect.class);

		canvas.drawColor(0xff000000);

		for (RenderColorRect comp : renderables) {
			canvas.drawRect(comp.x, comp.y, (comp.x + comp.width), (comp.y + comp.height), comp.paint);
		}

		view.holder.unlockCanvasAndPost(canvas);
	}
}
