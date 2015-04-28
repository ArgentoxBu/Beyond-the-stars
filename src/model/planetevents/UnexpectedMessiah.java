package model.planetevents;

import model.Vaisseau;

public class UnexpectedMessiah extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		System.out.println("Un taoïste altruiste s'approche de votre vaisseau");
		System.out.println("Hourra ! Il répare votre vaisseau dans un élan de générosité ! Constitution +3");
	}

}
