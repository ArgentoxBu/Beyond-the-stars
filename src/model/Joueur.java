package model;

import java.awt.Point;

public class Joueur {
	private Point coordonees;
	private Vaisseau vaisseau;
	private int equipe;
	
	public Joueur( Vaisseau v, int equipe ) {
		this.vaisseau = v;
		this.equipe = equipe;
	}

	public Point getCoordonees() {
		return coordonees;
	}

	public void setCoordonees(Point coordonees) {
		this.coordonees = coordonees;
	}

	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public void setVaisseau(Vaisseau vaisseau) {
		this.vaisseau = vaisseau;
	}

	public int getEquipe() {
		return equipe;
	}

	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}
}
