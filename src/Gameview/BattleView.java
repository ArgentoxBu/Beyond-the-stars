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
	private Sprite voidie = new Sprite();
	private Sprite asteroide1 = new Sprite();
	private Sprite asteroide2 = new Sprite();
	private Sprite asteroide3 = new Sprite();
	private Sprite ally = new Sprite();
	private Sprite ennemy = new Sprite();
	private Sprite hero = new Sprite();
	private int[][] casesBattle;

	public BattleView(Game P,RenderWindow maRenderWindow)
	{
		BattleWindow = maRenderWindow;
		monGame = P;
	}

	public String run(){

		chargerImages();
		FondSprite.setTexture(FondTexture);

		while(BattleWindow.isOpen() )
		{
			for(Event event : BattleWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					BattleWindow.close();
				}

				BattleWindow.clear();
				BattleWindow.draw(FondSprite);

				int i,j;
				casesBattle = monGame.getGrilleTBS().getCases();

				for(i=0;i<15;i++){
					for(j=0;j<15;j++){

						switch (casesBattle[i][j])
						{
						case 0:
							BattleWindow.draw(voidie);
							voidie.setTexture(VoidTexture);
							voidie.setPosition(150+i*38,10+j*38);
							break;
						case 1:
							BattleWindow.draw(asteroide1);
							asteroide1.setTexture(Asteroide1Texture);
							asteroide1.setPosition(150+i*38,10+j*38);
							break;
						case 2:
							BattleWindow.draw(asteroide2);
							asteroide2.setTexture(Asteroide2Texture);
							asteroide2.setPosition(150+i*38,10+j*38);
							break;
						case 3:
							BattleWindow.draw(asteroide3);
							asteroide3.setTexture(Asteroide3Texture);
							asteroide3.setPosition(150+i*38,10+j*38);
							break;
						case -1:
							BattleWindow.draw(hero);
							hero.setTexture(HeroTexture);
							hero.setPosition(150+i*38,10+j*38);
							break;
						case -2:
							BattleWindow.draw(ally);
							ally.setTexture(AllyTexture);
							ally.setPosition(150+i*38,10+j*38);
							break;
						case -3:
							BattleWindow.draw(ennemy);
							ennemy.setTexture(EnnemyTexture);
							ennemy.setPosition(150+i*38,10+j*38);
							break;
						default:

						}
					}
				}

				BattleWindow.display();
			}
		}
		return "endGame";
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

}
