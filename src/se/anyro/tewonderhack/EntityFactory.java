package se.anyro.tewonderhack;

import java.util.UUID;

import se.anyro.tewonderhack.components.BitmapComponent;
import se.anyro.tewonderhack.components.ColorComponent;
import se.anyro.tewonderhack.components.LayerIndexComponent;
import se.anyro.tewonderhack.components.SpatialComponent;
import se.anyro.tewonderhack.components.SpeedComponent;
import se.anyro.tewonderhack.components.TouchComponent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;

public class EntityFactory {

	private EntityManager mEntityManager;

	public EntityFactory(EntityManager entityManager) {
		mEntityManager = entityManager;
	}

	public UUID createColorRect(float x, float y, int color, int layerIndex) {
		UUID entity = mEntityManager.createEntity();

		mEntityManager.addComponent(entity, new LayerIndexComponent(layerIndex));
		mEntityManager.addComponent(entity, new ColorComponent(color));
		mEntityManager.addComponent(entity, new SpatialComponent(x, y, 100f, 100f));

		double rndX = Math.random() * 5f + 1f;
		double rndY = Math.random() * 5f + 1f;

		mEntityManager.addComponent(entity, new SpeedComponent((float) rndX, (float) rndY));
		return entity;
	}

	public UUID createScalableBitmapEntity(Drawable drawable, int layerIndex) {
		UUID entity = mEntityManager.createEntity();

		Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();

		mEntityManager.addComponent(entity, new SpatialComponent(0f, 0f));
		mEntityManager.addComponent(entity, new BitmapComponent(bmp));
		mEntityManager.addComponent(entity, new LayerIndexComponent(layerIndex));
		return entity;
	}

	public UUID createSystemEntity() {
		UUID entity = mEntityManager.createEntity();
		mEntityManager.addComponent(entity, new TouchComponent());
		return entity;
	}
}
