package Gameview;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

public class HangarView {
	
	public RenderWindow HangarWindow;
	private Texture FondTexture = new Texture();
	private Texture FlecheTexture = new Texture();
	private Font Font = new Font();
	private Sprite FondSprite = new Sprite();
	private Sprite FlecheDroiteMoteur = new Sprite();
	private Sprite FlecheDroiteCoque = new Sprite();
	private Sprite FlecheDroiteBouclier = new Sprite();
	private Sprite FlecheDroiteArmes = new Sprite();
	private Sprite FlecheGaucheMoteur = new Sprite();
	private Sprite FlecheGaucheCoque = new Sprite();
	private Sprite FlecheGaucheBouclier = new Sprite();
	private Sprite FlecheGaucheArmes = new Sprite();
	
	public HangarView()
	{
		HangarWindow = new RenderWindow(new VideoMode(800, 600, 32), "Hangar",WindowStyle.CLOSE);
	}
	
	public void run(){
		
		chargerImages();
		configurerTextures();
		
		int taille_Font = 25;
		
//		Titre.setFont(Font);
//		Titre.setCharacterSize((int)(1.90*taille_Font));
//		Titre.setString("HANGAR");
//      Titre.setPosition(HangarWindow.getSize().x/2-Titre.getLocalBounds().width/2, 20);
		
		while(HangarWindow.isOpen())
		{
			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					HangarWindow.close();
				}
				
				HangarWindow.clear();
				HangarWindow.draw(FondSprite);
				HangarWindow.draw(FlecheDroiteMoteur);
				HangarWindow.draw(FlecheDroiteCoque);
				HangarWindow.draw(FlecheDroiteBouclier);
				HangarWindow.draw(FlecheDroiteArmes);
				HangarWindow.draw(FlecheGaucheMoteur);
				HangarWindow.draw(FlecheGaucheCoque);
				HangarWindow.draw(FlecheGaucheBouclier);
				HangarWindow.draw(FlecheGaucheArmes);
				
				HangarWindow.display();
			}
		}
	}
	
	private void chargerImages(){
		try
		{
			FondTexture.loadFromFile(Paths.get("rsc\\Hangar.png"));
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
		
		try
		{
			FlecheTexture.loadFromFile(Paths.get("rsc\\fleche.png"));
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void configurerTextures()
	{
		FondSprite.setTexture(FondTexture);
		
		FlecheDroiteMoteur.setTexture(FlecheTexture);
		FlecheDroiteMoteur.rotate(90);
		FlecheDroiteMoteur.setPosition(190,220);
		
		FlecheDroiteCoque.setTexture(FlecheTexture);
		FlecheDroiteCoque.rotate(90);
		FlecheDroiteCoque.setPosition(420,213);
		
		FlecheDroiteBouclier.setTexture(FlecheTexture);
		FlecheDroiteBouclier.rotate(90);
		FlecheDroiteBouclier.setPosition(190,395);
		
		FlecheDroiteArmes.setTexture(FlecheTexture);
		FlecheDroiteArmes.rotate(90);
		FlecheDroiteArmes.setPosition(425,395);
		
		FlecheGaucheMoteur.setTexture(FlecheTexture);
		FlecheGaucheMoteur.rotate(-90);
		FlecheGaucheMoteur.setPosition(70,243);
		
		FlecheGaucheCoque.setTexture(FlecheTexture);
		FlecheGaucheCoque.rotate(-90);
		FlecheGaucheCoque.setPosition(270,235);
		
		FlecheGaucheBouclier.setTexture(FlecheTexture);
		FlecheGaucheBouclier.rotate(-90);
		FlecheGaucheBouclier.setPosition(70,418);
		
		FlecheGaucheArmes.setTexture(FlecheTexture);
		FlecheGaucheArmes.rotate(-90);
		FlecheGaucheArmes.setPosition(245,418);
	}
}
