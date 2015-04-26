package controller;

import java.util.ArrayList;

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

	public Game() {
		// variables personalisables
		int poidsMAX = 30;

		//initialisation variables
		GameOver = false;
		ConteneurObjetsVaisseau c = new ConteneurObjetsVaisseau();
		
		// armes choisies pour la creation du vaisseau au hangar
		Arme armeChoisi;
		GenerateurBouclier generateurBouclierChoisi;
		Coque coqueChoisi;
		Reacteur reacteurChoisi;
		PorteBonheur porteBonheurChoisi;
		ArrayList<ReliqueSacree> reliqueSacreeChoisi = new ArrayList<ReliqueSacree>();
		
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
		
		//-------------------------------------TESTS------------------------------------------
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
	}

	@Override
	public void run() {
		/*
		while (!GameOver) {
			
		}
		*/
		System.out.println("\nAttention : Le while(!GameOver) est desactive pour le moment");
	}
}
