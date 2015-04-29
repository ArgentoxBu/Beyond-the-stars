package Gameview;

import java.io.IOException;
import java.nio.file.Paths;

import model.Planet;
import model.Planet.PlanetType;
import model.Vaisseau;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import controller.Game;

public class PlanetView {
	public RenderWindow planetWindow;
	public Game monGame;
	private Texture FondTexture = new Texture();
	private Texture boutonSuivantTexture = new Texture();
	private Texture vaisseauTexture = new Texture();
	private Sprite FondSprite = new Sprite();
	private Sprite vaisseauSprite = new Sprite();
	private boolean endView;
	private Sprite boutonSuivantSprite = new Sprite();
	
	String nextState = new String();
	
	Vaisseau vaisseau;
	
	public PlanetView(Game P, RenderWindow maRenderWindow) {
		planetWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run(Vaisseau v) {
		vaisseau = v;
		chargerImages();
		configurerTextures();
		
		drawElements();

		while(!endView) {
		
			
			for(Event event : planetWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					planetWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					detecterClic(event);
					//drawElements();
				}
			}
		}
		
		return nextState;
	}

	private void chargerImages() {
		try {
			FondTexture.loadFromFile(Paths.get("rsc\\planetBackground.png"));
			vaisseauTexture.loadFromFile(Paths.get("rsc\\spaceShip.png"));
			boutonSuivantTexture.loadFromFile(Paths.get("rsc\\boutonSuivant.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
		vaisseauSprite.setTexture(vaisseauTexture);
		vaisseauSprite.setPosition(100, 200);
		boutonSuivantSprite.setTexture(boutonSuivantTexture);
		boutonSuivantSprite.setPosition(615,535);
	}
	
	private boolean detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(planetWindow);

        if(boutonSuivantSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
        	//passer a la fenetre suivante et enregistrer mes choix
        	System.out.println(Game.getInstance().getPlanet().getType());
        	if(Game.getInstance().getPlanet().getType() == PlanetType.Arene) {
        		nextState = "Battle";
        	} else {
            	nextState = "Space";
        	}
        	endView = true;
        	return true;
        }
        return false;
	}
	
	private void drawElements(){
		planetWindow.clear();
		planetWindow.draw(FondSprite);
		planetWindow.draw(vaisseauSprite);
		
		Planet p = Game.getInstance().getPlanet();
		p.run(vaisseau);
		
		planetWindow.draw(boutonSuivantSprite);
		
		planetWindow.display();
	}
}
