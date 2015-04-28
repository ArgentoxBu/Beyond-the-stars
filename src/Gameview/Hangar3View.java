package Gameview;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import com.sun.crypto.provider.DESCipher;

import controller.Game;

public class Hangar3View {
	
	public RenderWindow HangarWindow;
	public Game monGame;
	private boolean endView;
	private Texture FondTexture = new Texture();
	private Font Font = new Font();
	
	private Sprite FondSprite = new Sprite();
	
	private int itRelique1;
	private int itRelique2;
	private int itRelique3;
	
	private Text nomRelique1 =  new Text();
	private Text nomRelique2 =  new Text();
	private Text nomRelique3 = new Text();
	
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
//					detecterClic(event);
				}

				HangarWindow.draw(nomRelique1);
				HangarWindow.draw(nomRelique2);
				HangarWindow.draw(nomRelique3);
				HangarWindow.draw(descriptionRelique1);
				HangarWindow.draw(descriptionRelique2);
				HangarWindow.draw(descriptionRelique3);
				HangarWindow.display();
			}
		}
		return "EndGame";
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
	}
	
	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
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

		
		
		tabCompetences[1][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getName());
		tabCompetences[1][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getName());
		
		tabCompetences[1][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getPuissanceToString());
		tabCompetences[1][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getPuissanceToString());
		
		tabCompetences[1][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getMiniMaxi());
		tabCompetences[1][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getMiniMaxi());
		
		tabCompetences[1][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[1][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getNameCombatEffectOuDefaut());
		
		tabCompetences[1][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getIsLignedeVue());
		tabCompetences[1][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique1).getCompetence().getIsLignedeVue());
		
		tabCompetences[2][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getName());
		tabCompetences[2][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getName());
		
		tabCompetences[2][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getPuissanceToString());
		tabCompetences[2][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getPuissanceToString());
		
		tabCompetences[2][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getMiniMaxi());
		tabCompetences[2][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getMiniMaxi());
		
		tabCompetences[2][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[2][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getNameCombatEffectOuDefaut());
		
		tabCompetences[2][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getIsLignedeVue());
		tabCompetences[2][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique2).getCompetence().getIsLignedeVue());
		
		tabCompetences[3][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getName());
		tabCompetences[3][0].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getName());
		
		tabCompetences[3][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getPuissanceToString());
		tabCompetences[3][1].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getPuissanceToString());
		
		tabCompetences[3][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getMiniMaxi());
		tabCompetences[3][2].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getPuissanceToString());
		
		tabCompetences[3][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getNameCombatEffectOuDefaut());
		tabCompetences[3][3].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getNameCombatEffectOuDefaut());
		
		tabCompetences[3][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getIsLignedeVue());
		tabCompetences[3][4].setString(monGame.getConteneurObjetsVaisseau().reliqueSacreeDispo.get(itRelique3).getCompetence().getIsLignedeVue());
		
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

}
