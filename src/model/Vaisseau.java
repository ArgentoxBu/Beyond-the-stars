package model;

import java.util.ArrayList;

import controller.Game;

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
		actualiserStatsVaisseau();
	}

	// retourne true si le poids est acceptable
	public boolean poidsAcceptable() {
		if ( maxWeight > weight ) return false;
		return true;
	}
	
	// maj les caracteristiques du vaisseau en fonction des composants
	private void actualiserStatsVaisseau() {
		weight = arme.getWeight() + coque.getWeight() + reacteur.getWeight() + generateurBouclier.getWeight();
		
		attack = arme.getAttack() + coque.getAttack() + reacteur.getAttack() + generateurBouclier.getAttack();
		attack += attack*(double)(porteBonheur.getPouvoirSpecial().getBonusAttack()/100.);
		
		defense = arme.getDefense() + coque.getDefense() + reacteur.getDefense() + generateurBouclier.getDefense();
		defense += defense*(double)(porteBonheur.getPouvoirSpecial().getBonusDefense()/100.);
		
		mobility = arme.getMobility() + coque.getMobility() + reacteur.getMobility() + generateurBouclier.getMobility();
		mobility += mobility*(double)(porteBonheur.getPouvoirSpecial().getBonusMobilite()/100.);
		
		constitution = arme.getConstitution() + coque.getConstitution() + reacteur.getConstitution() + generateurBouclier.getConstitution();
		constitution += constitution*(double)(porteBonheur.getPouvoirSpecial().getBonusConstitution()/100.);
		
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

	public Vaisseau creerVaisseauAleatoire() {
		int alea;
		alea = alea(0, 9);
		ArrayList<String> name = new ArrayList<String>();
		name.add("Le Pat Mobile");
		name.add("L'Abdoubidou");
		name.add("Le Tessier Supreme");
		name.add("Le Lewis Swaggos");
		name.add("La Poubelle de l'Espace");
		name.add("Le GodGiven de Guerre");
		name.add("Le Charles AFK");
		name.add("La Casserole Defoncee");
		name.add("Le Cassos");
		name.add("HAGG Engine");
		String myname = name.get(alea);
		
		alea = alea(0,1);
		Coque coque;
		coque = Game.getInstance().getConteneurObjetsVaisseau().coqueDispo.get(alea);
		
		alea = alea(0,1);
		Reacteur reacteur;
		reacteur = Game.getInstance().getConteneurObjetsVaisseau().reacteurDispo.get(alea);
		
		alea = alea(0,1);
		GenerateurBouclier generateurBouclier;
		generateurBouclier = Game.getInstance().getConteneurObjetsVaisseau().generateurBouclierDispo.get(alea);
		
		alea = alea(0,1);
		Arme arme;
		arme = Game.getInstance().getConteneurObjetsVaisseau().armeDispo.get(alea);
		
		alea = alea(0,1);
		PorteBonheur porteBonheur;
		porteBonheur = Game.getInstance().getConteneurObjetsVaisseau().porteBonheurDispo.get(alea);
		
		ArrayList<ReliqueSacree> reliqueSacree = new ArrayList<ReliqueSacree>();
		reliqueSacree.add(Game.getInstance().getConteneurObjetsVaisseau().reliqueSacreeDispo.get(2));
		reliqueSacree.add(Game.getInstance().getConteneurObjetsVaisseau().reliqueSacreeDispo.get(3));
		reliqueSacree.add(Game.getInstance().getConteneurObjetsVaisseau().reliqueSacreeDispo.get(4));
		
		return new Vaisseau(myname, 30, arme, coque, reacteur, generateurBouclier, porteBonheur, reliqueSacree);
	}

	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b) {
		assert(a<=b);
		return (int)(Math.random() * (b-a+1)) + a;
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
	
	public ArrayList<Competence> getCompetencesUtilisables() {
		ArrayList<Competence> list = new ArrayList<Competence>();
		for ( ReliqueSacree r : reliqueSacree ) {
			list.add(r.getCompetence());
		}
		return list;
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

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	public Coque getCoque() {
		return coque;
	}

	public void setCoque(Coque coque) {
		this.coque = coque;
	}

	public Reacteur getReacteur() {
		return reacteur;
	}

	public void setReacteur(Reacteur reacteur) {
		this.reacteur = reacteur;
	}

	public GenerateurBouclier getGenerateurBouclier() {
		return generateurBouclier;
	}

	public void setGenerateurBouclier(GenerateurBouclier generateurBouclier) {
		this.generateurBouclier = generateurBouclier;
	}

	public PorteBonheur getPorteBonheur() {
		return porteBonheur;
	}

	public void setPorteBonheur(PorteBonheur porteBonheur) {
		this.porteBonheur = porteBonheur;
	}

	public ArrayList<ReliqueSacree> getReliqueSacree() {
		return reliqueSacree;
	}

	public void setReliqueSacree(ArrayList<ReliqueSacree> reliqueSacree) {
		this.reliqueSacree = reliqueSacree;
	}
}
