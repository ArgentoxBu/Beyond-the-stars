package Gameview;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.Joueur;

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

public class BattleView {

	public RenderWindow BattleWindow;
	public Game monGame;
	private Texture FondTexture = new Texture();
	private Texture VoidTexture = new Texture();
	private Texture Asteroide1Texture = new Texture();
	private Texture Asteroide2Texture = new Texture();
	private Texture Asteroide3Texture = new Texture();
	private Texture AllyTexture = new Texture();
	private Texture HeroTexture = new Texture();
	private Texture EnnemyTexture = new Texture();
	private Sprite FondSprite = new Sprite();

	public boolean endView;

	public BattleView(Game P,RenderWindow maRenderWindow)
	{
		BattleWindow = maRenderWindow;
		monGame = P;
		endView = false;
	}

	public void start(){
		chargerImages();
		FondSprite.setTexture(FondTexture);
	}

	public void run(){
		BattleWindow.clear();
		BattleWindow.draw(FondSprite);
	}

	private void chargerImages(){
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\Battlefield.png"));
			VoidTexture.loadFromFile(Paths.get("rsc\\void.png"));
			Asteroide1Texture.loadFromFile(Paths.get("rsc\\aste1.png"));
			Asteroide2Texture.loadFromFile(Paths.get("rsc\\aste2.png"));
			Asteroide3Texture.loadFromFile(Paths.get("rsc\\aste3.png"));
			AllyTexture.loadFromFile(Paths.get("rsc\\ally.png"));
			EnnemyTexture.loadFromFile(Paths.get("rsc\\enemy.png"));
			HeroTexture.loadFromFile(Paths.get("rsc\\hero.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public ArrayList<Sprite> AfficherCases(ArrayList<Sprite> spriteCases){
		int i,j,k;k=0;

		int[][] casesBattle = monGame.getGrilleTBS().getCases();

		for(i=0;i<15;i++){
			for(j=0;j<15;j++){

				switch (casesBattle[i][j])
				{
				case 0:
					spriteCases.get(k).setTexture(VoidTexture);
					break;
				case 1:
					spriteCases.get(k).setTexture(Asteroide1Texture);
					break;
				case 2:
					spriteCases.get(k).setTexture(Asteroide2Texture);
					break;
				case 3:
					spriteCases.get(k).setTexture(Asteroide3Texture);
					break;
				case -1:
					spriteCases.get(k).setTexture(HeroTexture);
					break;
				case -2:
					spriteCases.get(k).setTexture(AllyTexture);
					break;
				case -3:
					spriteCases.get(k).setTexture(EnnemyTexture);
					break;
				default:

				}
				
				int x = 150 + i*38;
				int y = 10+j*38;
				spriteCases.get(k).setPosition(x, y);
				BattleWindow.draw(spriteCases.get(k));
				k++;
			}
		}
		return spriteCases;
	}

}
