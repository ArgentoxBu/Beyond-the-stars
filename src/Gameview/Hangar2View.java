package Gameview;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;
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

public class Hangar2View {

	public RenderWindow HangarWindow;
	public Game monGame;
	private Font Font = new Font();
	private Texture FondTexture = new Texture();
	private Texture flecheTexture = new Texture();
	private Texture boutonSuivantTexture = new Texture();
	private Sprite FondSprite = new Sprite();
	private boolean endView;

	private int itPorteBonheur = 0;
	private Text NomPorteBonheur = new Text();
	private Text DescriptionPorteBonheur = new Text();
	private Text BonusSpecial = new Text();
	private Text DescriptionBonus = new Text();

	private Sprite FlecheDroite = new Sprite();
	private Sprite FlecheGauche = new Sprite();
	
	private Sprite boutonSuivantSprite = new Sprite();


	public Hangar2View(Game P, RenderWindow maRenderWindow)
	{
		HangarWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run()
	{
		chargerRessources();
		configurerTextures();

		while(!endView)
		{
			HangarWindow.clear();
			HangarWindow.draw(FondSprite);

			configurerTextesPiecesVaisseau();

			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					HangarWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					detecterClic(event);
				}


				HangarWindow.draw(NomPorteBonheur);
				HangarWindow.draw(DescriptionPorteBonheur);
				HangarWindow.draw(BonusSpecial);
				HangarWindow.draw(DescriptionBonus);
				HangarWindow.draw(FlecheDroite);
				HangarWindow.draw(FlecheGauche);
				HangarWindow.draw(boutonSuivantSprite);
				HangarWindow.display();
			}
		}
		return "Hangar3";
	}

	private void chargerRessources(){
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\Hangar2.png"));
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
			flecheTexture.loadFromFile(Paths.get("rsc\\fleche.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			boutonSuivantTexture.loadFromFile(Paths.get("rsc\\boutonSuivant.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
		boutonSuivantSprite.setTexture(boutonSuivantTexture);
		boutonSuivantSprite.setPosition(550,520);

		FlecheDroite.setTexture(flecheTexture);
		FlecheDroite.rotate(90);
		FlecheDroite.setPosition(340,260);

		FlecheGauche.setTexture(flecheTexture);
		FlecheGauche.rotate(-90);
		FlecheGauche.setPosition(140,285);
	}

	private void configurerTextesPiecesVaisseau(){
		int taille_Font = 17;

		NomPorteBonheur.setFont(Font);
		NomPorteBonheur.setCharacterSize(taille_Font);
		NomPorteBonheur.setString(monGame.getConteneurObjetsVaisseau().porteBonheurDispo.get(itPorteBonheur).getName());
		NomPorteBonheur.setPosition(155,340);

		BonusSpecial.setFont(Font);
		BonusSpecial.setCharacterSize(taille_Font);
		BonusSpecial.setString("Bonus Special : ");
		BonusSpecial.setPosition(565,240);

		taille_Font = 14;
		int myCut = 38;

		DescriptionPorteBonheur.setFont(Font);
		DescriptionPorteBonheur.setCharacterSize(taille_Font);
		DescriptionPorteBonheur.setString(reforme(monGame.getConteneurObjetsVaisseau().porteBonheurDispo.get(itPorteBonheur).getDescription(),myCut));
		DescriptionPorteBonheur.setPosition(70,370);

		myCut = 27;

		DescriptionBonus.setFont(Font);
		DescriptionBonus.setCharacterSize(taille_Font);
		//DescriptionBonus.setString(reforme(monGame.getConteneurObjetsVaisseau().porteBonheurDispo.get(itPorteBonheur).,myCut));
		DescriptionBonus.setString(reforme("Ceci est un test de texte pour positionner le texte comme si cetait un texte",myCut));
		DescriptionBonus.setPosition(525,270);


	}
	
	private void detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(HangarWindow);
		
		if(FlecheDroite.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
            if(itPorteBonheur < monGame.getConteneurObjetsVaisseau().porteBonheurDispo.size()-1)
            {
            	itPorteBonheur++;
            }
            else
            {
            	itPorteBonheur=0;
            }
        }
        else if(FlecheGauche.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
        	if(itPorteBonheur >0)
            {
            	itPorteBonheur--;
            }
            else
            {
            	itPorteBonheur=monGame.getConteneurObjetsVaisseau().porteBonheurDispo.size()-1;
            }
        }
        else if(boutonSuivantSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
            	//passer a la fenetre suivante et enregistrer mes choix
        		endView = true;
        }
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
}

