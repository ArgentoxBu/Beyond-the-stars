package model.planetevents;

import model.Vaisseau;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		System.out.println("Une arm�e de ch�vres de l'espace vous attaque !");
		System.out.println("Votre bouclier est endommag�. D�fense -5");
	}

}
