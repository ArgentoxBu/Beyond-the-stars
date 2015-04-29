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
		arme.setDescription("un laser de merde pour un vaisseau de merde.");
		armeDispo.add(arme);
		armeDroppable.add(arme);
		
		arme = new Arme("Laser intergalactique", 10, 14, 0, 0, 3, 0);
		arme.setDescription("un laser convenable standard pour les vaisseaux intergalactiques. Bonus de mobilité.");
		armeDispo.add(arme);
		armeDroppable.add(arme);
		
		
		// ---------BOUCLIER---------
		GenerateurBouclier bouclier;
		
		bouclier = new GenerateurBouclier("generateur de creme glacee", 5, 0, 10, 0, 0, 0);
		bouclier.setDescription("generateur trouve dans une decheterie.");
		generateurBouclierDispo.add(bouclier);
		generateurBouclierDroppable.add(bouclier);
		
		bouclier = new GenerateurBouclier("generateur intergalactique", 10, 0, 14, 3, 0, 0);
		bouclier.setDescription("un generateur de bouclier convenable standard pour les vaisseaux intergalactiques. Bonus de constitution.");
		generateurBouclierDispo.add(bouclier);
		generateurBouclierDroppable.add(bouclier);
		
		
		// ---------COQUE---------
		Coque coque;
		
		coque = new Coque("tabarnak de coq", 5, 0, 0, 10, 0, 0);
		coque.setDescription("une coque inutile mais qui vous reveillera le matin, et vous fournira des oeufs frais.");
		coqueDispo.add(coque);
		coqueDroppable.add(coque);
		
		coque = new Coque("coque intergalactique", 10, 0, 3, 14, 0, 0);
		coque.setDescription("une coque convenable standard pour les vaisseaux intergalactiques. Bonus de defense.");
		coqueDispo.add(coque);
		coqueDroppable.add(coque);
		
		
		// ---------REACTEUR---------
		Reacteur reacteur;
		
		reacteur = new Reacteur("reacteur Diesel", 5, 0, 0, 0, 30, 0);
		reacteur.setDescription("certainement vole sur une voiture, peu utile sur un vaisseau");
		reacteurDispo.add(reacteur);
		reacteurDroppable.add(reacteur);
		
		reacteur = new Reacteur("reacteur Intergalactique", 10, 3, 0, 0, 14, 0);
		reacteur.setDescription("un reacteur convenable standard pour les vaisseaux intergalactiques. Bonus d'attaque.");
		reacteurDispo.add(reacteur);
		reacteurDroppable.add(reacteur);
		
		
		// ---------PORTEBONHEUR---------
		PouvoirSpecial pvr_vadrouille = new PouvoirSpecial("vadrouille", "bonus de 10 pourcent a la mobilité", 0, 10, 0, 0);
		PouvoirSpecial pvr_violent = new PouvoirSpecial("violent", "bonus de 10 pourcent a l'attaque", 10, 0, 0, 0);
		PouvoirSpecial pvr_bouclier = new PouvoirSpecial("bouclier", "bonus de 10 pourcent a la défense", 0, 0, 10, 0);
		PouvoirSpecial pvr_sacapv = new PouvoirSpecial("sac a pv", "bonus de 10 pourcent a la constitution",  0,  0,  0,  10);
		
		// ---------PORTEBONHEUR---------
		PorteBonheur porteBonheur;
		
		porteBonheur = new PorteBonheur("crottin de chevre", 5, pvr_violent);
		porteBonheur.setDescription("friandise intergalactique provenant de la Terre.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		porteBonheur = new PorteBonheur("ma gloire passee", 5, pvr_bouclier);
		porteBonheur.setDescription("trophee de guerre d'annee de loyaux services.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		porteBonheur = new PorteBonheur("trefle a 14 feuilles", 5, pvr_vadrouille);
		porteBonheur.setDescription("ce trefle commun d'une planete lointaine portera chance.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		porteBonheur = new PorteBonheur("bouteille de cola enrichi", 5, pvr_sacapv);
		porteBonheur.setDescription("delicieuse boisson qui saura vous combler.");
		porteBonheurDispo.add(porteBonheur);
		porteBonheurDroppable.add(porteBonheur);
		
		// ---------EFFETS---------
		CombatEffect effet_Renforcement = new CombatEffect("renforcement", 3);
		effet_Renforcement.setPercentDefense(30);
		
		CombatEffect effet_Enflammed = new CombatEffect("enflammé", 3);
		effet_Renforcement.setPuissance(25);
		
		// ---------COMPETENCES---------
		// il faudra afficher : Nom / Puissance / Portee X-Y / Effet / Portee X-Y / type / ligne de vue / Puissance
		Competence comp_Renforcement = new Competence("renforcement", 0, "booster", 0, 0, effet_Renforcement, false);
		comp_Renforcement.setDescription("augmente de 30% la défense du vaisseau pendant 3 tours.");
		
		Competence comp_LanceFlamme = new Competence("lance-Flamme", 50, "attackLibre", 1, 6, effet_Enflammed, true);
		comp_LanceFlamme.setDescription("tir à distance sur la cible et lui infigeant l'effet Enflammé");
		
		Competence comp_Laser = new Competence("laser", 110, "attackLigne", 1, 100, null, false);
		comp_Laser.setDescription("compétence puissante grace a sa capacité à tirer au travers des obstacles et à très longue portée");
		
		Competence comp_GrosCoupDeBelier = new Competence("gros Coup de Bélier", 180, "attackLibre", 1, 1, null, false);
		comp_GrosCoupDeBelier.setDescription("attaque très puissante utilisable uniquement au contact d'un autre vaisseau");
		
		Competence comp_TirStandart = new Competence("tir Standard", 100, "attackLibre", 1, 5, null, false);
		comp_TirStandart.setDescription("competence commune fournissant bonne portée et bons dégats.");
		
		// ---------RELIQUES---------
		ReliqueSacree relique;
		
		relique = new ReliqueSacree("momie de Toutankamon", comp_Renforcement);
		relique.setDescription("relique du celebre pharaon");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("peignoir de DSK", comp_LanceFlamme);
		relique.setDescription("relique faisant fuir les femmes de menage");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("lunettes de Lewis", comp_Laser);
		relique.setDescription("protege du soleil et augmente le swag.");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("ballon de basket", comp_GrosCoupDeBelier);
		relique.setDescription("fait passer le temps.");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		relique = new ReliqueSacree("dinde de Noel", comp_TirStandart);
		relique.setDescription("cette magnifique bete vous portera chance");
		reliqueSacreeDispo.add(relique);
		reliqueSacreeDroppable.add(relique);
		
		assert ( reliqueSacreeDispo.size() > 3 );
	}
}
