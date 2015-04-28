package model;

import java.util.ArrayList;

public class Combat {

	private GrilleTBS grilleTBS;
	
	private ArrayList<Integer> ordreJoueurs;
	
	public Combat ( GrilleTBS grilleTBS ) {
		this.grilleTBS = grilleTBS;
		ordreJoueurs = getOrdreJoueur();
	}
	
	// lancement d'un combat en utilisant la grille tbs. celle ci doit donc être initialisée au préalable.
	public void lancerCombat() {
		System.out.println("LE COMBAT COMMENCE");
		// boucle de jeu tant  qu'il reste un enemi ou un allié
		while ( getNbJoueurEquipe( 3 ) > 0 || getNbJoueurEquipe( 1 ) + getNbJoueurEquipe( 2 ) > 0 ) {
			// tour de jeu de chaque joueur
			for ( int i=0; i<ordreJoueurs.size(); i++ ) {
				appliquerEffets(ordreJoueurs.get(i));
				
				// si c'est le joueur reel
				if ( grilleTBS.getJoueurs().get(i).getEquipe() == 1 ) {
					
				}
				
				// si c'est un bot
				else {
					jouerTourBot( i );
				}
			}
		}
		
		System.out.println("COMBAT TERMINE");
	}
	
	private void jouerTourBot(int i) {
		System.out.println("Le bot " + i + " passe son tour.");
	}

	public void actualiserMorts() {
		for ( int i=0; i<grilleTBS.getJoueurs().size(); i++ ) {
			if ( grilleTBS.getJoueurs().get(i).getNbPointVie()<=0 ) {
				grilleTBS.tuerJoueur(i);
				System.out.println("Un joueur est mort");
			}
		}
	}
	
	// applique les degats d'effets, dissipe les effets terminés sur le joueur a l'index i.
	public void appliquerEffets( int i ) {
		for ( int j=0; j<grilleTBS.getJoueurs().get(i).getEffets().size(); j++ ) {
			if ( grilleTBS.getJoueurs().get(i).getEffets().get(j).getPuissance() != 0 ) {
				grilleTBS.blesserJoueur( i, grilleTBS.getJoueurs().get(i).getEffets().get(j).getPuissance() );
			}
			grilleTBS.baisserDureeEffets(j, i);
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
	private int getNbJoueurEquipe(int i) {
		int cpt = 0;
		for ( Joueur j : grilleTBS.getJoueurs() ) {
			if ( j.getEquipe() == i && !j.isMort()) cpt ++;
		}
		return cpt;
	}
	
	// retourne un tableau d'int avec l'ordre des joueurs
	public ArrayList<Integer> getOrdreJoueur() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		int alea;
		ArrayList<Integer> nb_restants = new ArrayList<Integer>();
		for ( int i=0; i<grilleTBS.getJoueurs().size(); i++ )
			nb_restants.add(i);
		for ( int i=0; i<grilleTBS.getJoueurs().size(); i++ ) {
			alea = alea(0, nb_restants.size()-1);
			tab.add(alea);
			nb_restants.remove(alea);
		}
		return tab;
	}
	
	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b ) {
		assert(a<=b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
}
