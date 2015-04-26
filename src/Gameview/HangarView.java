package Gameview;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import controller.Game;

public class HangarView {

	public RenderWindow HangarWindow;
	public Game monGame;
	private Texture FondTexture = new Texture();
	private Texture FlecheTexture = new Texture();
	private Font Font = new Font();
	private Sprite FondSprite = new Sprite();
	private Sprite FlecheDroiteMoteur = new Sprite();
	private Sprite FlecheDroiteCoque = new Sprite();
	private Sprite FlecheDroiteBouclier = new Sprite();
	private Sprite FlecheDroiteArmes = new Sprite();
	private Sprite FlecheGaucheMoteur = new Sprite();
	private Sprite FlecheGaucheCoque = new Sprite();
	private Sprite FlecheGaucheBouclier = new Sprite();
	private Sprite FlecheGaucheArmes = new Sprite();

	private int itMoteur = 0;
	private int itCoque = 0;
	private int itBouclier = 0;
	private int itArmes = 0 ;

	private Text MoteurNom = new Text();
	private Text CoqueNom = new Text();
	private Text BouclierNom = new Text();
	private Text ArmesNom = new Text();

	private Text MoteurDesc = new Text();
	private Text CoqueDesc = new Text();
	private Text BouclierDesc = new Text();
	private Text ArmesDesc = new Text();
	
	private Text PoidsVaisseau = new Text();
	private Text poidsMax = new Text();
	private Text Attaque = new Text();
	private Text Defense = new Text();
	private Text Mobilite = new Text();
	private Text Constitution = new Text();
	private Text SURPOIDS = new Text();

	public HangarView(Game P)
	{
		HangarWindow = new RenderWindow(new VideoMode(800, 600, 32), "Hangar",WindowStyle.CLOSE);
		monGame = P;
	}

	public void run(){

		chargerImages();
		configurerTextures();

		while(HangarWindow.isOpen() )
		{
			HangarWindow.clear();
			HangarWindow.draw(FondSprite);
			
			configurerTextesPiecesVaisseau();
			chargerTextesStats();
			
			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					HangarWindow.close();
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					detecterClic(event);
				}
				
				
				HangarWindow.draw(FlecheDroiteMoteur);
				HangarWindow.draw(FlecheDroiteCoque);
				HangarWindow.draw(FlecheDroiteBouclier);
				HangarWindow.draw(FlecheDroiteArmes);
				HangarWindow.draw(FlecheGaucheMoteur);
				HangarWindow.draw(FlecheGaucheCoque);
				HangarWindow.draw(FlecheGaucheBouclier);
				HangarWindow.draw(FlecheGaucheArmes);
				HangarWindow.draw(MoteurNom);
				HangarWindow.draw(CoqueNom);
				HangarWindow.draw(BouclierNom);
				HangarWindow.draw(ArmesNom);
				HangarWindow.draw(MoteurDesc);
				HangarWindow.draw(CoqueDesc);
				HangarWindow.draw(BouclierDesc);
				HangarWindow.draw(ArmesDesc);
				HangarWindow.draw(PoidsVaisseau);
				HangarWindow.draw(poidsMax);
				HangarWindow.draw(Attaque);
				HangarWindow.draw(Defense);
				HangarWindow.draw(Mobilite);
				HangarWindow.draw(Constitution);
				HangarWindow.draw(SURPOIDS);

				HangarWindow.display();
			}
		}
	}

	private void chargerImages(){
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\Hangar.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Font.loadFromFile(Paths.get("rsc\\Starjedi.ttf"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try
		{
			FlecheTexture.loadFromFile(Paths.get("rsc\\fleche.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);

		FlecheDroiteMoteur.setTexture(FlecheTexture);
		FlecheDroiteMoteur.rotate(90);
		FlecheDroiteMoteur.setPosition(190,220);

		FlecheDroiteCoque.setTexture(FlecheTexture);
		FlecheDroiteCoque.rotate(90);
		FlecheDroiteCoque.setPosition(420,213);

		FlecheDroiteBouclier.setTexture(FlecheTexture);
		FlecheDroiteBouclier.rotate(90);
		FlecheDroiteBouclier.setPosition(190,395);

		FlecheDroiteArmes.setTexture(FlecheTexture);
		FlecheDroiteArmes.rotate(90);
		FlecheDroiteArmes.setPosition(425,395);

		FlecheGaucheMoteur.setTexture(FlecheTexture);
		FlecheGaucheMoteur.rotate(-90);
		FlecheGaucheMoteur.setPosition(70,243);

		FlecheGaucheCoque.setTexture(FlecheTexture);
		FlecheGaucheCoque.rotate(-90);
		FlecheGaucheCoque.setPosition(270,235);

		FlecheGaucheBouclier.setTexture(FlecheTexture);
		FlecheGaucheBouclier.rotate(-90);
		FlecheGaucheBouclier.setPosition(70,418);

		FlecheGaucheArmes.setTexture(FlecheTexture);
		FlecheGaucheArmes.rotate(-90);
		FlecheGaucheArmes.setPosition(245,418);
	}

	private void configurerTextesPiecesVaisseau()
	{
		int taille_Font = 10;
		int myCut = 28;

		MoteurNom.setFont(Font);
		MoteurNom.setCharacterSize(taille_Font);
		MoteurNom.setString(monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getName());
		MoteurNom.setPosition(60,260);

		CoqueNom.setFont(Font);
		CoqueNom.setCharacterSize(taille_Font);
		CoqueNom.setString(monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getName());
		CoqueNom.setPosition(250,265);

		BouclierNom.setFont(Font);
		BouclierNom.setCharacterSize(taille_Font);
		BouclierNom.setString(monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getName());
		BouclierNom.setPosition(50,440);

		ArmesNom.setFont(Font);
		ArmesNom.setCharacterSize(taille_Font);
		ArmesNom.setString(monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getName());
		ArmesNom.setPosition(250,440);

		MoteurDesc.setFont(Font);
		MoteurDesc.setCharacterSize(taille_Font);
		MoteurDesc.setString(reforme(monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getDescription(),myCut));
		MoteurDesc.setPosition(60,280);
		
		CoqueDesc.setFont(Font);
		CoqueDesc.setCharacterSize(taille_Font);
		CoqueDesc.setString(reforme(monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getDescription(),myCut));
		CoqueDesc.setPosition(250,285);
		
		BouclierDesc.setFont(Font);
		BouclierDesc.setCharacterSize(taille_Font);
		BouclierDesc.setString(reforme(monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getDescription(),myCut));
		BouclierDesc.setPosition(50,460);
		
		ArmesDesc.setFont(Font);
		ArmesDesc.setCharacterSize(taille_Font);
		ArmesDesc.setString(reforme(monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getDescription(),myCut));
		ArmesDesc.setPosition(250,460);
	}

	// chaine et nombre de carac max par ligne
	private String reforme( String s, int cut ) {
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

	private void detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(HangarWindow);
		
		if(FlecheDroiteMoteur.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
            if(itMoteur < monGame.getConteneurObjetsVaisseau().reacteurDispo.size()-1)
            {
            	itMoteur++;
            }
            else
            {
            	itMoteur=0;
            }
        }
        else if(FlecheDroiteCoque.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
        	if(itCoque < monGame.getConteneurObjetsVaisseau().coqueDispo.size()-1)
            {
            	itCoque++;
            }
            else
            {
            	itCoque=0;
            }
        }
        else if(FlecheDroiteBouclier.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
        	if(itBouclier < monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.size()-1)
            {
            	itBouclier++;
            }
            else
            {
            	itBouclier=0;
            }
        }
        else if(FlecheDroiteArmes.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
        	if(itArmes < monGame.getConteneurObjetsVaisseau().armeDispo.size()-1)
            {
            	itArmes++;
            }
            else
            {
            	itArmes=0;
            }
        }  
        else if(FlecheGaucheMoteur.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
        	if(itMoteur > 0 )
            {
            	itMoteur--;
            }
            else
            {
            	itMoteur=monGame.getConteneurObjetsVaisseau().reacteurDispo.size()-1;
            }
        }
        else if(FlecheGaucheCoque.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
        	if(itCoque > 0 )
            {
            	itCoque--;
            }
            else
            {
            	itCoque=monGame.getConteneurObjetsVaisseau().coqueDispo.size()-1;
            }
        }
        else if(FlecheGaucheBouclier.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
        	if(itBouclier > 0 )
            {
            	itBouclier--;
            }
            else
            {
            	itBouclier=monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.size()-1;
            }
        }
        else if(FlecheGaucheArmes.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
        	if(itArmes > 0 )
            {
            	itArmes--;
            }
            else
            {
            	itArmes=monGame.getConteneurObjetsVaisseau().armeDispo.size()-1;
            }
        }
	}
	
	private void chargerTextesStats(){
		int taille_Font = 14;
		int poidsActuel = monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getWeight()
				+monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getWeight()
				+monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getWeight()
				+monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getWeight();
		
		int monAttaque = monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getAttack()
				+monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getAttack()
				+monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getAttack()
				+monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getAttack();
		
		int maDefense = monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getDefense()
				+monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getDefense()
				+monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getDefense()
				+monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getDefense();
		
		int maMobilite =  monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getMobility()
				+monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getMobility()
				+monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getMobility()
				+monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getMobility();
		
		int maConstitution = monGame.getConteneurObjetsVaisseau().reacteurDispo.get(itMoteur).getConstitution()
				+monGame.getConteneurObjetsVaisseau().coqueDispo.get(itCoque).getConstitution()
				+monGame.getConteneurObjetsVaisseau().generateurBouclierDispo.get(itBouclier).getConstitution()
				+monGame.getConteneurObjetsVaisseau().armeDispo.get(itArmes).getConstitution();
		
		PoidsVaisseau.setFont(Font);
		PoidsVaisseau.setCharacterSize(taille_Font);
		PoidsVaisseau.setString("masse du vaisseau : "+poidsActuel); 
		PoidsVaisseau.setPosition(540,230);
		
		poidsMax.setFont(Font);
		poidsMax.setCharacterSize(taille_Font);
		poidsMax.setString("limite de masse : "+monGame.getPoidsMax());
		poidsMax.setPosition(540,250);
		
		Attaque.setFont(Font);
		Attaque.setCharacterSize(taille_Font);
		Attaque.setString("attaque : "+monAttaque);
		Attaque.setPosition(540,280);
		
		Defense.setFont(Font);
		Defense.setCharacterSize(taille_Font);
		Defense.setString("defense : "+maDefense);
		Defense.setPosition(540,300);
		
		Mobilite.setFont(Font);
		Mobilite.setCharacterSize(taille_Font);
		Mobilite.setString("mobilite : "+maMobilite);
		Mobilite.setPosition(540,320);

		Constitution.setFont(Font);
		Constitution.setCharacterSize(taille_Font);
		Constitution.setString("constitution : "+maConstitution);
		Constitution.setPosition(540,340);
		
		SURPOIDS.setFont(Font);
		SURPOIDS.setCharacterSize(taille_Font);
		
		SURPOIDS.setPosition(540,440);
		if(poidsActuel > monGame.getPoidsMax()){
			SURPOIDS.setString("surpoids");
		}
		else
		{
			SURPOIDS.setString(" ");
		}
	}

}
