package model;

import java.util.ArrayList;

import controller.Game;


// instancie les fonctions pour affecter les joueurs sur la grille TBS en combat
public class Combat {
	
	private ArrayList<Integer> ordreJoueurs;
	boolean competenceUsed; // true si le joueur reel peut attaquer 
	
	public Combat () {
		ordreJoueurs = new ArrayList<Integer>();
		initCombat();
	}
	
	public void initCombat () {
		ordreJoueurs = getOrdreJoueur();
		competenceUsed = false;
	}
	
	// lancement d'un combat en utilisant la grille tbs. celle ci doit donc être initialisée au préalable.
	public void tourSuivant() {
		System.out.println("LE COMBAT COMMENCE");
		// boucle de jeu tant  qu'il reste un enemi ou un allié
		while ( getNbJoueurEquipe( 3 ) > 0 || getNbJoueurEquipe( 1 ) + getNbJoueurEquipe( 2 ) > 0 ) {
			// tour de jeu de chaque joueur
			for ( int i=0; i<ordreJoueurs.size(); i++ ) {
				
				System.out.println("ésysysysysys");
				
				// donne 1 competence utilisable
				competenceUsed = false;
				// donne les pm pour ce tour
				Game.getInstance().getGrilleTBS().giveTurnPM(ordreJoueurs.get(i));
				
				appliquerEffets(ordreJoueurs.get(i));
				
				// si c'est le joueur reel
				if ( Game.getInstance().getGrilleTBS().getJoueurs().get(ordreJoueurs.get(i)).getEquipe() == 1 ) {
					Game.getInstance().getGrilleTBS().setMyTurn(true);
					while ( Game.getInstance().getGrilleTBS().isMyTurn()) {
						System.out.println("caca");
					}
				}
				
				// si c'est un bot
				else {
					jouerTourBot( ordreJoueurs.get(i) );
				}
			}
		}
		
		System.out.println("COMBAT TERMINE");
	}
	
	private void jouerTourBot(int i) {
		System.out.println("Le bot " + i + " passe son tour.");
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
		for ( Joueur j : Game.getInstance().getGrilleTBS().getJoueurs() ) {
			if ( j.getEquipe() == i && !j.isMort()) cpt ++;
		}
		return cpt;
	}
	
	// retourne un tableau d'int avec l'ordre des joueurs
	public ArrayList<Integer> getOrdreJoueur() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		int alea;
		ArrayList<Integer> nb_restants = new ArrayList<Integer>();
		for ( int i=0; i<Game.getInstance().getGrilleTBS().getJoueurs().size(); i++ )
			nb_restants.add(i);
		for ( int i=0; i<Game.getInstance().getGrilleTBS().getJoueurs().size(); i++ ) {
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

	public boolean isCompetenceUsed() {
		return competenceUsed;
	}

	public void setCompetenceUsed(boolean competenceUsed) {
		this.competenceUsed = competenceUsed;
	}
	
	
}
