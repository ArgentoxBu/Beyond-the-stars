package Gameview;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import controller.Game;

public class Hangar2View {

	public RenderWindow Hangar2Window;
	public Game monGame;

	public Hangar2View(Game P, RenderWindow maRenderWindow)
	{
		Hangar2Window = maRenderWindow;
		monGame = P;
	}

	public String run(){

		while(Hangar2Window.isOpen() )
		{
			Hangar2Window.clear();
			for(Event event : Hangar2Window.pollEvents())
			{
				if(event.type == Type.CLOSED){
					Hangar2Window.close();
					return "endGame";
				}
			}
			Hangar2Window.display();
		}
		return "endGame";
	}
}

