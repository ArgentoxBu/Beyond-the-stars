package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "un taoïste altruiste s'approche de votre vaisseau\n";
		description += "hourra ! il répare votre vaisseau dans un élan de générosité ! constitution +3\n";
		drawDescription();
	}

}
