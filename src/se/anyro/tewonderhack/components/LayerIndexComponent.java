package se.anyro.tewonderhack.components;

import com.wikidot.entitysystems.rdbmsbeta.Component;

public class LayerIndexComponent implements Component {

	private static final long serialVersionUID = -6338720377588788182L;
	public int layerIndex;

	public LayerIndexComponent(int layerIndex) {
		this.layerIndex = layerIndex;
	}

}
