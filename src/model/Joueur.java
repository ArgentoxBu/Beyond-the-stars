package model;

import java.awt.Point;
import java.util.ArrayList;

public class Joueur {
	private Point coordonees;
	private Vaisseau vaisseau;
	private int equipe;
	private ArrayList<CombatEffect> effets;
	
	public Joueur( Vaisseau v, int equipe ) {
		this.vaisseau = v;
		this.equipe = equipe;
		this.effets = new ArrayList<CombatEffect>();
	}
	
	public int getFinalMobility() {
		int res = vaisseau.getMobility();
		if ( !effets.isEmpty() ) {
			for ( CombatEffect c : effets ) {
				res += c.getFixMobility();
			}
			for ( CombatEffect c : effets ) {
				res = res * (c.getFixMobility()+100) / 100;
			}
		}
		return res;
	}
	
	public int getFinalDefense() {
		int res = vaisseau.getDefense();
		if ( !effets.isEmpty() ) {
			for ( CombatEffect c : effets ) {
				res += c.getFixDefense();
			}
			for ( CombatEffect c : effets ) {
				res = res * (c.getFixDefense()+100) / 100;
			}
		}
		return res;
	}
	
	public int getFinalAttack() {
		int res = vaisseau.getAttack();
		if ( !effets.isEmpty() ) {
			for ( CombatEffect c : effets ) {
				res += c.getFixAttack();
			}
			for ( CombatEffect c : effets ) {
				res = res * (c.getFixAttack()+100) / 100;
			}
		}
		return res;
	}
	
	public int getFinalConstitution() {
		int res = vaisseau.getConstitution();
		if ( !effets.isEmpty() ) {
			for ( CombatEffect c : effets ) {
				res += c.getFixConstitution();
			}
			for ( CombatEffect c : effets ) {
				res = res * (c.getFixConstitution()+100) / 100;
			}
		}
		return res;
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
