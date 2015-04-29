package model;

import java.awt.Point;
import java.util.ArrayList;

import model.GrilleTBS.PathStep;

import controller.Game;


// instancie les fonctions pour affecter les joueurs sur la grille TBS en combat
public class Combat {
	
	private ArrayList<Integer> ordreJoueurs;


	public Combat() {
		ordreJoueurs = new ArrayList<Integer>();
	}
	
	public void jouerTourBot(int i) {
		Point bot = Game.getInstance().getJoueurs().get(i).getCoordonees();
		Point h = Game.getInstance().getJoueurs().get(0).getCoordonees();
		if(Game.getInstance().getGrilleTBS().getStepQueue() == null || 
				Game.getInstance().getGrilleTBS().getStepQueue().size()==0)
			Game.getInstance().getGrilleTBS().shortestPath(bot.x, bot.y, h.x, h.y);
		
		if(Game.getInstance().getGrilleTBS().getStepQueue() != null) {
			PathStep p = Game.getInstance().getGrilleTBS().getStepQueue().get(0);
			Game.getInstance().getJoueurs().get(i).setCoordonees(new Point(p.i, p.j));
			System.out.println(Game.getInstance().getJoueurs().get(i).getCoordonees());
			Game.getInstance().getGrilleTBS().getStepQueue().remove(0);
		}
	}

	public void actualiserMorts() {
		for ( int i=0; i<Game.getInstance().getGrilleTBS().getJoueurs().size(); i++ ) {
			if ( Game.getInstance().getGrilleTBS().getJoueurs().get(i).getNbPointVie()<=0 ) {
				Game.getInstance().getGrilleTBS().tuerJoueur(i);
				System.out.println("Un joueur est mort");
			}
		}
	}
	
	// applique les degats d'effets, dissipe les effets terminés sur le joueur a l'index i.
	public void appliquerEffets( int i ) {
		for ( int j=0; j<Game.getInstance().getGrilleTBS().getJoueurs().get(i).getEffets().size(); j++ ) {
			if ( Game.getInstance().getGrilleTBS().getJoueurs().get(i).getEffets().get(j).getPuissance() != 0 ) {
				Game.getInstance().getGrilleTBS().blesserJoueur( i, Game.getInstance().getGrilleTBS().getJoueurs().get(i).getEffets().get(j).getPuissance() );
			}
			Game.getInstance().getGrilleTBS().baisserDureeEffets(j, i);
		}
		actualiserMorts();
	}
	
	public void useCompetence ( int attaquant, int cible, Competence comp ) {
		int deg = calculerDegats ( comp.getPuissance(), Game.getInstance().getGrilleTBS().getJoueurs().get(attaquant), Game.getInstance().getGrilleTBS().getJoueurs().get(cible) );
		Game.getInstance().getGrilleTBS().blesserJoueur( cible, deg);
		if ( comp.getCombatEffect()!=null ) {
			Game.getInstance().getGrilleTBS().addEffectJoueur(cible, comp.getCombatEffect());
		}
		actualiserMorts();
	}
	
	private int calculerDegats( int puissance, Joueur attaquant, Joueur cible ) {
		double tmp = puissance * ( 1 + ( attaquant.getFinalAttack()/100 ) );
		int degats = (int) tmp;
		tmp = degats * ( cible.getFinalDefense() /100 );
		degats -= (int) tmp;
		return degats;
	}

	// retourne le nb de joueurs restants dans l'equipe i
	public int getNbJoueurEquipe(int i) {
		int cpt = 0;
		for ( Joueur j : Game.getInstance().getGrilleTBS().getJoueurs() ) {
			if ( j.getEquipe() == i && !j.isMort()) cpt ++;
		}
		return cpt;
	}
	
	// retourne un tableau d'int avec l'ordre des joueurs
	public ArrayList<Integer> getOrdreJoueur( int n ) {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		int alea;
		ArrayList<Integer> nb_restants = new ArrayList<Integer>();
		for ( int i=0; i<n; i++ )
			nb_restants.add(i);
		
		for ( int i=0; i<n; i++ ) {
			alea = alea(0, nb_restants.size()-1);
			tab.add(nb_restants.get(alea));
			nb_restants.remove(alea);
		}
		return tab;
	}
	
	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b ) {
		assert(a<=b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
	
	public ArrayList<Integer> getOrdreJoueurs() {
		return ordreJoueurs;
	}

	public void setOrdreJoueurs(ArrayList<Integer> ordreJoueurs) {
		this.ordreJoueurs = ordreJoueurs;
	}
}
