package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class GrilleTBS {

	private int[][] cases; // cases[x][y] = 0 : la case x,y est vide. 1+ = asteroide de taille 1+. -X = joueur de l'equipe X;
	private int taille;
	private ArrayList<Joueur> joueurs;
	
	public GrilleTBS(int taille, ArrayList<Joueur> joueurs) {
		cases = new int[taille][taille];
		this.taille = taille;
		this.joueurs = joueurs;
	}
	
	@Override
	public String toString() {
		String res = "\nGrilleTBS de taille " + taille + "\n- libre / x obstacle / O vaisseau\n   0 1 2 3 4 5 6 7 8 9 1011121314\n";
		for ( int j=0; j<taille; j++ ) {
			res += j;
			if ( j<10 ) res += "  ";
			else res += " ";
			for ( int i=0; i<taille; i++ ) {
				if ( cases[j][i] == 0 ) res += "- ";
				else if ( cases[j][i] <0 ) res += "O ";
				else if ( cases[j][i] >0 ) res += "x ";
			}
			res+= "\n";
		}
		return res;
	}

	// ajout des obstacles aléatoire et positionnement des joueurs
	public void generer_map() {
		Point p = new Point();
		int taille_asteroide;
		boolean isOk;
		
		//joueurs
		for(Joueur j : joueurs){
			isOk = false;
			while ( !isOk ) {
				p.x = alea(0,taille-1);
				p.y = alea(0, taille-1);
				if ( isFree(p) ) {
					j.setCoordonees(p);
					cases[p.x][p.y] = 0 - j.getEquipe();
					isOk = true;
				}
			}
		}
		
		// gros asteroides
		taille_asteroide = 3;
		for ( int i=0; i<alea(1,3); i++ ) {
			isOk = false;
			while (!isOk){
				p.x = alea(0,taille-taille_asteroide);
				p.y = alea(0, taille-taille_asteroide);
				if ( placementAsteroideOk(taille_asteroide, p) ){
					placerAsteroide ( taille_asteroide, p );
					isOk = true;
				}
			}
		}
		// asteroides moyens
		taille_asteroide = 2;
		for ( int i=0; i<alea(2,4); i++ ) {
			isOk = false;
			while (!isOk){
				p.x = alea(0,taille-taille_asteroide);
				p.y = alea(0, taille-taille_asteroide);
				if ( placementAsteroideOk(taille_asteroide, p) ){
					placerAsteroide ( taille_asteroide, p );
					isOk = true;
				}
			}
		}
		// gros asteroides
		taille_asteroide = 1;
		for ( int i=0; i<alea(6,12); i++ ) {
			isOk = false;
			while (!isOk){
				p.x = alea(0,taille-taille_asteroide);
				p.y = alea(0, taille-taille_asteroide);
				if ( placementAsteroideOk(taille_asteroide, p) ){
					placerAsteroide ( taille_asteroide, p );
					isOk = true;
				}
			}
		}
	}

	// place l'asteroide de taille t au point p
	// les verifications de dispo des cases doivent être effectuees avant
	private void placerAsteroide( int t, Point p) {
		for (int i=p.y; i<p.y+t; i++){
			for (int j=p.x; j<p.x+t; j++) {
				cases[i][j] = t;
			}
		}
	}

	// retourne true si on peut positionner un obstacle de taille t en coordonner p(x,y)
	private boolean placementAsteroideOk( int t, Point p ) {
		assert(t>=0 && t<=5);
		for (int i=p.y; i<p.y+t; i++){
			for (int j=p.x; j<p.x+t; j++) {
				if ( !isFree(new Point(i, j)) ) return false;
			}
		}	
		return true;
	}
	
	// retourn true si la case p est libre
	public boolean isFree(Point p){
		if (cases[p.x][p.y] == 0) return true;
		return false;
	}
	
	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b) {
		assert(a>b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
}
