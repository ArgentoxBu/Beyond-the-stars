package controller;

import java.awt.Point;

// poss�de les fonctions appel�es en cas d'appui sur une touche dans la view BattleView

public class BattleController {

	public BattleController() {
	}
	
	public void caseClicGauche( Point p ){
		System.out.println("CASE CLIQUEE : " + p.x + ";" + p.y );
	}
	
	public void caseClicDroit( Point p ){
		System.out.println("CASE CLIQUEE DROIT : " + p.x + ";" + p.y );
	}
	
	public void Touche1Cliquee( Point p ){
		System.out.println("Comp�tence n�1 Active");
	}
	
	public void Touche2Cliquee( Point p ){
		System.out.println("Comp�tence n�2 Active");
	}	
	
	public void Touche3Cliquee( Point p ){
		System.out.println("Comp�tence n�3 Active");
	}	
}
