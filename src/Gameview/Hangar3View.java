package Gameview;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.ReliqueSacree;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import com.sun.crypto.provider.DESCipher;

import controller.Game;

public class Hangar3View {

	public RenderWindow HangarWindow;
	public Game monGame;
	private boolean endView;
	private Texture FondTexture = new Texture();
	private Texture flecheTexture = new Texture();
	private Texture boutonTerminerTexture = new Texture();
	private Font Font = new Font();

	private Sprite FondSprite = new Sprite();

	private int itRelique1;
	private int itRelique2;
	private int itRelique3;

	private Text nomRelique1 =  new Text();
	private Text nomRelique2 =  new Text();
	private Text nomRelique3 = new Text();

	private Sprite boutonTerminerSprite = new Sprite();
	private Sprite FlecheDroite1 = new Sprite();
	private Sprite FlecheGauche1 = new Sprite();
	private Sprite FlecheDroite2 = new Sprite();
	private Sprite FlecheGauche2 = new Sprite();
	private Sprite FlecheDroite3 = new Sprite();
	private Sprite FlecheGauche3 = new Sprite();

	private Text descriptionRelique1 =  new Text();
	private Text descriptionRelique2 =  new Text();
	private Text descriptionRelique3 = new Text();



	private Text[][] tabCompetences =  new Text[3][5];

	public Hangar3View(Game P, RenderWindow maRenderWindow)
	{
		HangarWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run()
	{
		itRelique1 = monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-1;
		itRelique2= monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-2;
		itRelique3= monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-3;
		remplirTableauComp();
		chargerRessources();
		configurerTextures();
		
		drawElements();

		while(!endView)
		{
			

			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					HangarWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					if(detecterClic(event)){
						drawElements();
					}
				}

				
			}
		}
		return "Space";
	}

	private void chargerRessources(){
		try {
			Font.loadFromFile(Paths.get("rsc\\Starjedi.ttf"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\Hangar3.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			flecheTexture.loadFromFile(Paths.get("rsc\\fleche.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			boutonTerminerTexture.loadFromFile(Paths.get("rsc\\boutonTerminer.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
		boutonTerminerSprite.setTexture(boutonTerminerTexture);
		boutonTerminerSprite.setPosition(610,550);

		FlecheDroite1.setTexture(flecheTexture);
		FlecheDroite1.rotate(90);
		FlecheDroite1.setPosition(160,252);

		FlecheGauche1.setTexture(flecheTexture);
		FlecheGauche1.rotate(-90);
		FlecheGauche1.setPosition(60,277);

		FlecheDroite2.setTexture(flecheTexture);
		FlecheDroite2.rotate(90);
		FlecheDroite2.setPosition(360,252);

		FlecheGauche2.setTexture(flecheTexture);
		FlecheGauche2.rotate(-90);
		FlecheGauche2.setPosition(260,277);

		FlecheDroite3.setTexture(flecheTexture);
		FlecheDroite3.rotate(90);
		FlecheDroite3.setPosition(560,252);

		FlecheGauche3.setTexture(flecheTexture);
		FlecheGauche3.rotate(-90);
		FlecheGauche3.setPosition(460,277);
	}

	private boolean detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(HangarWindow);

		if(FlecheDroite1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique1 < monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-1)
				{
					itRelique1++;
				}
				else
				{
					itRelique1=0;
				}
			}while(itRelique1==itRelique2 || itRelique1==itRelique3);
			return true;
		}
		else if(FlecheDroite2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique2 < monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-1)
				{
					itRelique2++;
				}
				else
				{
					itRelique2=0;
				}
			}while(itRelique2==itRelique1 || itRelique2==itRelique3);
			return true;

		}
		else if(FlecheDroite3.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique3 < monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.size()-1)
				{
					itRelique3++;
				}
				else
				{
					itRelique3=0;
				}
			}while(itRelique3==itRelique1 || itRelique3==itRelique2);
			return true;
		}
		else if(FlecheGauche1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique1 >0)
	            {
	            	itRelique1--;
	            }
	            else
	            {
	            	itRelique1=monGame.getConteneurObjetsVaisseau().porteBonheurDispo.size()-1;
	            }
			}while(itRelique1==itRelique2 || itRelique1==itRelique3);
			return true;
		}
		else if(FlecheGauche2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique2 >0)
	            {
	            	itRelique2--;
	            }
	            else
	            {
	            	itRelique2=monGame.getConteneurObjetsVaisseau().porteBonheurDispo.size()-1;
	            }
			}while(itRelique2==itRelique1 || itRelique2==itRelique3);
			return true;
		}
		else if(FlecheGauche3.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			do{
				if(itRelique3 >0)
	            {
	            	itRelique3--;
	            }
	            else
	            {
	            	itRelique3=monGame.getConteneurObjetsVaisseau().porteBonheurDispo.size()-1;
	            }
			}while(itRelique3==itRelique1 || itRelique3==itRelique2);
			return true;
		}
		else if(boutonTerminerSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
			//passer a la fenetre suivante et enregistrer mes choix
			ArrayList<ReliqueSacree> reliqueSacreeChoisies = new ArrayList<ReliqueSacree>();
			reliqueSacreeChoisies.add(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1));
			reliqueSacreeChoisies.add(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2));
			reliqueSacreeChoisies.add(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3));
			monGame.setReliqueSacreeChoisi(reliqueSacreeChoisies);
			endView = true;
		}
		return false;
	}

	private void configurerTextesPiecesVaisseau(){

		int taille_Font = 10;

		nomRelique1.setFont(Font);
		nomRelique1.setCharacterSize(taille_Font);
		nomRelique1.setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getName());
		nomRelique1.setPosition(60,280);

		nomRelique2.setFont(Font);
		nomRelique2.setCharacterSize(taille_Font);
		nomRelique2.setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getName());
		nomRelique2.setPosition(255,280);

		nomRelique3.setFont(Font);
		nomRelique3.setCharacterSize(taille_Font);
		nomRelique3.setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getName());
		nomRelique3.setPosition(450,280);

		taille_Font = 9;
		int myCut = 22;

		descriptionRelique1.setFont(Font);
		descriptionRelique1.setCharacterSize(taille_Font);
		descriptionRelique1.setString(reforme(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getDescription(),myCut));
		descriptionRelique1.setPosition(45,305);

		descriptionRelique2.setFont(Font);
		descriptionRelique2.setCharacterSize(taille_Font);
		descriptionRelique2.setString(reforme(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getDescription(),myCut));
		descriptionRelique2.setPosition(240,305);

		descriptionRelique3.setFont(Font);
		descriptionRelique3.setCharacterSize(taille_Font);
		descriptionRelique3.setString(reforme(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getDescription(),myCut));
		descriptionRelique3.setPosition(440,305);

		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				tabCompetences[i][j].setFont(Font);
				tabCompetences[i][j].setCharacterSize(taille_Font);
			}
		}
		taille_Font = 10;
		tabCompetences[0][0].setCharacterSize(taille_Font);
		tabCompetences[1][0].setCharacterSize(taille_Font);
		tabCompetences[2][0].setCharacterSize(taille_Font);


		tabCompetences[0][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getName());
		tabCompetences[0][0].setPosition(50,375);

		tabCompetences[0][1].setString("Puissance : " +monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getPuissanceToString());
		tabCompetences[0][1].setPosition(50,395);

		tabCompetences[0][2].setString("Portee : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getMiniMaxi());
		tabCompetences[0][2].setPosition(50,415);

		tabCompetences[0][3].setString("Effet : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[0][3].setPosition(50,435);

		tabCompetences[0][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getIsLignedeVue());
		tabCompetences[0][4].setPosition(50,455);

		tabCompetences[1][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getName());
		tabCompetences[1][0].setPosition(245,375);

		tabCompetences[1][1].setString("Puissance : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getPuissanceToString());
		tabCompetences[1][1].setPosition(245,395);

		tabCompetences[1][2].setString("Portee : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getMiniMaxi());
		tabCompetences[1][2].setPosition(245,415);

		tabCompetences[1][3].setString("Effet : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[1][3].setPosition(245,435);

		tabCompetences[1][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getIsLignedeVue());
		tabCompetences[1][4].setPosition(245,455);

		tabCompetences[2][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getName());
		tabCompetences[2][0].setPosition(440,375);

		tabCompetences[2][1].setString("Puissance : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getPuissanceToString());
		tabCompetences[2][1].setPosition(440,395);

		tabCompetences[2][2].setString("Portee : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getMiniMaxi());
		tabCompetences[2][2].setPosition(440,415);

		tabCompetences[2][3].setString("Effet : "+monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[2][3].setPosition(440,435);

		tabCompetences[2][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getIsLignedeVue());
		tabCompetences[2][4].setPosition(440,455);

	}

	private void remplirTableauComp(){
		for(int i=0;i<3;i++){
			for(int j =0;j<5;j++){
				tabCompetences[i][j] = new Text();
			}
		}
	}

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

	public void drawElements(){
		
		HangarWindow.clear();
		HangarWindow.draw(FondSprite);

		configurerTextesPiecesVaisseau();
		HangarWindow.draw(nomRelique1);
		HangarWindow.draw(nomRelique2);
		HangarWindow.draw(nomRelique3);
		HangarWindow.draw(descriptionRelique1);
		HangarWindow.draw(descriptionRelique2);
		HangarWindow.draw(descriptionRelique3);

		HangarWindow.draw(FlecheDroite1);
		HangarWindow.draw(FlecheDroite2);
		HangarWindow.draw(FlecheDroite3);
		HangarWindow.draw(FlecheGauche1);
		HangarWindow.draw(FlecheGauche2);
		HangarWindow.draw(FlecheGauche3);
		HangarWindow.draw(boutonTerminerSprite);

		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				HangarWindow.draw(tabCompetences[i][j]);
			}
		}
		HangarWindow.display();
	}
}
