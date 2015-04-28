package model;

public class ReliqueSacree extends PieceVaisseau {

	Competence competence;
	
	public ReliqueSacree(String name, int attack, int defense, int constitution, int mobility, int luck) {
		super(name, 0, attack, defense, constitution, mobility, luck);
		this.competence = new Competence("Ptit coup par défaut", 100, "attackLibre", 1, 1, null, true);
		this.competence.setDescription("Aucune competence n'a été donnée à cette relique.");
	}
	
	public ReliqueSacree(String name, Competence competence) {
		super(name, 0, 0, 0, 0, 0, 0);
		this.competence = competence;
	}

	// getters setters
	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}
}
