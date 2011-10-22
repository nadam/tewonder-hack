package se.anyro.tewonderhack;

import java.util.UUID;

import se.anyro.tewonderhack.components.RenderColorRect;
import se.anyro.tewonderhack.components.SpeedComponent;
import se.anyro.tewonderhack.components.TouchComponent;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;

public class EntityFactory {

	private EntityManager mEntityManager;

	public EntityFactory(EntityManager entityManager) {
		mEntityManager = entityManager;
	}

	public UUID createColorRect(int x, int y, int color) {
		UUID entity = mEntityManager.createEntity();
		mEntityManager.addComponent(entity, new RenderColorRect(x, y, 100, 100, color));

		double rndX = Math.random() * 5f + 1f;
		double rndY = Math.random() * 5f + 1f;

		mEntityManager.addComponent(entity, new SpeedComponent((float) rndX, (float) rndY));
		return entity;
	}

	public UUID createSystemEntity() {
		UUID entity = mEntityManager.createEntity();
		mEntityManager.addComponent(entity, new TouchComponent());
		return entity;
	}
}
