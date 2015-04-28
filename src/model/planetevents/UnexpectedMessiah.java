package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "Un tao�ste altruiste s'approche de votre vaisseau\n";
		description += "Hourra ! Il r�pare votre vaisseau dans un �lan de g�n�rosit� ! Constitution +3\n";
		drawDescription();
	}

}
