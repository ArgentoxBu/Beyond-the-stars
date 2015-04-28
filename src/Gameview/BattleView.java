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
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			VoidTexture.loadFromFile(Paths.get("rsc\\void.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			Asteroide1Texture.loadFromFile(Paths.get("rsc\\aste1.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			Asteroide2Texture.loadFromFile(Paths.get("rsc\\aste2.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			Asteroide3Texture.loadFromFile(Paths.get("rsc\\aste3.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			AllyTexture.loadFromFile(Paths.get("rsc\\ally.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			EnnemyTexture.loadFromFile(Paths.get("rsc\\enemy.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
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
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(VoidTexture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case 1:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(Asteroide1Texture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case 2:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(Asteroide2Texture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case 3:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(Asteroide3Texture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case -1:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(HeroTexture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case -2:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(AllyTexture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				case -3:
					BattleWindow.draw(spriteCases.get(k));
					spriteCases.get(k).setTexture(EnnemyTexture);
					spriteCases.get(k).setPosition(150+i*38,10+j*38);
					break;
				default:

				}
				k++;
			}
		}
		return spriteCases;
	}

}
