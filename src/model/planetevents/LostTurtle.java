package model.planetevents;

import model.Vaisseau;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "Une tortue géante qui semble dériver ne semble pas vous avoir vu\n";
		description += "Aïe ! Elle vous rentre dedans...\n";
		description += "Par chance, votre bouclier a peu souffert. Bouclier -2\n";
		drawDescription();
	}

}
