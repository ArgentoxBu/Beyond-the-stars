package model.planetevents;

import model.Vaisseau;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		System.out.println("Une armée de chèvres de l'espace vous attaque !");
		System.out.println("Votre bouclier est endommagé. Défense -5");
	}

}
