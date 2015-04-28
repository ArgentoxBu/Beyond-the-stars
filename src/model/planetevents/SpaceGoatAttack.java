package model.planetevents;

import model.Vaisseau;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "Une arm�e de ch�vres de l'espace vous attaque !\n";
		description += "Votre bouclier est endommag�. D�fense -5\n";
		drawDescription();
	}

}
