package se.anyro.tewonderhack.subsystem;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import se.anyro.tewonderhack.GameView;
import se.anyro.tewonderhack.components.BitmapComponent;
import se.anyro.tewonderhack.components.ColorComponent;
import se.anyro.tewonderhack.components.LayerIndexComponent;
import se.anyro.tewonderhack.components.SpatialComponent;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class RenderScaledDrawableSystem implements SubSystem {

	private EntityManager _entityManager;
	private GameView _gameView;

	private float _scaleFactor = 6f;

	private Paint _paint;
	private Matrix _matrix;

	public RenderScaledDrawableSystem(EntityManager em, GameView view) {
		_entityManager = em;
		_gameView = view;
		_paint = new Paint();
		_paint.setFilterBitmap(false);
		_matrix = new Matrix();
	}

	@Override
	public void processOneGameTick(long lastFrameTime) {
		if (!_gameView.available)
			return;

		Canvas canvas = _gameView.holder.lockCanvas();
		if (canvas != null) {

			Set<UUID> renderables = _entityManager.getAllEntitiesPossessingComponent(LayerIndexComponent.class);

			ArrayList<UUID> layer0 = new ArrayList<UUID>();
			ArrayList<UUID> layer1 = new ArrayList<UUID>();
			ArrayList<UUID> layer2 = new ArrayList<UUID>();

			for (UUID entity : renderables) {
				int layerIndex = _entityManager.getComponent(entity, LayerIndexComponent.class).layerIndex;
				if (layerIndex == 0)
					layer0.add(entity);
				else if (layerIndex == 1)
					layer1.add(entity);
				else if (layerIndex == 2)
					layer2.add(entity);
			}

			ArrayList<UUID> allLayers = new ArrayList<UUID>();
			allLayers.addAll(layer0);
			allLayers.addAll(layer1);
			allLayers.addAll(layer2);

			_matrix = new Matrix();
			_matrix.setScale(_scaleFactor, _scaleFactor);

			for (UUID renderable : allLayers) {
				SpatialComponent posComponent = _entityManager.getComponent(renderable, SpatialComponent.class);

				ColorComponent colComponent = null;
				BitmapComponent bmpComponent = null;

				if (_entityManager.hasComponent(renderable, ColorComponent.class))
					colComponent = _entityManager.getComponent(renderable, ColorComponent.class);
				else if (_entityManager.hasComponent(renderable, BitmapComponent.class))
					bmpComponent = _entityManager.getComponent(renderable, BitmapComponent.class);

				if (bmpComponent != null) {
					//_matrix.setTranslate(posComponent.x, posComponent.y);
					canvas.drawBitmap(bmpComponent.bitmap, _matrix, _paint);
				} else if (colComponent != null) {
					canvas.drawRect(posComponent.x, posComponent.y, posComponent.width, posComponent.height, colComponent.paint);
				}
			}

			_gameView.holder.unlockCanvasAndPost(canvas);
		}
	}

}
