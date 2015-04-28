package model.planetevents;

import model.Vaisseau;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "Une armée de chèvres de l'espace vous attaque !\n";
		description += "Votre bouclier est endommagé. Défense -5\n";
		drawDescription();
	}

}
