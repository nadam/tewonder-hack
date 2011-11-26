package se.anyro.tewonderhack.components;

import android.graphics.Bitmap;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class BitmapComponent implements Component {
	private static final long serialVersionUID = 3358653565565146463L;
	public Bitmap bitmap;

	public BitmapComponent(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

}
