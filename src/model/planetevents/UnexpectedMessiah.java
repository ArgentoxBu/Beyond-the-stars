package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "un tao�ste altruiste s'approche de votre vaisseau\n";
		description += "hourra ! il r�pare votre vaisseau dans un �lan de g�n�rosit� ! constitution +3\n";
		drawDescription();
	}

}
