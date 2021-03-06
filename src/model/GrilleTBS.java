package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Collections;

public class GrilleTBS {

	private int[][] cases; // cases[x][y] = 0 : la case x,y est vide. 1+ = asteroide de taille 1+. -X = joueur de l'equipe X;
	private int taille; // taille de la map
	private ArrayList<Joueur> joueurs; // les joueurs sur la map
	private ArrayList<PathStep> path = null;
	
	private boolean myTurn; // true = tour du joueur reel
	private Combat combat;

	public GrilleTBS(int taille, ArrayList<Joueur> joueurs) {
		cases = new int[taille][taille];
		this.taille = taille;
		this.joueurs = joueurs;
		myTurn = false;
		combat = new Combat();
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
				else if ( cases[i][j] <0 ) res += "" + -cases[i][j] + " ";
				else if ( cases[i][j] >0 ) res += "x ";
			}
			res+= "\n";
		}
		return res;
	}
	
	public int getIndexJoueurCase ( Point p ) {
		
		for (int i=0; i<joueurs.size(); i++) {
			Point point = joueurs.get(i).getCoordonees();
			if ( point.x == p.x && point.y == p.y ) {
				return i;
			}
		}
		return -1;
	}
	
	// ------------------ MODIF DES JOUEURS -----------------------

	// blesse de x degats le joueur � l'index i
	public void blesserJoueur(int i, int x) {
		Joueur joueur = joueurs.get(i);
		joueur.setNbPointVie(joueur.getNbPointVie()-x);
		joueurs.set(i, joueur);
	}

	public void addEffectJoueur ( int i, CombatEffect effet ) {
		Joueur joueur = joueurs.get(i);
		ArrayList<CombatEffect> effets = joueur.getEffets();
		effets.add(effet);
		joueur.setEffets(effets);
		joueurs.set(i, joueur);
	}
	
	// enleve le joueur � l'index i
	public void tuerJoueur( int i ) {
		Joueur joueur = joueurs.get(i);
		joueur.setMort(true);
		joueurs.set(i, joueur);
	}

	// duree-- de l'effet n�i du joueur n�j
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

	// deplace le joueur j au point p
	public void deplacerJoueur( int joueur , Point p ) {
		Joueur j = joueurs.get(joueur);
		Point old = j.getCoordonees();
		int tmp = cases[old.x][old.y];
		cases[old.x][old.y] = cases[p.x][p.y];
		cases[p.x][p.y] = tmp;
		j.setNbPointMvt(j.getNbPointMvt()-nbCasesEntrePoints(old, p));
		j.setCoordonees(p);
	}

	//enlever l'effet � l'index i du joueur � l'index j
	public void removeEffectFromJoueur ( int i, int j ) {
		Joueur joueur = joueurs.get(j);
		ArrayList<CombatEffect> effets = joueur.getEffets();
		effets.remove(i);
		joueur.setEffets(effets);
		joueurs.set(j, joueur);
	}

	public void giveTurnPM( int joueur ) {
		Joueur j = joueurs.get(joueur);
		j.setNbPointMvt(joueurs.get(joueur).getTurnPM());
		joueurs.set(joueur, j);
	}
	
	// ------------------ FIN MODIF DES JOUEURS -----------------------

	// ajout des obstacles al�atoire et positionnement des joueurs
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

	// si reinitialisation sans reg�n�ration
	private void viderGrille() {
		for ( int j=0; j<taille; j++){
			for (int i=0; i<taille; i++ ) cases[i][j] = 0;
		}
	}

	// place l'asteroide de taille t au point p
	// les verifications de dispo des cases doivent �tre effectuees avant
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
	
	// retourne une liste des points ou il est possible que le joueur j se d�place. les obstacles sont pris en compte.
	public ArrayList<Point> getDeplacementCases ( Joueur joueur ){
		int dist;
		ArrayList<Point> res = new ArrayList<Point>();
		for ( int j=0; j<taille; j++ ){
			for ( int i=0; i<taille; i++){
				dist = nbCasesEntrePoints( new Point(i, j), joueur.getCoordonees() );
				// VERIF a faire ici pour ne pas passer � travers les obstacles
				if ( dist > 0 && dist <= joueur.getNbPointMvt() ) {
					if ( cases[i][j] == 0 ) {
						res.add(new Point(i, j));
					}
				}
			}
		}
		return res;
	}

	public ArrayList<Point> getCompetenceCases(Joueur monJoueur, Competence maCompetence)
	{
		if(maCompetence.getType() == "attackLigne"){
			return getCompetenceCasesLigneDroite(monJoueur, maCompetence);
		}
		else
		{
			return getCompetenceCasesPluridir(monJoueur, maCompetence);
		}
	}

	public ArrayList<Point> getCompetenceCasesPluridir(Joueur monJoueur, Competence maCompetence)
	{
		ArrayList<Point> casesAPortee = new ArrayList<Point>();
		ArrayList<Point> obstacleDiag = new ArrayList<Point>();

		int dist;
		
		int monXDepart = monJoueur.getCoordonees().x;
		int monYDepart = monJoueur.getCoordonees().y;

		int porteeMin = maCompetence.getPorteeMini();
		int porteeMax = maCompetence.getPorteeMaxi();

		for ( int j=0; j<taille; j++ ){
			for ( int i=0; i<taille; i++){
				dist = nbCasesEntrePoints( new Point(i, j), monJoueur.getCoordonees());
				if(dist <= porteeMax && dist >= porteeMin)
				{
					casesAPortee.add(new Point(i,j));
				}
			}
		}

		Iterator<Point> iterator = casesAPortee.iterator();
		while (iterator.hasNext()) {
			Point P = iterator.next();
			if(cases[P.x][P.y]>0){
				obstacleDiag.add(new Point((int)((P.x-0.5)*2),(int)((P.y-0.5)*2)));
				obstacleDiag.add(new Point((int)((P.x+0.5)*2),(int)((P.y+0.5)*2)));
				obstacleDiag.add(new Point((int)((P.x-0.5)*2),(int)((P.y+0.5)*2)));
				obstacleDiag.add(new Point((int)((P.x+0.5)*2),(int)((P.y-0.5)*2)));
				iterator.remove();
			}
		}

		//		Iterator<Point> iterator2 = casesAPortee.iterator();
		//		Iterator<Point> iterator3;
		//		ArrayList<Point> del = new ArrayList<Point>();
		//		while (iterator2.hasNext()) {
		//			Point P = iterator2.next();
		//			
		//			iterator3 = obstacleDiag.iterator();
		//			
		//			while (iterator3.hasNext()) {
		//				Point P1 = iterator3.next();
		//				Point P2 = iterator3.next();
		//				if(intersection(monXDepart,monYDepart,P.x,P.y,P1.x,P1.y,P2.x,P2.y))
		//				{
		//					del.add(P);
		//				}
		//			}			
		//		}
		//		casesAPortee.removeAll(del);

		return casesAPortee;
	}

	public ArrayList<Point> getCompetenceCasesLigneDroite(Joueur monJoueur, Competence maCompetence)
	{
		ArrayList<Point> casesAPortee = new ArrayList<Point>();
		ArrayList<Point> obstacleDiag = new ArrayList<Point>();

		int dist;

		int monXDepart = monJoueur.getCoordonees().x;
		int monYDepart = monJoueur.getCoordonees().y;

		int porteeMin = maCompetence.getPorteeMini();
		int porteeMax = maCompetence.getPorteeMaxi();

		int porteePosXMax = 15;
		int porteePosYMax = 15;
		int porteeNegXmax = 15;
		int porteeNegYMax = 15;

//		for ( int j=0; j<taille; j++ ){
//			for ( int i=0; i<taille; i++){
//				dist = nbCasesEntrePoints( new Point(i, j), monJoueur.getCoordonees());
//				if(dist <= porteeMax && dist >= porteeMin)
//				{
//					del.add(P);
//				}
//			}
//		}

		for(int i = monXDepart+porteeMin;i<=monXDepart+porteeMax;i++){
			if(i<15){
				if(cases[i][monYDepart]==0){
					casesAPortee.add(new Point(i,monYDepart));
				}
				else if(cases[i][monYDepart]<0)
				{
					casesAPortee.add(new Point(i,monYDepart));
					break;
				}
				else
				{
					break;
				}
			}
		}
		
		for(int i = monYDepart+porteeMin;i<=monYDepart+porteeMax;i++){
			if(i<15){
				if(cases[monXDepart][i]==0){
					casesAPortee.add(new Point(monXDepart,i));
				}
				else if(cases[monXDepart][i]<0)
				{
					casesAPortee.add(new Point(monXDepart,i));
					break;
				}
				else
				{
					break;
				}
			}
		}


		for(int i = monXDepart-porteeMin;i>=monXDepart-porteeMax;i--){
			if(i>=0){
				if(cases[i][monYDepart]==0){
					casesAPortee.add(new Point(i,monYDepart));
				}
				else if(cases[i][monYDepart]<0)
				{
					casesAPortee.add(new Point(i,monYDepart));
					break;
				}
				else
				{
					break;
				}
			}
		}


		for(int i = monYDepart-porteeMin;i>=monYDepart-porteeMax;i--){
			if(i>=0){
				if(cases[monXDepart][i]==0){
					casesAPortee.add(new Point(monXDepart,i));
				}
				else if(cases[monXDepart][i]<0)
				{
					casesAPortee.add(new Point(monXDepart,i));
					break;
				}
				else
				{
					break;
				}
			}
		}

//		System.out.println("porteePosXMax : "+porteePosXMax+"porteePosYMax : "+porteePosYMax+"porteeNegXmax : "+porteeNegXmax+"porteeNegYMax : "+porteeNegYMax);
//		
//		Iterator<Point> iterator = casesAPortee.iterator();
//		while (iterator.hasNext()) {
//			Point P = iterator.next();
//			if(P.x+monXDepart>porteePosXMax || P.y+monYDepart>porteePosYMax 
//					|| P.x-monXDepart>porteeNegXmax || P.y-monYDepart>porteeNegYMax){
//				iterator.remove();
//			}
//		}
//		casesAPortee.removeAll(del);

		return casesAPortee;
	}

	public Boolean intersection( double x1,double y1,double x2,double y2, double x3, double y3, double x4,double y4 ) 
	{ 
		double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4); 
		if (d == 0) return false; 
		double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d; 
		double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

		if(x3==x4) { 
			if ( yi < Math.min(y1,y2) || yi > Math.max(y1,y2) )return false; 
		} 

		if (xi < Math.min(x1,x2) || xi > Math.max(x1,x2)) return false; 
		if (xi < Math.min(x3,x4) || xi > Math.max(x3,x4)) return false; 

		return true; 
	}

	// retourne la valeur absolue de a
	private int absolu( int a ){
		if ( a < 0 ) return -a;
		return a;
	}

	//retourne un int al�atoire entre a et b compris
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

		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}

		public PathStep getPrev() {
			return prev;
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

		LinkedList<PathStep> stepQueue = new LinkedList<PathStep>();
		stepQueue.add(step);

		// case visit�
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
			path = new ArrayList<PathStep>();
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

	public Combat getCombat() {
		return combat;
	}

	public void setCombat(Combat combat) {
		this.combat = combat;
	}
	
	public ArrayList<PathStep> getStepQueue() {
		return path;
	}

	public int[][] getCases() {
		return cases;
	}

	public int getValeurCase( Point p ) {
		return cases[p.x][p.y];
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

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}	
}
