package Gameview;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
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
	private Sprite FondSprite = new Sprite();
	private boolean endView;

	private int itPorteBonheur = 0;

	private Text titrePorteBonheur = new Text();
	private Text NomPorteBonheur = new Text();
	private Text DescriptionPorteBonheur = new Text();
	private Text BonusSpecial = new Text();
	private Text DescriptionBonus = new Text();


	public Hangar2View(Game P, RenderWindow maRenderWindow)
	{
		HangarWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run()
	{
		chargerImages();
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
					return "endGame";
				}
			}

			HangarWindow.draw(titrePorteBonheur);
			HangarWindow.draw(NomPorteBonheur);
			HangarWindow.draw(DescriptionPorteBonheur);
			HangarWindow.draw(BonusSpecial);
			HangarWindow.draw(DescriptionBonus);
			HangarWindow.display();
		}
		return "endGame";
	}

	private void chargerImages(){
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
	}

	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
	}

	private void configurerTextesPiecesVaisseau(){
		int taille_Font = 17;


		titrePorteBonheur.setFont(Font);
		titrePorteBonheur.setCharacterSize(taille_Font);
		titrePorteBonheur.setString("Porte Bonheur");
		titrePorteBonheur.setPosition(175,200);

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

