package controller;

import java.util.ArrayList;

import model.Arme;
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
		int nb_reliques = 3;

		
		//initialisation variables
		GameOver = false;
		Arme arme;
		GenerateurBouclier generateurBouclier;
		Coque coque;
		Reacteur reacteur;
		PorteBonheur porteBonheur;
		ArrayList<ReliqueSacree> reliqueSacree = new ArrayList<ReliqueSacree>();
		
		//initialisation pieces de vaisseau
		Arme arme1 = new Arme("Laser en carton", 5, 10, 0, 0, 0, 0);
		arme1.setDescription("Un laser de merde pour un vaisseau de merde.");
		Arme arme2 = new Arme("Laser intergalactique", 10, 14, 0, 0, 3, 0);
		arme2.setDescription("Un laser convenable standart pour les vaisseaux intergalactiques. Bonus de mobilité.");
		
		GenerateurBouclier bouclier1 = new GenerateurBouclier("Generateur de creme glacee", 5, 0, 10, 0, 0, 0);
		bouclier1.setDescription("Generateur trouve dans une decheterie.");
		GenerateurBouclier bouclier2 = new GenerateurBouclier("Generateur intergalactique", 10, 0, 14, 3, 0, 0);
		bouclier2.setDescription("Un generateur de bouclier convenable standart pour les vaisseaux intergalactiques. Bonus de constitution.");
		
		Coque coque1 = new Coque("Tabarnak de coq", 5, 0, 0, 10, 0, 0);
		coque1.setDescription("Une coque inutile mais qui vous reveillera le matin, et vous fournira des oeufs frais.");
		Coque coque2 = new Coque("Coque intergalactique", 10, 0, 3, 14, 0, 0);
		coque2.setDescription("Une coque convenable standart pour les vaisseaux intergalactiques. Bonus de defense.");
		
		Reacteur reacteur1 = new Reacteur("Reacteur Diesel", 5, 0, 0, 0, 10, 0);
		reacteur1.setDescription("Certainement vole sur une voiture, peu utile sur un vaisseau");
		Reacteur reacteur2 = new Reacteur("Reacteur Intergalactique", 10, 3, 0, 0, 14, 0);
		reacteur2.setDescription("Un reacteur convenable standart pour les vaisseaux intergalactiques. Bonus d'attaque.");
		
		PorteBonheur porteBonheur1 = new PorteBonheur("Crottin de chevre", 5);
		porteBonheur1.setDescription("Friandise intergalactique provenant de la Terre.");
		PorteBonheur porteBonheur2 = new PorteBonheur("Ma gloire passee", 10);
		porteBonheur2.setDescription("Trophee de guerre d'annee de loyaux services.");
		
		ReliqueSacree relique1 = new ReliqueSacree("Momie de Toutankamon", 2, 0, 0, 2, 1);
		relique1.setDescription("Relique du celebre pharaon, bonus d'attaque, de mobiltié et de chance.");
		ReliqueSacree relique2 = new ReliqueSacree("Peignoire de DSK", 0, 0, 5, 0, 0);
		relique2.setDescription("Relique permettant de beneficier d'un gros bonus de constitution");
		ReliqueSacree relique3 = new ReliqueSacree("Lunettes de Lewis", 0, 2, 0, 0, 3);
		relique3.setDescription("Protege du soleil et augmente le swag. Bonus de défence et de chance");
		ReliqueSacree relique4 = new ReliqueSacree("Ballon de basket", 1, 1, 1, 1, 1);
		relique4.setDescription("Fait passer le temps. Bonus dans toutes les caracteristiques.");
		
		// TODO CHOIX DES COMPOSANTS DANS LE HANGAR A FAIRE ICI!!!!!!
		// en attendant on aura par default :
		reliqueSacree.add(relique1);
		reliqueSacree.add(relique2);
		reliqueSacree.add(relique3);
		reacteur = reacteur1;
		coque = coque1;
		generateurBouclier = bouclier1;
		arme = arme1;
		porteBonheur = porteBonheur1;
		// A changer en fonction des choix de l'user dans la GUI
		
		// creation du vaisseau
		Vaisseau vaisseau = new Vaisseau("Vaisseau sans nom", poidsMAX, arme, coque, reacteur, generateurBouclier, porteBonheur, reliqueSacree);
		
		//-------------------------------------TESTS------------------------------------------
		// affichage composantes du vaisseau
		System.out.println("\nTEST --VOICI LE VAISSEAU CREE-- \n" + vaisseau.toString());
		// creation grille TBS, generation aleatoire avec le vaisseau cree, affichage en terminal
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		Joueur joueur1 = new Joueur(vaisseau, 1);
		joueurs.add(joueur1);
		Joueur joueur2 = new Joueur (vaisseau, 2);
		joueurs.add(joueur2);
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
