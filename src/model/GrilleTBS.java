package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class GrilleTBS {

	private int[][] cases; // cases[x][y] = 0 : la case x,y est vide. 1+ = asteroide de taille 1+. -X = joueur de l'equipe X;
	private int taille; // taille de la map
	private ArrayList<Joueur> joueurs; // les joueurs sur la map
	private LinkedList<PathStep> stepQueue = null;
	
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
				if ( cases[i][j] == 0 ) res += "- ";
				else if ( cases[i][j] <0 ) res += "O ";
				else if ( cases[i][j] >0 ) res += "x ";
			}
			res+= "\n";
		}
		return res;
	}
	
	// ------------------ MODIF DES JOUEURS -----------------------
	
	// blesse de x degats le joueur à l'index i
	public void blesserJoueur(int i, int x) {
		Joueur joueur = joueurs.get(i);
		joueur.setNbPointVie(joueur.getNbPointVie()-x);
		joueurs.set(i, joueur);
	}
	
	// enleve le joueur à l'index i
	public void tuerJoueur( int i ) {
		Joueur joueur = joueurs.get(i);
		joueur.setMort(true);
		joueurs.set(i, joueur);
	}
	
	// duree-- de l'effet n°i du joueur n°j
	public void baisserDureeEffets( int i, int j ) {
		if ( joueurs.get(i).getEffets().get(j).getDuree() <= 1 )
			removeEffectFromJoueur( i, j );
		else {
			Joueur joueur = joueurs.get(j);
			ArrayList<CombatEffect> effets = joueur.getEffets();
			CombatEffect effet = effets.get(i);
			
			effet.setDuree(effet.getDuree()-1);
			effets.set(i, effet);
			joueur.setEffets(effets);
			joueurs.set(j, joueur);
		}
	}
	
	//enlever l'effet à l'index i du joueur à l'index j
	public void removeEffectFromJoueur ( int i, int j ) {
		Joueur joueur = joueurs.get(j);
		ArrayList<CombatEffect> effets = joueur.getEffets();
		effets.remove(i);
		joueur.setEffets(effets);
		joueurs.set(j, joueur);
	}
	
	// ------------------ FIN MODIF DES JOUEURS -----------------------
	
	// ajout des obstacles aléatoire et positionnement des joueurs
	public void generer_map() {
		Point p = new Point();
		int taille_asteroide;
		boolean isOk;
		
		//joueurs
		for(int i=0; i<joueurs.size(); i++ ) {
			Joueur j;
			isOk = false;
			int compteur_max = 0;
			while ( !isOk ) {
				p.setLocation(alea(0,taille-1),alea(0,taille-1));
				if ( isFree(p) ) {
					// si ya d'autres joueurs, on verifie qu'un chemin existe
					if ( i > 0 ) {
						if ( existingPath(p, joueurs.get(i-1).getCoordonees())) {
							j = joueurs.get(i);
							j.setCoordonees(new Point(p.x, p.y));
							joueurs.set(i, j);
							cases[p.x][p.y] = 0 - j.getEquipe();
							isOk = true;
						}
					}
					// si ya pas d'autres joueurs c'est ok
					else {
						j = joueurs.get(i);
						j.setCoordonees(new Point(p.x, p.y));
						joueurs.set(i, j);
						cases[p.x][p.y] = 0 - j.getEquipe();
						isOk = true;
					}
				}
				if ( !isOk ) compteur_max++;
				if ( compteur_max > 50 ) {
					System.out.println("Probleme lors de la creation de la map : retentative de placement des joueurs.");
					compteur_max = 0;
					i = 0;
					viderGrille();
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
		// petits asteroides
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

	// si reinitialisation sans regénération
	private void viderGrille() {
		for ( int j=0; j<taille; j++){
			for (int i=0; i<taille; i++ ) cases[i][j] = 0;
		}
	}

	// place l'asteroide de taille t au point p
	// les verifications de dispo des cases doivent être effectuees avant
	private void placerAsteroide( int t, Point p) {
		for (int j=p.y; j<p.y+t; j++){
			for (int i=p.x; i<p.x+t; i++) {
				cases[i][j] = t;
			}
		}
	}

	// retourne true si on peut positionner un obstacle de taille t en coordonner p(x,y)
	private boolean placementAsteroideOk( int t, Point p ) {
		assert(t>=0 && t<=5);
		for (int j=p.y; j<p.y+t; j++){
			for (int i=p.x; i<p.x+t; i++) {
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
	
	// retourne true si un chemin du point a au point b existe.
	public boolean existingPath(Point a, Point b){
		return shortestPath(a.x, a.y, b.x, b.y);
	}
	
	// retourne le nombre de cases entre le points A et B
	public int nbCasesEntrePoints ( Point a, Point b ){
		return absolu(a.x-b.x)+absolu(a.y-b.y);
	}
	
	// retourne une liste des points ou il est possible que le joueur j se déplace. les obstacles sont pris en compte.
	public ArrayList<Point> getDeplacementCases ( Joueur joueur ){
		int dist;
		ArrayList<Point> res = new ArrayList<Point>();
		for ( int j=0; j<taille; j++ ){
			for ( int i=0; i<taille; i++){
				dist = nbCasesEntrePoints( new Point(i, j), joueur.getCoordonees() );
				if ( dist > 0 && dist <= joueur.getVaisseau().calculerPM() ) {
					if ( cases[i][j] == 0 ) {
						res.add(new Point(i, j));
					}
				}
			}
		}
		return res;
	}
	
	// deplace le joueur j au point p
	public void deplacerJoueur( Joueur j, Point p ) {
		j.setCoordonees(p);
	}
	
	
	// retourne la valeur absolue de a
	private int absolu( int a ){
		if ( a < 0 ) return -a;
		return a;
	}
	
	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b) {
		assert(a<=b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
	
	
	// *************************************************************************
	
	class PathStep {
		int i, j;
		PathStep prev;

		public PathStep(int i, int j, PathStep prev) {
			this.i = i;
			this.j = j;
			this.prev = prev;
		}

		public String toString() {
			return "[" + i + ", " + j + "]";
		}
	}

	
	public boolean shortestPath(int xo, int yo, int xf, int yf) {
		int N = cases.length;		
		PathStep step = new PathStep(xo, yo, null);

		//Creer une matrice convenable
		int grid[][] = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(cases[i][j] == 0)
					grid[i][j] = 1;
				else if(cases[i][j] < 0)
					grid[i][j] = 1;
				else
					grid[i][j] = 0;
				
				if(i == xf && j == yf)
					grid[i][j] = 3;
			}
		}
		
		stepQueue = new LinkedList<PathStep>();
		stepQueue.add(step);

		// case visité
		HashSet<Integer> set = new HashSet<Integer>();
		boolean findDest = false;
		while(!stepQueue.isEmpty() && !findDest) {
			LinkedList<PathStep> tmpQueue = new LinkedList<PathStep>();
			while(!stepQueue.isEmpty()) {
				step = stepQueue.remove();
				int i = step.i, j = step.j, id;
				if(grid[i][j] == 3) {
					findDest = true;
					break;
				}
				
				PathStep next;
				// gauche
				if(j > 0 && grid[i][j - 1] != 0) {
					id = N * i + (j - 1);
					if(!set.contains(id)) {
						set.add(id);
						next = new PathStep(i, j - 1, step);
						tmpQueue.add(next);
					}
				}
				// droite
				if(j < N - 1 && grid[i][j + 1] != 0) {
					id = N * i + (j + 1);
					if(!set.contains(id)) {
						set.add(id);
						next = new PathStep(i, j + 1, step);
						tmpQueue.add(next);
					}
				}
				// haut
				if(i > 0 && grid[i - 1][j] != 0) {
					id = N * (i - 1) + j;
					if(!set.contains(id)) {
						set.add(id);
						next = new PathStep(i - 1, j, step);
						tmpQueue.add(next);
					}
				}
				// bas
				if(i < N - 1 && grid[i + 1][j] != 0) {
					id = N * (i + 1) + j;
					if(!set.contains(id)) {
						set.add(id);
						next = new PathStep(i + 1, j, step);
						tmpQueue.add(next);
					}
				}
			}
			stepQueue = tmpQueue;
		}
		if(findDest) {
			// le chemin
			ArrayList<PathStep> path = new ArrayList<PathStep>();
			while(step != null) {
				path.add(step);
				step = step.prev;
			}
			Collections.reverse(path);

			for(int i = 0; i < path.size(); i++) {
				if(i == path.size() - 1) {
					System.out.println(path.get(i));
				}
				else {
					System.out.print(path.get(i) + " -> ");
				}
			}
			
			return true;
		} else {
			return false;
		}
	}

	// GETTERS SETTERS
	
	public LinkedList<PathStep> getStepQueue() {
		return stepQueue;
	}

	public int[][] getCases() {
		return cases;
	}

	public void setCases(int[][] cases) {
		this.cases = cases;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
}
