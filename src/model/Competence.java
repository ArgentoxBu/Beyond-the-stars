package model;

// une compétence est utilisée en combat par un vaisseau pour se battre
// usuellement, le vaisseau possede plusieurs compétences sur le terrain et a le choix.

public class Competence {
	
	private String name;
	private String description;
	
	private int porteeMini;
	private int porteeMaxi;
	private Object type; // TODO cf en bas
	
	public Competence( String name, int porteeMini, int porteeMaxi ) {
		this.name = name;
		this.porteeMaxi = porteeMaxi;
		this.porteeMini = porteeMini;
		
		System.out.println("Competence cree : assignation de variables par default. Louis creera ca demain.");
	}

	// TODO
	// type d'attaque : ligne droite, carré, cac, portée minimale, portée maximal, libre ( cf dofus : très bon systeme tbs )
	
	// getters setters
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
}
