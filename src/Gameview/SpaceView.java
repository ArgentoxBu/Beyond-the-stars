package Gameview;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import model.Planet;
import model.SpaceMap;
import model.graph.Node;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import controller.Game;

public class SpaceView {
	private SpaceMap spaceMap = new SpaceMap();
	public RenderWindow SpaceWindow;
	public Game monGame;
	private Texture FondTexture = new Texture();
	private Vector<Texture> planetsTexture = new Vector<Texture>();
	private Sprite FondSprite = new Sprite();
	private Vector<Sprite> planetsSprite = new Vector<Sprite>();
	private boolean endView;
	
	public SpaceView(Game P, RenderWindow maRenderWindow) {
		SpaceWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run() {
		chargerImages();
		configurerTextures();

		while(!endView) {
			SpaceWindow.clear();
			SpaceWindow.draw(FondSprite);
			
			for(Event event : SpaceWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					SpaceWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					detecterClic(event);
				}

				drawPlanets();
				SpaceWindow.display();
			}
		}
		
		return "Planet";
	}

	private void chargerImages() {
		try {
			FondTexture.loadFromFile(Paths.get("rsc\\spaceBackground.png"));
			for(Planet p : spaceMap.getPlanets()) {
				Texture t = new Texture();
				t.loadFromFile(Paths.get("rsc\\" + p.getType().getFile()));
				planetsTexture.add(t);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void drawPlanets() {
		for(Sprite s : planetsSprite) {
			SpaceWindow.draw(s);
		}
	}
	
	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
		int x = 0, y = 200, dy = 100;
		for(Texture t : planetsTexture) {
			Sprite s = new Sprite();
			s.setTexture(t);
			s.setPosition(x, y);
			x += 120;
			y += dy;
			dy *= (-1);
			planetsSprite.add(s);
		}
	}
	
	private void detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(SpaceWindow);

		for(Sprite s : planetsSprite) {
	        if(s.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
		    	//passer a la fenetre suivante et enregistrer mes choix
	        	//spaceMap.getPlanets().get(planetsSprite.indexOf(s)).run(v);
				endView = true;
	        }
		}
	}
}
