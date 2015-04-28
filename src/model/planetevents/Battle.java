package model.planetevents;

import model.Vaisseau;

public class Battle extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "COMBAT !!\n";
		drawDescription();
	}

}
