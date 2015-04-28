package Gameview;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;
import org.jsfml.graphics.Font;

import controller.Game;

public class TitleScreenView {
	
	private RenderWindow HangarWindow;
//	private Game monGame;
	private boolean endView;
	private boolean endGame;
	
	private Texture FondTexture = new Texture();
	private Texture boutonJouerTexture = new Texture();
	private Texture boutonQuitterTexture = new Texture();
	
	private Sprite FondSprite = new Sprite(); 
	private Sprite boutonJouerSprite = new Sprite();
	private Sprite boutonQuitterSprite = new Sprite();
	
	private Font Font = new Font();
	
	private Text titre = new Text();
	
	public TitleScreenView(Game P, RenderWindow maRenderWindow)
	{
		HangarWindow = maRenderWindow;
//		monGame = P;
		endView = false;
		endGame = false;
	}
	
	public String run()
	{
		chargerRessources();
		configurerTextures();

		while(!endView)
		{
			HangarWindow.clear();
			HangarWindow.draw(FondSprite);
			configurerTextes();

			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED || endGame==true){
					HangarWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					detecterClic(event);
				}


				HangarWindow.draw(boutonJouerSprite);
				HangarWindow.draw(boutonQuitterSprite);
				HangarWindow.draw(titre);

				HangarWindow.display();
			}
		}
		return "Hangar";
	}
	
	private void detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(HangarWindow);
		
		if(boutonJouerSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			endView = true;
        }
        else if(boutonQuitterSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
			endGame = true;
        }
	}

	private void chargerRessources(){
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\ScreenTitle.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			boutonJouerTexture.loadFromFile(Paths.get("rsc\\boutonSuivant.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			boutonQuitterTexture.loadFromFile(Paths.get("rsc\\boutonSuivant.png"));
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
		boutonJouerSprite.setTexture(boutonJouerTexture);
		boutonJouerSprite.setPosition(317,250);
		boutonQuitterSprite.setTexture(boutonQuitterTexture);
		boutonQuitterSprite.setPosition(317,350);
	}

	private void configurerTextes(){
		int taille_Font = 50;

		titre.setFont(Font);
		titre.setCharacterSize(taille_Font);
		titre.setString("Beyond The Stars");
		titre.setPosition(150,100);

	}

}
