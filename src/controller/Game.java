package controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

import Gameview.AightMusic;
import Gameview.Hangar2View;
import Gameview.HangarView;
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
	private int poidsMAX;
	private GrilleTBS grilleTBS;
	private ArrayList<Joueur> joueurs;
	private AightMusic musicActu;
	
	public Game() {
		// variables personalisables
		poidsMAX = 30;

		//initialisation variables
		GameOver = false;
		c = new ConteneurObjetsVaisseau();
		
		reliqueSacreeChoisi = new ArrayList<ReliqueSacree>();

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
		
		// TEMPORAIRE : CREATION GRILLE TBS AVEC LE VAISSEAU, UN ALLIE ET UN ENNEMI
		Joueur joueur;
		joueurs = new ArrayList<Joueur>();
		joueur = new Joueur(vaisseau, 1);
		joueurs.add(joueur);
		joueur = new Joueur(vaisseau, 1);
		joueurs.add(joueur);
		joueur = new Joueur(vaisseau, 2);
		joueurs.add(joueur);
		grilleTBS = new GrilleTBS(15, joueurs);
		grilleTBS.generer_map();
		
		// -------------------------------------------------------
		//                          TESTS
		// -------------------------------------------------------
		
		// lancement de la zik
		// musique aléatoire lolol
		musicActu = new AightMusic();
		//aightMusic.balancer();
		
		System.out.println("----------------- AFFICHAGE DES TESTS ------------------");
		// affichage composantes du vaisseau
		System.out.println("\n" + vaisseau.toString());
		// creation grille TBS, generation aleatoire avec le vaisseau cree, affichage en terminal

		System.out.println(grilleTBS.toString());

		/* PUTIN DE TEST DE FUCKING ABDOU DE MES DEUX!!!!!
		Scanner scan = new Scanner(System.in);
		int xo = scan.nextInt();
		int yo = scan.nextInt();
		int xf = scan.nextInt();
		int yf = scan.nextInt();
		boolean b = grille.shortestPath(xo, yo, xf, yf);
		System.out.println(b);
		*/
		
		// -------------------------------------------------------
		//                       FIN DES TESTS
		// -------------------------------------------------------
	}
	
	@Override
	public void run() {
		musicActu.balancer();

		String Etat = "Hangar";
		
		RenderWindow RenderWind = new RenderWindow(new VideoMode(800, 600, 32), "Hangar",WindowStyle.CLOSE);
		
		while(Etat!="EndGame")
		{
			switch(Etat){

				case "Hangar" :
					HangarView monHangar = new HangarView(this, RenderWind);
					Etat = monHangar.run();
					break;
					
				case "Hangar2" :
					Hangar2View monHangar2 = new Hangar2View(this, RenderWind);
					Etat = monHangar2.run();
					break;
			}
		}
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
	
	public int getPoidsMax(){
		return poidsMAX;
	}

	public int getPoidsMAX() {
		return poidsMAX;
	}

	public void setPoidsMAX(int poidsMAX) {
		this.poidsMAX = poidsMAX;
	}

	public GrilleTBS getGrilleTBS() {
		return grilleTBS;
	}

	public void setGrilleTBS(GrilleTBS grilleTBS) {
		this.grilleTBS = grilleTBS;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}	
}
