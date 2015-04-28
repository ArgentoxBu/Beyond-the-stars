package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "Un taoïste altruiste s'approche de votre vaisseau\n";
		description += "Hourra ! Il répare votre vaisseau dans un élan de générosité ! Constitution +3\n";
		drawDescription();
	}

}
