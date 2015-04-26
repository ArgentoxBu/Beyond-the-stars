package controller;

import java.util.ArrayList;
import java.util.Scanner;

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
		// armes choisies pour la creation du vaisseau au hangar
		Arme armeChoisi;
		GenerateurBouclier generateurBouclierChoisi;
		Coque coqueChoisi;
		Reacteur reacteurChoisi;
		PorteBonheur porteBonheurChoisi;
		ArrayList<ReliqueSacree> reliqueSacreeChoisi = new ArrayList<ReliqueSacree>();
		// les listes suivantes contiennent les objets de chaques type qui sont dispos au début du jeu
		ArrayList<Arme> armeDispo = new ArrayList<Arme>();
		ArrayList<GenerateurBouclier> generateurBouclierDispo = new ArrayList<GenerateurBouclier>();
		ArrayList<Coque> coqueDispo = new ArrayList<Coque>();
		ArrayList<Reacteur> reacteurDispo = new ArrayList<Reacteur>();
		ArrayList<PorteBonheur> porteBonheurDispo = new ArrayList<PorteBonheur>();
		ArrayList<ReliqueSacree> reliqueSacreeDispo = new ArrayList<ReliqueSacree>();
		// les listes suivantes comprennent les objets qui seront droppables en jeu
		ArrayList<Arme> armeDroppable = new ArrayList<Arme>();
		ArrayList<GenerateurBouclier> generateurBouclierDroppable = new ArrayList<GenerateurBouclier>();
		ArrayList<Coque> coqueDroppable = new ArrayList<Coque>();
		ArrayList<Reacteur> reacteurDroppable = new ArrayList<Reacteur>();
		ArrayList<PorteBonheur> porteBonheurDroppable = new ArrayList<PorteBonheur>();
		ArrayList<ReliqueSacree> reliqueSacreeDroppable = new ArrayList<ReliqueSacree>();
		
		//initialisation pieces de vaisseau
		Arme arme;
		arme = new Arme("Laser en carton", 5, 10, 0, 0, 0, 0);
		arme.setDescription("Un laser de merde pour un vaisseau de merde.");
		armeDispo.add(arme);
		arme = new Arme("Laser intergalactique", 10, 14, 0, 0, 3, 0);
		arme.setDescription("Un laser convenable standart pour les vaisseaux intergalactiques. Bonus de mobilité.");
		armeDispo.add(arme);
		assert ( armeDispo.isEmpty() );
		
		GenerateurBouclier bouclier;
		bouclier = new GenerateurBouclier("Generateur de creme glacee", 5, 0, 10, 0, 0, 0);
		bouclier.setDescription("Generateur trouve dans une decheterie.");
		generateurBouclierDispo.add(bouclier);
		bouclier = new GenerateurBouclier("Generateur intergalactique", 10, 0, 14, 3, 0, 0);
		bouclier.setDescription("Un generateur de bouclier convenable standart pour les vaisseaux intergalactiques. Bonus de constitution.");
		generateurBouclierDispo.add(bouclier);
		assert ( generateurBouclierDispo.isEmpty() );
		
		Coque coque;
		coque = new Coque("Tabarnak de coq", 5, 0, 0, 10, 0, 0);
		coque.setDescription("Une coque inutile mais qui vous reveillera le matin, et vous fournira des oeufs frais.");
		coqueDispo.add(coque);
		coque = new Coque("Coque intergalactique", 10, 0, 3, 14, 0, 0);
		coque.setDescription("Une coque convenable standart pour les vaisseaux intergalactiques. Bonus de defense.");
		coqueDispo.add(coque);
		assert ( coqueDispo.isEmpty() );
		
		Reacteur reacteur;
		reacteur = new Reacteur("Reacteur Diesel", 5, 0, 0, 0, 10, 0);
		reacteur.setDescription("Certainement vole sur une voiture, peu utile sur un vaisseau");
		reacteurDispo.add(reacteur);
		reacteur = new Reacteur("Reacteur Intergalactique", 10, 3, 0, 0, 14, 0);
		reacteur.setDescription("Un reacteur convenable standart pour les vaisseaux intergalactiques. Bonus d'attaque.");
		reacteurDispo.add(reacteur);
		assert ( reacteurDispo.isEmpty() );
		
		PorteBonheur porteBonheur;
		porteBonheur = new PorteBonheur("Crottin de chevre", 5);
		porteBonheur.setDescription("Friandise intergalactique provenant de la Terre.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheur = new PorteBonheur("Ma gloire passee", 10);
		porteBonheur.setDescription("Trophee de guerre d'annee de loyaux services.");
		porteBonheurDispo.add(porteBonheur);
		assert ( porteBonheurDispo.isEmpty() );
		
		ReliqueSacree relique;
		relique = new ReliqueSacree("Momie de Toutankamon", 2, 0, 0, 2, 1);
		relique.setDescription("Relique du celebre pharaon, bonus d'attaque, de mobiltié et de chance.");
		reliqueSacreeDispo.add(relique);
		relique = new ReliqueSacree("Peignoire de DSK", 0, 0, 5, 0, 0);
		relique.setDescription("Relique permettant de beneficier d'un gros bonus de constitution");
		reliqueSacreeDispo.add(relique);
		relique = new ReliqueSacree("Lunettes de Lewis", 0, 2, 0, 0, 3);
		relique.setDescription("Protege du soleil et augmente le swag. Bonus de défence et de chance");
		reliqueSacreeDispo.add(relique);
		relique = new ReliqueSacree("Ballon de basket", 1, 1, 1, 1, 1);
		relique.setDescription("Fait passer le temps. Bonus dans toutes les caracteristiques.");
		reliqueSacreeDispo.add(relique);
		assert ( reliqueSacreeDispo.size() < 3 );
		
		// TODO CHOIX DES COMPOSANTS DANS LE HANGAR A FAIRE ICI!!!!!!
		// en attendant on aura par default :
		reliqueSacreeChoisi.add(reliqueSacreeDispo.get(0));
		reliqueSacreeChoisi.add(reliqueSacreeDispo.get(1));
		reliqueSacreeChoisi.add(reliqueSacreeDispo.get(2));
		reacteurChoisi = reacteurDispo.get(0);
		coqueChoisi = coqueDispo.get(0);
		generateurBouclierChoisi = generateurBouclierDispo.get(0);
		armeChoisi = armeDispo.get(0);
		porteBonheurChoisi = porteBonheurDispo.get(0);
		// A changer en fonction des choix de l'user dans la GUI
		
		// creation du vaisseau
		assert ( reliqueSacreeChoisi.size() > nb_reliques );
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
		Scanner scan = new Scanner(System.in);
		int xo = scan.nextInt();
		int yo = scan.nextInt();
		int xf = scan.nextInt();
		int yf = scan.nextInt();
		boolean b = grille.shortestPath(xo, yo, xf, yf);
		System.out.println(b);
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
