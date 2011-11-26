package se.anyro.tewonderhack.subsystem;

import java.util.Set;
import java.util.UUID;

import se.anyro.tewonderhack.GameView;
import se.anyro.tewonderhack.components.SpatialComponent;
import se.anyro.tewonderhack.components.SpeedComponent;
import se.anyro.tewonderhack.components.TouchComponent;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class PositionSubSystem implements SubSystem {

	private EntityManager mEntityManager;
	private GameView mGameView;

	public PositionSubSystem(EntityManager em, GameView gv) {
		mEntityManager = em;
		mGameView = gv;
	}

	@Override
	public void processOneGameTick(long lastFrameTime) {
		TouchComponent touch = mEntityManager.getAllComponentsOfType(TouchComponent.class).iterator().next();

		if (touch.isTouching) {
			// Move each component
			Set<UUID> movables = mEntityManager.getAllEntitiesPossessingComponent(SpeedComponent.class);
			for (UUID entity : movables) {

				SpeedComponent speedComponent = mEntityManager.getComponent(entity, SpeedComponent.class);
				SpatialComponent posComponent = mEntityManager.getComponent(entity, SpatialComponent.class);

				posComponent.x += speedComponent.speedX;
				posComponent.y += speedComponent.speedY;

				if (mGameView.available) {
					int w = 200;
					int h = 120;
					if (posComponent.x + posComponent.width > w) {
						posComponent.x = w - posComponent.width;
						speedComponent.speedX = -speedComponent.speedX;
					} else if (posComponent.x < 0) {
						posComponent.x = 0;
						speedComponent.speedX = -speedComponent.speedX;
					}
					if (posComponent.y + posComponent.height > h) {
						posComponent.y = h - posComponent.height;
						speedComponent.speedY = -speedComponent.speedY;
					} else if (posComponent.y < 0) {
						posComponent.y = 0;
						speedComponent.speedY = -speedComponent.speedY;
					}
				}
			}

		}
	}

}
