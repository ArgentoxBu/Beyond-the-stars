package model;

// une comp�tence est utilis�e en combat par un vaisseau pour se battre
// usuellement, le vaisseau possede plusieurs comp�tences sur le terrain et a le choix.

public class Competence {
	
	private String name;
	private String description;
	
	private int porteeMini;
	private int porteeMaxi;
	private int puissance;
	private String type; // booster, teleporter, attackLigne, attackLibre ( boost stats / deplacement instant / attaque en ligne droite / attaque � portee libre
	private boolean ligneDeVue;
	private CombatEffect combatEffect;

	public Competence( String name, int puissance, String type, int porteeMini, int porteeMaxi, CombatEffect combatEffect, boolean ligneDeVue ) {
		this.name = name;
		this.porteeMaxi = porteeMaxi;
		this.porteeMini = porteeMini;
		this.ligneDeVue = ligneDeVue;
		this.puissance = puissance;
		setType(type);
		this.combatEffect = combatEffect;
	}
	
	// getters setters
	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		assert ( type == "attackLibre" || type == "attackLigne" || type == "booster" || type == "teleporter" );
		this.type = type;
	}

	public boolean isLigneDeVue() {
		return ligneDeVue;
	}

	public void setLigneDeVue(boolean ligneDeVue) {
		this.ligneDeVue = ligneDeVue;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPorteeMini() {
		return porteeMini;
	}

	public void setPorteeMini(int porteeMini) {
		this.porteeMini = porteeMini;
	}

	public int getPorteeMaxi() {
		return porteeMaxi;
	}

	public void setPorteeMaxi(int porteeMaxi) {
		this.porteeMaxi = porteeMaxi;
	}

	public CombatEffect getCombatEffect() {
		return combatEffect;
	}

	public void setCombatEffect(CombatEffect combatEffect) {
		this.combatEffect = combatEffect;
	}
	
	public String getMiniMaxi(){
		return ""+getPorteeMini() +" - "+ getPorteeMaxi();
	}
	
	public String getNameCombatEffectOuDefaut(){
		if(this.combatEffect == null){
			return "Pas d'effet";
		}
		else
		{
			return this.combatEffect.getName();
		}
	}
	
	public String getIsLignedeVue(){
		if(this.isLigneDeVue()){
			return "Ligne de vue non requise";
		}
		else
		{
			return "Ligne de vue requise";
		}
	}
	
	public String getPuissanceToString(){
		return ""+this.getPuissance();
	}
}
