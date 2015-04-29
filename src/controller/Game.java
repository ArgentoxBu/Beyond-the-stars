package controller;

import java.util.ArrayList;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

import Gameview.AightMusic;
import Gameview.BattleView;
import Gameview.Hangar2View;
import Gameview.Hangar3View;
import Gameview.HangarView;
import Gameview.PlanetView;
import Gameview.SpaceView;
import Gameview.TitleScreenView;
import model.Arme;
import model.ConteneurObjetsVaisseau;
import model.Coque;
import model.GenerateurBouclier;
import model.GrilleTBS;
import model.Joueur;
import model.Planet;
import model.PorteBonheur;
import model.Reacteur;
import model.ReliqueSacree;
import model.Vaisseau;


public class Game extends Thread {
	private static Game instance = new Game();
	
	boolean GameOver;
	private ConteneurObjetsVaisseau c;
	private Vaisseau vaisseau;
	private RenderWindow RenderWind;

	// armes choisies pour la creation du vaisseau au hangar
	private Arme armeChoisi;
	private GenerateurBouclier generateurBouclierChoisi;
	private Coque coqueChoisi;
	private Reacteur reacteurChoisi;
	private PorteBonheur porteBonheurChoisi;
	private ArrayList<ReliqueSacree> reliqueSacreeChoisi;
	private int poidsMAX;
	private GrilleTBS grilleTBS;
	private AightMusic musicActu;
	private ArrayList<Joueur> joueurs;

	private Planet planet;
	
	public static Game getInstance() {
		return instance;
	}
	
	private Game() {
		// variables personalisables
		poidsMAX = 30;

		//initialisation variables
		GameOver = false;
		c = new ConteneurObjetsVaisseau();
		
		
		// --------------- CREATION VAISSEAU ------------------------------
		// Creation du vaisseau de base avant de le changer dans le hangar
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
		// creation du vaisseau
		vaisseau = new Vaisseau("LE VIEUX DEBRIS", poidsMAX, armeChoisi, coqueChoisi, reacteurChoisi, generateurBouclierChoisi, porteBonheurChoisi, reliqueSacreeChoisi);
		// ---------------------------------------------------------------
		
		// TEMPORAIRE : CREATION GRILLE TBS AVEC LE VAISSEAU, UN ALLIE ET UN ENNEMI
		Joueur joueur;
		joueurs = new ArrayList<Joueur>();
		joueur = new Joueur(vaisseau, 1);
		joueurs.add(joueur);
		joueur = new Joueur(vaisseau, 2);
		joueurs.add(joueur);
		joueur = new Joueur(vaisseau, 3);
		joueurs.add(joueur);
		grilleTBS = new GrilleTBS(15, joueurs);
		grilleTBS.generer_map();
		
		// -------------------------------------------------------
		//                          TESTS
		// -------------------------------------------------------
		System.out.println("\n           COUCOU JE SUIS UN CHATON :3\n");
		System.out.println("           	    /\\_/\\   meow!   ");
		System.out.println("		  =( °w° )= ");
		System.out.println("		    )   (  // ");
		System.out.println("		   (__ __)// ");
		
		System.out.println("\n\n------------ AFFICHAGE DES TESTS -------------");
		
		// affichage composantes du vaisseau
		//System.out.println("\n" + vaisseau.toString());
		// creation grille TBS, generation aleatoire avec le vaisseau cree, affichage en terminal

		// affichage grille terminal
		//System.out.println(grilleTBS.toString());
		
		// test portee deplacement
		//System.out.println(grilleTBS.getDeplacementCases(grilleTBS.getJoueurs().get(0)));

		/* Test A*
		Scanner scan = new Scanner(System.in);
		int xo = scan.nextInt();
		int yo = scan.nextInt();
		int xf = scan.nextInt();
		int yf = scan.nextInt();
		boolean b = grille.shortestPath(xo, yo, xf, yf);
		System.out.println(b);
		*/

//		ArrayList<Point> retour = grilleTBS.getCompetenceCases(getGrilleTBS().getJoueurs().get(0), 
//				c.reliqueSacreeDispo.get(3).getCompetence());
//		for(Point P : retour)
//		{
//			System.out.println("point : "+P.x +","+P.y);
//		}
		// -------------------------------------------------------
		//                       FIN DES TESTS
		// -------------------------------------------------------
	}
	
	@Override
	public void run() {

		String Etat = "TitleScreen";
		
		RenderWind = new RenderWindow(new VideoMode(800, 600, 32), "Beyond the stars",WindowStyle.CLOSE);
		RenderWind.setFramerateLimit(24);
		
		while(Etat!="EndGame")
		{
			switch(Etat){

				case "Battle" :
					if ( musicActu != null ) musicActu.stopper();
					musicActu = new AightMusic("battle");
					musicActu.balancer();
					BattleView maBattleView = new BattleView(this, RenderWind);
					BattleController monBattleController =  new BattleController(maBattleView);
					Etat = monBattleController.lancer();
					break;
			
				case "TitleScreen" :
					if ( musicActu != null ) musicActu.stopper();
					musicActu = new AightMusic("title");
					musicActu.balancer();
					TitleScreenView monMenu = new TitleScreenView(this, RenderWind);
					Etat = monMenu.run();
					break;
			
				case "Hangar" :
					if ( musicActu != null ) musicActu.stopper();
					musicActu = new AightMusic("hangar");
					musicActu.balancer();
					HangarView monHangar = new HangarView(this, RenderWind);
					Etat = monHangar.run();
					break;
					
				case "Hangar2" :
					Hangar2View monHangar2 = new Hangar2View(this, RenderWind);
					Etat = monHangar2.run();
					break;
					
				case "Hangar3" :
					Hangar3View monHangar3 = new Hangar3View(this, RenderWind);
					Etat = monHangar3.run();
					break;
				
				case "Space" :
					if ( musicActu != null ) musicActu.stopper();
					musicActu = new AightMusic("space");
					musicActu.balancer();
					SpaceView mySpace = new SpaceView(this, RenderWind);
					Etat = mySpace.run(vaisseau);
					break;
					
				case "Planet" :
					PlanetView myPlanet = new PlanetView(this, RenderWind);
					Etat = myPlanet.run(vaisseau);
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

	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public RenderWindow getRenderWind() {
		return RenderWind;
	}
}
