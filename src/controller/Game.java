package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Arme;
import model.ConteneurObjetsVaisseau;
import model.Coque;
import model.GenerateurBouclier;
import model.GrilleTBS;
import model.Joueur;
import model.PorteBonheur;
import model.Reacteur;
import model.ReliqueSacree;
import model.Vaisseau;


public class Game extends Thread {

	boolean GameOver;
	private ConteneurObjetsVaisseau c;

	// armes choisies pour la creation du vaisseau au hangar
	private Arme armeChoisi;
	private GenerateurBouclier generateurBouclierChoisi;
	private Coque coqueChoisi;
	private Reacteur reacteurChoisi;
	private PorteBonheur porteBonheurChoisi;
	private ArrayList<ReliqueSacree> reliqueSacreeChoisi;
	
	public Game() {
		// variables personalisables
		int poidsMAX = 30;

		//initialisation variables
		GameOver = false;
		c = new ConteneurObjetsVaisseau();
		
		reliqueSacreeChoisi = new ArrayList<ReliqueSacree>();
		
		// TODO CHOIX DES COMPOSANTS DANS LE HANGAR A FAIRE ICI!!!!!!
		// en attendant on aura par default :
		reliqueSacreeChoisi.add(c.reliqueSacreeDispo.get(0));
		reliqueSacreeChoisi.add(c.reliqueSacreeDispo.get(1));
		reliqueSacreeChoisi.add(c.reliqueSacreeDispo.get(2));
		reacteurChoisi = c.reacteurDispo.get(0);
		coqueChoisi = c.coqueDispo.get(0);
		generateurBouclierChoisi = c.generateurBouclierDispo.get(0);
		armeChoisi = c.armeDispo.get(0);
		porteBonheurChoisi = c.porteBonheurDispo.get(0);
		// A changer en fonction des choix de l'user dans la GUI
		
		// creation du vaisseau
		Vaisseau vaisseau = new Vaisseau("Vaisseau sans nom", poidsMAX, armeChoisi, coqueChoisi, reacteurChoisi, generateurBouclierChoisi, porteBonheurChoisi, reliqueSacreeChoisi);
		
		
		// -------------------------------------------------------
		//                          TESTS
		// -------------------------------------------------------
		System.out.println("----------------- AFFICHAGE DES TESTS ------------------");
		// affichage composantes du vaisseau
		System.out.println("\n" + vaisseau.toString());
		// creation grille TBS, generation aleatoire avec le vaisseau cree, affichage en terminal
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		Joueur joueur1 = new Joueur(vaisseau, 1);
		joueurs.add(joueur1);
		GrilleTBS grille = new GrilleTBS(15, joueurs);
		grille.generer_map();
		System.out.println(grille.toString());

		/* PUTIN DE TEST DE FUCKING ABDOU DE MES DEUX!!!!!
		Scanner scan = new Scanner(System.in);
		int xo = scan.nextInt();
		int yo = scan.nextInt();
		int xf = scan.nextInt();
		int yf = scan.nextInt();
		boolean b = grille.shortestPath(xo, yo, xf, yf);
		System.out.println(b);
		*/

		String test = "abdou a putin d'oublie d'enlever son putin de tabarnak de calisse d'osti de scan cet enfoir�! Il me le payera! haha";
		test=reforme(test, 25);
		System.out.println(test);
		
		// -------------------------------------------------------
		//                       FIN DES TESTS
		// -------------------------------------------------------
	}
	
	// Cadeau pour Loic, entre la chaine et le nombre de carac max par ligne
	// d�place l� o� tu en as besoin et supprime l� d'ici!
	public String reforme( String s, int cut ) {
		if ( cut > s.length()-1 ) return s;
		String res = "";
		int parser = cut;
		int pasted = 0;
		while ( parser < s.length()-1 ) {
			while ( s.charAt(parser) != ' ' && parser > 0 ) {
				parser--;
				assert ( parser > 0 );
			}
			res += s.subSequence(pasted, parser) + "\n";
			pasted = parser+1;
			parser += cut;
			if ( parser > s.length()-1 ) return res + s.substring(parser-cut+1, s.length());
		}
		return res;
	}
	
	@Override
	public void run() {
		/*
		while (!GameOver) {
			
		}
		*/
		System.out.println("\nAttention : Le while(!GameOver) est desactive pour le moment");
	}
	
	
	
	
	// --------------------------------------------------
	//                GETTERS / SETTERS
	// --------------------------------------------------
	
	public boolean isGameOver() {
		return GameOver;
	}

	public void setGameOver(boolean gameOver) {
		GameOver = gameOver;
	}

	public ConteneurObjetsVaisseau getConteneurObjetsVaisseau() {
		return c;
	}

	public void setConteneurObjetsVaisseau(ConteneurObjetsVaisseau c) {
		this.c = c;
	}

	public Arme getArmeChoisi() {
		return armeChoisi;
	}

	public void setArmeChoisi(Arme armeChoisi) {
		this.armeChoisi = armeChoisi;
	}

	public GenerateurBouclier getGenerateurBouclierChoisi() {
		return generateurBouclierChoisi;
	}

	public void setGenerateurBouclierChoisi(
			GenerateurBouclier generateurBouclierChoisi) {
		this.generateurBouclierChoisi = generateurBouclierChoisi;
	}

	public Coque getCoqueChoisi() {
		return coqueChoisi;
	}

	public void setCoqueChoisi(Coque coqueChoisi) {
		this.coqueChoisi = coqueChoisi;
	}

	public Reacteur getReacteurChoisi() {
		return reacteurChoisi;
	}

	public void setReacteurChoisi(Reacteur reacteurChoisi) {
		this.reacteurChoisi = reacteurChoisi;
	}

	public PorteBonheur getPorteBonheurChoisi() {
		return porteBonheurChoisi;
	}

	public void setPorteBonheurChoisi(PorteBonheur porteBonheurChoisi) {
		this.porteBonheurChoisi = porteBonheurChoisi;
	}

	public ArrayList<ReliqueSacree> getReliqueSacreeChoisi() {
		return reliqueSacreeChoisi;
	}

	public void setReliqueSacreeChoisi(ArrayList<ReliqueSacree> reliqueSacreeChoisi) {
		this.reliqueSacreeChoisi = reliqueSacreeChoisi;
	}
}
