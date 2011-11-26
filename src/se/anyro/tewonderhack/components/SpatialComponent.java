package se.anyro.tewonderhack.components;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class SpatialComponent implements Component {

	private static final long serialVersionUID = 3458625361819184853L;

	public float x = 0f;
	public float y = 0f;
	public float width = 0f;
	public float height = 0f;

	public float getRight() {
		return x + width;
	}

	public float getBottom() {
		return y + height;
	}

	public SpatialComponent(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public SpatialComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}

}
