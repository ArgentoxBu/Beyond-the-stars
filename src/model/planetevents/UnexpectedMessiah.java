package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		System.out.println("Un tao�ste altruiste s'approche de votre vaisseau");
		System.out.println("Hourra ! Il r�pare votre vaisseau dans un �lan de g�n�rosit� ! Constitution +3");
	}

}
