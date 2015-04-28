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

import controller.Game;

public class Hangar3View {
	
	public RenderWindow HangarWindow;
	public Game monGame;
	private boolean endView;
	private Texture FondTexture = new Texture();
	private Font Font = new Font();
	
	private Sprite FondSprite = new Sprite();
	
	private Text NomRelique1 =  new Text();
	private Text NomRelique2 =  new Text();
	private Text NomRelique3 = new Text();
	
	public Hangar3View(Game P, RenderWindow maRenderWindow)
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

//			configurerTextesPiecesVaisseau();

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
	

}
