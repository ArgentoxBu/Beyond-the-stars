package model;

import java.util.ArrayList;

// cette classe est vitale
// elle possede tous les objets droppables / dispos et compétences du jeu.
// elle est initialisée lors du démarrage du jeu et possède les méthodes d'initialisation des listes d'accès

public class ConteneurObjetsVaisseau {
	
	// les listes suivantes contiennent les objets de chaques type qui sont dispos au début du jeu
	public ArrayList<Arme> armeDispo = new ArrayList<Arme>();
	public ArrayList<GenerateurBouclier> generateurBouclierDispo = new ArrayList<GenerateurBouclier>();
	public ArrayList<Coque> coqueDispo = new ArrayList<Coque>();
	public ArrayList<Reacteur> reacteurDispo = new ArrayList<Reacteur>();
	public ArrayList<PorteBonheur> porteBonheurDispo = new ArrayList<PorteBonheur>();
	public ArrayList<ReliqueSacree> reliqueSacreeDispo = new ArrayList<ReliqueSacree>();
	
	// les listes suivantes comprennent les objets qui seront droppables en jeu
	public ArrayList<Arme> armeDroppable = new ArrayList<Arme>();
	public ArrayList<GenerateurBouclier> generateurBouclierDroppable = new ArrayList<GenerateurBouclier>();
	public ArrayList<Coque> coqueDroppable = new ArrayList<Coque>();
	public ArrayList<Reacteur> reacteurDroppable = new ArrayList<Reacteur>();
	public ArrayList<PorteBonheur> porteBonheurDroppable = new ArrayList<PorteBonheur>();
	public ArrayList<ReliqueSacree> reliqueSacreeDroppable = new ArrayList<ReliqueSacree>();
	
	public ConteneurObjetsVaisseau() {
	
		//CREATION ET AJOUT DES PIECES
		
		// ---------ARMES---------
		Arme arme;
		
		arme = new Arme("Laser en carton", 5, 10, 0, 0, 0, 0);
		arme.setDescription("Un laser de merde pour un vaisseau de merde.");
		armeDispo.add(arme);
		armeDroppable.add(arme);
		
		arme = new Arme("Laser intergalactique", 10, 14, 0, 0, 3, 0);
		arme.setDescription("Un laser convenable standart pour les vaisseaux intergalactiques. Bonus de mobilité.");
		armeDispo.add(arme);
		armeDroppable.add(arme);
		
		
		// ---------BOUCLIER---------
		GenerateurBouclier bouclier;
		
		bouclier = new GenerateurBouclier("Generateur de creme glacee", 5, 0, 10, 0, 0, 0);
		bouclier.setDescription("Generateur trouve dans une decheterie.");
		generateurBouclierDispo.add(bouclier);
		generateurBouclierDroppable.add(bouclier);
		
		bouclier = new GenerateurBouclier("Generateur intergalactique", 10, 0, 14, 3, 0, 0);
		bouclier.setDescription("Un generateur de bouclier convenable standart pour les vaisseaux intergalactiques. Bonus de constitution.");
		generateurBouclierDispo.add(bouclier);
		generateurBouclierDroppable.add(bouclier);
		
		
		// ---------COQUE---------
		Coque coque;
		
		coque = new Coque("Tabarnak de coq", 5, 0, 0, 10, 0, 0);
		coque.setDescription("Une coque inutile mais qui vous reveillera le matin, et vous fournira des oeufs frais.");
		coqueDispo.add(coque);
		coqueDroppable.add(coque);
		
		coque = new Coque("Coque intergalactique", 10, 0, 3, 14, 0, 0);
		coque.setDescription("Une coque convenable standart pour les vaisseaux intergalactiques. Bonus de defense.");
		coqueDispo.add(coque);
		coqueDroppable.add(coque);
		
		
		// ---------REACTEUR---------
		Reacteur reacteur;
		
		reacteur = new Reacteur("Reacteur Diesel", 5, 0, 0, 0, 10, 0);
		reacteur.setDescription("Certainement vole sur une voiture, peu utile sur un vaisseau");
		reacteurDispo.add(reacteur);
		reacteurDroppable.add(reacteur);
		
		reacteur = new Reacteur("Reacteur Intergalactique", 10, 3, 0, 0, 14, 0);
		reacteur.setDescription("Un reacteur convenable standart pour les vaisseaux intergalactiques. Bonus d'attaque.");
		reacteurDispo.add(reacteur);
		reacteurDroppable.add(reacteur);
		
		
		// ---------PORTEBONHEUR---------
		PorteBonheur porteBonheur;
		
		porteBonheur = new PorteBonheur("Crottin de chevre", 5);
		porteBonheur.setDescription("Friandise intergalactique provenant de la Terre.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		porteBonheur = new PorteBonheur("Ma gloire passee", 10);
		porteBonheur.setDescription("Trophee de guerre d'annee de loyaux services.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		// ---------EFFETS---------
		CombatEffect effet_Renforcement = new CombatEffect("Renforcement", 3);
		effet_Renforcement.setPercentDefense(30);
		
		CombatEffect effet_Enflammed = new CombatEffect("Enflammed", 3);
		effet_Renforcement.setPuissance(25);
		
		// ---------COMPETENCES---------
		// il faudra afficher : Nom / Puissance / Portee X-Y / Effet / Portee X-Y / type / ligne de vue / Puissance
		Competence comp_Renforcement = new Competence("Renforcement", 0, "booster", 0, 0, effet_Renforcement, true);
		comp_Renforcement.setDescription("Augmente de 30% la défense du vaisseau pendant 3 tours.");
		
		Competence comp_LanceFlamme = new Competence("Lance-Flamme", 50, "attackLibre", 1, 6, effet_Enflammed, true);
		comp_LanceFlamme.setDescription("Tir à distance sur la cible et lui infigeant l'effet Enflammé");
		
		Competence comp_Laser = new Competence("Laser", 110, "attackLigne", 1, 100, null, false);
		comp_Laser.setDescription("Compétence puissante grace a sa capacité à tirer au travers des obstacles et à très longue portée");
		
		Competence comp_GrosCoupDeBelier = new Competence("Gros Coup de Bélier", 180, "attackLibre", 1, 1, null, false);
		comp_GrosCoupDeBelier.setDescription("Attaque très puissante utilisable uniquement au contact d'un autre vaisseau");
		
		Competence comp_TirStandart = new Competence("Tir Standart", 100, "attackLibre", 1, 5, null, false);
		comp_TirStandart.setDescription("Competence commune fournissant bonne portée et bons dégats.");
		
		// ---------RELIQUES---------
		ReliqueSacree relique;
		
		relique = new ReliqueSacree("Momie de Toutankamon", comp_Renforcement, 2, 0, 0, 2, 1);
		relique.setDescription("Relique du celebre pharaon, bonus d'attaque, de mobiltié et de chance.");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("Peignoire de DSK", comp_LanceFlamme, 0, 0, 5, 0, 0);
		relique.setDescription("Relique permettant de beneficier d'un gros bonus de constitution");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("Lunettes de Lewis", comp_Laser, 0, 2, 0, 0, 3);
		relique.setDescription("Protege du soleil et augmente le swag. Bonus de défence et de chance");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("Ballon de basket", comp_TirStandart, 1, 1, 1, 1, 1);
		relique.setDescription("Fait passer le temps. Bonus dans toutes les caracteristiques.");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("Dinde de Noel", comp_TirStandart, 0, 0, 0, 0, 5);
		relique.setDescription("Cette magnifique bete vous portera chance");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		assert ( reliqueSacreeDispo.size() > 3 );
	}
}
