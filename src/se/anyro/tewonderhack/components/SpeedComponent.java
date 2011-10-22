package se.anyro.tewonderhack.components;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class SpeedComponent implements Component {

	private static final long serialVersionUID = -4480199779602491674L;

	public float speedX = 0f;
	public float speedY = 0f;

	public SpeedComponent(float speedX, float speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
	}

}
