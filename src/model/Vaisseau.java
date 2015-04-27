package model;

import java.util.ArrayList;

public class Vaisseau {
	private int maxWeight;
	private int weight;
	private int attack;
	private int defense;
	private int constitution;
	private int mobility;
	private int luck;
	
	private String name;
	
	// Composantes
	Arme arme;
	Coque coque;
	Reacteur reacteur;
	GenerateurBouclier generateurBouclier;
	PorteBonheur porteBonheur;
	ArrayList<ReliqueSacree> reliqueSacree; // définit les sorts dispos en combat
	
	public Vaisseau(String name, int maxWeight, Arme arme, Coque coque, Reacteur reacteur, GenerateurBouclier generateurBouclier, PorteBonheur porteBonheur, ArrayList<ReliqueSacree> reliqueSacree) {
		this.name = name;
		this.maxWeight = maxWeight;
		this.arme = arme;
		this.coque = coque;
		this.reacteur = reacteur;
		this.porteBonheur = porteBonheur;
		this.reliqueSacree = reliqueSacree;
		this.generateurBouclier = generateurBouclier;
		calculerStatsVaisseau();
		assert ( !poidsAcceptable() );
	}

	// retourne true si le poids est acceptable
	public boolean poidsAcceptable() {
		if ( maxWeight > weight ) return false;
		return true;
	}
	
	// maj les caracteristiques du vaisseau en fonction des composants
	private void calculerStatsVaisseau() {
		weight = arme.getWeight() + coque.getWeight() + reacteur.getWeight() + generateurBouclier.getWeight();
		
		attack = arme.getAttack() + coque.getAttack() + reacteur.getAttack() + generateurBouclier.getAttack() + porteBonheur.getAttack();
		defense = arme.getDefense() + coque.getDefense() + reacteur.getDefense() + generateurBouclier.getDefense() + porteBonheur.getDefense();
		mobility = arme.getMobility() + coque.getMobility() + reacteur.getMobility() + generateurBouclier.getMobility() + porteBonheur.getMobility();
		constitution = arme.getConstitution() + coque.getConstitution() + reacteur.getConstitution() + generateurBouclier.getConstitution() + porteBonheur.getConstitution();
		luck = arme.getLuck() + coque.getLuck() + reacteur.getLuck() + generateurBouclier.getLuck() + porteBonheur.getLuck();
		
		// ajout des bonus des reliques
		for ( ReliqueSacree r : reliqueSacree ) {
			attack += r.getAttack();
			defense += r.getDefense();
			luck += r.getLuck();
			constitution += r.getConstitution();
			mobility += r.getMobility();
		}
	}

	@Override
	public String toString() {
		String res = "Vaisseau :\nmaxWeight=" + maxWeight + ", weight=" + weight
				+ ",\nattack=" + attack + ", defense=" + defense
				+ ", constitution=" + constitution + ", mobility=" + mobility
				+ ", luck=" + luck + ",\nname=" + name + ",\narme=" + arme.getName()
				+ ",\ncoque=" + coque.getName() + ",\nreacteur=" + reacteur.getName()
				+ ",\ngenerateurBouclier=" +generateurBouclier.getName()
				+ ",\nporteBonheur=" + porteBonheur.getName() + "\nReliques : ";
				for ( ReliqueSacree r : reliqueSacree ) res += r.getName() + ", ";
				res = res.substring(0, res.length()-2);
		return res;
	}

	// retourne le nb de PM, soit la mobilité / 5 et troncaturé
	public int calculerPM() {
		return mobility/5;
	}
	
	// getters setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxWeight() {
		return maxWeight;
	}
	
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public int getConstitution() {
		return constitution;
	}
	
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	
	public int getMobility() {
		return mobility;
	}
	
	public void setMobility(int mobility) {
		this.mobility = mobility;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}
}
