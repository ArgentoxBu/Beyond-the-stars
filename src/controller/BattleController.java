package controller;

import java.awt.Point;
import java.util.ArrayList;

import model.Competence;
import model.Joueur;

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
	private boolean endView;
	private ArrayList<Sprite> spriteCases ;
	private int iCase, yCase = -1;
	private Game game;
	
	private ArrayList<Point> casesClickable;
	private Competence competenceEnCours;
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
		clickMode = "normal";
		game = Game.getInstance();
		casesClickable = new ArrayList<Point>();
	}

	public String lancer(){
		spriteCases = maBattleView.AfficherGrille(spriteCases);
		drawElements();
		
		while(!endView){
			for(Event event : maBattleView.BattleWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					maBattleView.BattleWindow.close();
					return "EndGame";
				}

				if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				{
					if(detecterClic(event)){
						drawElements();
					}
				}
				
				/*if(event.type == Event.Type.MOUSE_MOVED)
				{
					if(caseSurvolee(event)){
						drawElements();
					}
				}*/
				
				if(event.type == Event.Type.KEY_PRESSED)
				{
					if(detecterKeyPressed(event)){
						drawElements();
					}
				}
				
				
				endView = maBattleView.endView;
			}
		}
		return "EndGame";
	}
	
	public boolean detecterClic(Event myEvent){		
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(maBattleView.BattleWindow);
		int i=0, j=0;

		for (Sprite e : spriteCases){
			if(e.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
				iCase = -1; yCase = -1;
				iCase = j; yCase = i%15;
				caseClic(new Point(iCase, yCase));
				return true;
			}
			i++;if(i%15==0){j++;}
		}
		return false;
		//detecter bouton fermeture fenetre endview = true;
	}
	
	public boolean caseSurvolee(Event myEvent){
		myEvent.asMouseEvent();
		Vector2i pos = new Vector2i(0,0);
		pos = Mouse.getPosition(maBattleView.BattleWindow);
		int i=0, j=0;


		for (Sprite e : spriteCases){
			if(e.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
				iCase = j; yCase = i%15;
				caseSurvolee(new Point(iCase, yCase));
				return true;
			}
			i++;if(i%15==0){j++;}
		}
		return false;
	}
	
	public void caseClic( Point p ){
		
		// TODO A enlever, tour du joueur actuel pas forcément cash!
		game.getGrilleTBS().setMyTurn(true);
		if ( game.getGrilleTBS().isMyTurn() ) {
			if ( clickMode == "normal") {
				if ( game.getGrilleTBS().getValeurCase(p) == -1 ) {
					System.out.println("Clic sur le vaisseau");
					clickMode = "deplacement";
					casesClickable = game.getGrilleTBS().getDeplacementCases(game.getGrilleTBS().getJoueurs().get(0));
					maBattleView.placerHalo(casesClickable, 0);
				}
			}
			else if ( clickMode == "deplacement" ) {
				if ( casesClickable.contains(p) )
					Game.getInstance().getGrilleTBS().deplacerJoueur(0, p);
				maBattleView.resetHalo();
				clickMode = "normal";
			}
			else if ( clickMode == "competence1" || clickMode == "competence2" || clickMode == "competence3" ) {
				if ( casesClickable.contains(p) ) {
					int index = Game.getInstance().getGrilleTBS().getIndexJoueurCase(p);
					if ( index != -1 )
						Game.getInstance().getGrilleTBS().getCombat().useCompetence( 0, index, competenceEnCours);
					
					Game.getInstance().getGrilleTBS().setCompetenceUsed();
				}
				maBattleView.resetHalo();
				clickMode = "normal";
			}
		}
	}

	public boolean detecterKeyPressed(Event myEvent){
		myEvent.asKeyEvent();
		
		if(myEvent.asKeyEvent().key == Key.A){
			Touche1Pushed();
			return true;
		}
		
		else if(myEvent.asKeyEvent().key == Key.Z){
			Touche2Pushed();
			return true;
		}
		else if(myEvent.asKeyEvent().key == Key.E)
		{
			Touche3Pushed();
			return true;
		}
		else if(myEvent.asKeyEvent().key == Key.SPACE)
		{
			ToucheSpacePushed();
			return true;
		}
		return false;
	}
	
	public void drawElements(){
		maBattleView.run();
		spriteCases = maBattleView.AfficherGrille(spriteCases);
		maBattleView.afficherHalo();
		maBattleView.BattleWindow.display();
	}

	public void caseSurvolee( Point p ){
		//System.out.println("CASE SURVOLEE : " + p.x + ";" + p.y );
	}

	public void Touche1Pushed(){
		if ( game.getGrilleTBS().isMyTurn() ) {
			if ( clickMode != "competence1" && !Game.getInstance().getGrilleTBS().getCombat().isCompetenceUsed()) {
				competenceEnCours = Game.getInstance().getGrilleTBS().getJoueurs().get(0).getVaisseau().getCompetencesUtilisables().get(0);
				Joueur j = Game.getInstance().getGrilleTBS().getJoueurs().get(0);
				casesClickable = Game.getInstance().getGrilleTBS().getCompetenceCases(j, competenceEnCours);
				maBattleView.resetHalo();
				maBattleView.placerHalo(casesClickable, 1);
				clickMode = "competence1";
			}
			else {
				maBattleView.resetHalo();
				clickMode = "normal";
			}
		}
	}

	public void Touche2Pushed(){
		if ( game.getGrilleTBS().isMyTurn() ) {
			if ( clickMode != "competence2" && !Game.getInstance().getGrilleTBS().getCombat().isCompetenceUsed()) {
				competenceEnCours = Game.getInstance().getGrilleTBS().getJoueurs().get(0).getVaisseau().getCompetencesUtilisables().get(1);
				Joueur j = Game.getInstance().getGrilleTBS().getJoueurs().get(0);
				casesClickable = Game.getInstance().getGrilleTBS().getCompetenceCases(j, competenceEnCours);
				maBattleView.resetHalo();
				maBattleView.placerHalo(casesClickable, 1);
				clickMode = "competence2";
			}
			else {
				maBattleView.resetHalo();
				clickMode = "normal";
			}
		}
	}	

	public void Touche3Pushed(){
		if ( game.getGrilleTBS().isMyTurn() ) {
			if ( clickMode != "competence3" && !Game.getInstance().getGrilleTBS().getCombat().isCompetenceUsed()) {
				competenceEnCours = Game.getInstance().getGrilleTBS().getJoueurs().get(0).getVaisseau().getCompetencesUtilisables().get(2);
				Joueur j = Game.getInstance().getGrilleTBS().getJoueurs().get(0);
				casesClickable = Game.getInstance().getGrilleTBS().getCompetenceCases(j, competenceEnCours);
				maBattleView.resetHalo();
				maBattleView.placerHalo(casesClickable, 1);
				clickMode = "competence3";
			}
			else {
				maBattleView.resetHalo();
				clickMode = "normal";
			}
		}
	}
	
	public void ToucheSpacePushed() {
		game.getGrilleTBS().setMyTurn(false);
		Game.getInstance().getGrilleTBS().getCombat().tourSuivant();
	}
}
