package model.planetevents;

import model.Vaisseau;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "une tortue g�ante qui semble d�river ne semble pas vous avoir vu\n";
		description += "a�e ! elle vous rentre dedans...\n";
		description += "par chance, votre bouclier a peu souffert. bouclier -2\n";
		drawDescription();
	}

}
