package model.planetevents;

import model.Vaisseau;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "une tortue géante qui semble dériver ne semble pas vous avoir vu\n";
		description += "aïe ! elle vous rentre dedans...\n";
		description += "par chance, votre bouclier a peu souffert. bouclier -2\n";
		drawDescription();
	}

}
