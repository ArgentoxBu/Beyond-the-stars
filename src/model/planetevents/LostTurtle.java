package model.planetevents;

import model.Vaisseau;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "une tortue g�ante qui semble d�river ne semble pas\n";
		description += "vous avoir vu.\n";
		description += "a�e ! elle vous rentre dedans...\n";
		description += "par chance, votre bouclier a peu souffert.\n";
		description += "Defense -2\n";
		v.setDefense(v.getDefense()-2);
		drawDescription();
	}

}
