package model;

public class ReliqueSacree extends PieceVaisseau {

	Competence competence;
	
	public ReliqueSacree(String name, int attack, int defense, int constitution, int mobility, int luck) {
		super(name, 0, attack, defense, constitution, mobility, luck);
		competence = new Competence("DEFAULT", 1, 1);
		competence.setDescription("Aucune competence n'a ete donnee a cette relique.");
	}

	// getters setters
	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

}
