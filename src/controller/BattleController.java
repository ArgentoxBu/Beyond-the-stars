package controller;

import java.awt.Point;

import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import Gameview.BattleView;

// possède les fonctions appelées en cas d'appui sur une touche dans la view BattleView

public class BattleController {

	private BattleView maBattleView;
	private Boolean endView;

	public BattleController(BattleView maBattleView) {
		this.maBattleView = maBattleView;
		maBattleView.start();
		endView = false;
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
					maBattleView.detecterClic(event);
				}
				
				maBattleView.run();
				endView = maBattleView.endView;
			}
		}
		return "EndGame";
	}

	public void caseClicGauche( Point p ){
		System.out.println("CASE CLIQUEE : " + p.x + ";" + p.y );
	}

	public void caseClicDroit( Point p ){
		System.out.println("CASE CLIQUEE DROIT : " + p.x + ";" + p.y );
	}

	public void Touche1Cliquee( Point p ){
		System.out.println("Compétence n°1 Active");
	}

	public void Touche2Cliquee( Point p ){
		System.out.println("Compétence n°2 Active");
	}	

	public void Touche3Cliquee( Point p ){
		System.out.println("Compétence n°3 Active");
	}	
}
