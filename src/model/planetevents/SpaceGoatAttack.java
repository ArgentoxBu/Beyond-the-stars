package model.planetevents;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run() {
		System.out.println("Une arm�e de ch�vres de l'espace vous attaque !");
		System.out.println("Votre bouclier est endommag�. D�fense -5");
	}

}
