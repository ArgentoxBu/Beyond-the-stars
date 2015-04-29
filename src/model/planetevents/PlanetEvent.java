package model.planetevents;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;

import controller.Game;

import model.Vaisseau;

public abstract class PlanetEvent {
	private Sprite guiMenuSprite = new Sprite();
	protected String description = "";
	private Text descriptionText = new Text();
	
	public void run(Vaisseau v) {
		try {
			Texture t = new Texture();
			t.loadFromFile(Paths.get("rsc\\guiTextMenu.png"));
			guiMenuSprite.setTexture(t);
			guiMenuSprite.setPosition(0, 420);
			RenderWindow rw = Game.getInstance().getRenderWind();
			rw.draw(guiMenuSprite);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// Ecrire le nom de chaque classe PlanetEvent
	private static ArrayList<String> allEvent = new ArrayList<>(Arrays.asList(
			"LostTurtle",
			"SpaceGoatAttack",
			"UnexpectedMessiah",
			"Battle"));
	public static ArrayList<String> getAllEvents() {
		return allEvent;
	}
	
	protected void drawDescription() {
		Font font = new Font();
		try {
			font.loadFromFile(Paths.get("rsc\\spaceranger3d.ttf"));
			descriptionText.setFont(font);
			descriptionText.setCharacterSize(18);
			descriptionText.setString(description);
			descriptionText.setPosition(30, 450);
			RenderWindow rw = Game.getInstance().getRenderWind();
			rw.draw(descriptionText);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
