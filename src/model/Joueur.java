package model;

import java.awt.Point;
import java.util.ArrayList;

public class Joueur {
	private Point coordonees;
	private Vaisseau vaisseau;
	private int equipe; // EQUIPE 1 = PERSONNEL, 2 = ALLIES, 3 = ENEMIS
	private ArrayList<CombatEffect> effets;
	
	// combat
	private int nbPointMvt;
	private int nbPointVie;
	private boolean mort;
	
	public Joueur( Vaisseau v, int equipe ) {
		this.vaisseau = v;
		this.equipe = equipe;
		this.effets = new ArrayList<CombatEffect>();
		
		nbPointMvt = vaisseau.getMobility()/5;
		nbPointVie = vaisseau.getConstitution()*10;
		mort = false;
	}
	
	public int getTurnPM() {
		return getFinalMobility()/5;
	}
	
	// enleve l'effet à l'index i
	public void removeEffectIndex( int i ) {
		effets.remove(i);
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
	
	public ArrayList<CombatEffect> getEffets() {
		return effets;
	}

	public void setEffets(ArrayList<CombatEffect> effets) {
		this.effets = effets;
	}

	public int getNbPointMvt() {
		return nbPointMvt;
	}

	public void setNbPointMvt(int nbPointMvt) {
		this.nbPointMvt = nbPointMvt;
	}

	public int getNbPointVie() {
		return nbPointVie;
	}

	public void setNbPointVie(int nbPointVie) {
		this.nbPointVie = nbPointVie;
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

	public boolean isMort() {
		return mort;
	}

	public void setMort(boolean mort) {
		this.mort = mort;
	}
}
