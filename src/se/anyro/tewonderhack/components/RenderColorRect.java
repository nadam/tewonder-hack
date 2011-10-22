package se.anyro.tewonderhack.components;

import android.graphics.Paint;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class RenderColorRect implements Component {
	public int x = 0;
	public int y = 0;
	public int width = 100;
	public int height = 100;
	public Paint paint;

	public RenderColorRect(int x, int y, int width, int height, int color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.paint = new Paint();
		this.paint.setColor(color);
	}
}
