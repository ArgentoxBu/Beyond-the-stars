package controller;

import java.awt.Point;
import java.util.ArrayList;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import Gameview.BattleView;

// possède les fonctions appelées en cas d'appui sur une touche dans la view BattleView

public class BattleController {

	private BattleView maBattleView;
	private Boolean endView;
	private ArrayList<Sprite> spriteCases ;
	private int iCase, yCase = -1;

	private String clickMode;
	
	public BattleController(BattleView maBattleView) {
		spriteCases = new ArrayList<Sprite>();
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				spriteCases.add(new Sprite());
			}
		}
		this.maBattleView = maBattleView;
		maBattleView.start();
		endView = false;
		clickMode = "Normal";
	}

	public String lancer(){
		while(!endView){
			for(Event event : maBattleView.BattleWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					maBattleView.BattleWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					detecterClic(event);
				}
				
				if(event.type == Event.Type.MOUSE_MOVED)
				{
					detecterCaseSurvolee(event);
				}
				
				if(event.type == Event.Type.KEY_PRESSED)
				{
					detecterKeyPressed(event);
				}
				
				maBattleView.run();
				spriteCases = maBattleView.AfficherCases(spriteCases);			
				maBattleView.BattleWindow.display();
				endView = maBattleView.endView;
			}
		}
		return "EndGame";
	}
	
	public void detecterClic(Event myEvent){		
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(maBattleView.BattleWindow);
		int i=0, j=0;


		for (Sprite e : spriteCases){
			if(e.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
				iCase = j; yCase = i%15;
				caseClic(new Point(iCase, yCase));
			}
			i++;if(i%15==0){j++;}
		}
		//detecter bouton fermeture fenetre endview = true;
	}
	
	public void detecterCaseSurvolee(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(maBattleView.BattleWindow);
		int i=0, j=0;


		for (Sprite e : spriteCases){
			if(e.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
				iCase = j; yCase = i%15;
				caseSurvolee(new Point(iCase, yCase));
			}
			i++;if(i%15==0){j++;}
		}
	}
	
	public void detecterKeyPressed(Event myEvent){
		myEvent.asKeyEvent();
		
		if(myEvent.asKeyEvent().key == Key.A){
			Touche1Pushed();
		}
		else if(myEvent.asKeyEvent().key == Key.Z){
			Touche2Pushed();
		}
		else if(myEvent.asKeyEvent().key == Key.E)
		{
			Touche3Pushed();
		}
		else if(myEvent.asKeyEvent().key == Key.SPACE)
		{
			ToucheSpacePushed();
		}
		
	}

	public void caseClic( Point p ){
		System.out.println("CASE CLIQUEE : " + p.x + ";" + p.y );
	}

	public void caseSurvolee( Point p ){
		System.out.println("CASE CLIQUEE DROIT : " + p.x + ";" + p.y );
	}

	public void Touche1Pushed(){
		System.out.println("Compétence n°1 Active");
	}

	public void Touche2Pushed(){
		System.out.println("Compétence n°2 Active");
	}	

	public void Touche3Pushed(){
		System.out.println("Compétence n°3 Active");
	}
	
	public void ToucheSpacePushed() {
		System.out.println("Fin du tour");
	}
}
