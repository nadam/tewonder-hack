package se.anyro.tewonderhack.components;

import android.graphics.Paint;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class ColorComponent implements Component {
	private static final long serialVersionUID = -8841195219723063533L;
	public int color;
	public int red;
	public int green;
	public int blue;

	public Paint paint;

	public ColorComponent(int color) {
		this.color = color;
		red = (color >> 16) & 0xff;
		green = (color >> 8) & 0xff;
		blue = color & 0xff;
		paint = new Paint();
		paint.setARGB(255, red, green, blue);
	}
}
