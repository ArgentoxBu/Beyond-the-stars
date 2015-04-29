package model.planetevents;

import model.Vaisseau;


public class SpaceGoatAttack extends PlanetEvent {

	@Override
	public void run(Vaisseau v) {
		super.run(v);
		description += "une armée de chèvres de l'espace vous attaque !\n";
		description += "votre bouclier est endommagé. défense -5\n";
		v.setDefense(v.getDefense()-5);
		drawDescription();
	}

}
