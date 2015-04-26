package model.planetevents;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run() {
		System.out.println("Une armée de chèvres de l'espace vous attaque !");
		System.out.println("Votre bouclier est endommagé. Défense -5");
	}

}
