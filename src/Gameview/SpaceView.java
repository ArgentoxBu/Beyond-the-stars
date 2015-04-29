package Gameview;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import model.Planet;
import model.SpaceMap;
import model.Vaisseau;

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

public class SpaceView {
	private SpaceMap spaceMap = new SpaceMap();
	public RenderWindow SpaceWindow;
	public Game monGame;
	private Texture FondTexture = new Texture();
	private Vector<Texture> planetsTexture = new Vector<Texture>();
	private Sprite FondSprite = new Sprite();
	private Vector<Sprite> planetsSprite = new Vector<Sprite>();
	private boolean endView;
	private String nextState;
	
	private Texture retourHangarTexture = new Texture();
	private Sprite retourHangarSprite = new Sprite();
	
	private Font Font = new Font();
	private Text Hangar = new Text();
	
	Vaisseau vaisseau;
	
	public SpaceView(Game P, RenderWindow maRenderWindow) {
		SpaceWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public String run(Vaisseau v) {
		vaisseau = v;
		chargerImages();
		configurerTextures();
		
		drawElements();

		while(!endView) {
	
			
			for(Event event : SpaceWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					SpaceWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					detecterClic(event);
						drawElements();
				}

				
			}
		}
		
		return nextState;
	}

	private void chargerImages() {
		try {
			FondTexture.loadFromFile(Paths.get("rsc\\spaceBackground.png"));
			retourHangarTexture.loadFromFile(Paths.get("rsc\\hangarPlanet.png"));
			Font.loadFromFile(Paths.get("rsc\\Starjedi.ttf"));
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
		retourHangarSprite.setTexture(retourHangarTexture);
		retourHangarSprite.setPosition(50,450);
		
		int taille_Font = 24;

		Hangar.setFont(Font);
		Hangar.setCharacterSize(taille_Font);
		Hangar.setString("Hangar");
		Hangar.setPosition(50,425);
		
		int x = 230, y = 200, dy = 100;
		for(Texture t : planetsTexture) {
			Sprite s = new Sprite();
			s.setTexture(t);
//			s.setPosition(x, y);
//			x += 120;
//			y += dy;
//			dy *= (-1);
			planetsSprite.add(s);
		}
		
		planetsSprite.get(0).setPosition(100,100);
		planetsSprite.get(1).setPosition(545,165);
		planetsSprite.get(2).setPosition(390,440);
		
	}
	
	private boolean detecterClic(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(SpaceWindow);

		for(Sprite s : planetsSprite) {
	        if(s.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
		    	//passer a la fenetre suivante et enregistrer mes choix
	        	Planet planet = spaceMap.getPlanets().get(planetsSprite.indexOf(s));
	        	Game.getInstance().setPlanet(planet);
				endView = true;
				nextState = "Planet";
	        }
		}
		
		if(retourHangarSprite.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
			endView = true;
			nextState = "Hangar";
		}
		return false;
	}
	
	public void drawElements(){
		SpaceWindow.clear();
		SpaceWindow.draw(FondSprite);
		SpaceWindow.draw(retourHangarSprite);
		SpaceWindow.draw(Hangar);
		drawPlanets();
		SpaceWindow.display();
	}
}
