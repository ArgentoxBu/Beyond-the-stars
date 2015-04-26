package Gameview;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

public class HangarView {
	
	RenderWindow HangarWindow;
	
	public HangarView()
	{
		HangarWindow = new RenderWindow(new VideoMode(800, 600, 32), "Hangar");
	}
	
	public void run(){
		Texture image = new Texture();
		Sprite sprite = new Sprite();
		
		try
		{
			image.loadFromFile(Paths.get("rsc\\Hangar.png"));
		}
		catch(FileNotFoundException e1)
		{
			System.out.println(e1.getMessage());
		} 
		catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		sprite.setTexture(image);
		HangarWindow.draw(sprite);
		
		while(HangarWindow.isOpen())
		{
			for(Event event : HangarWindow.pollEvents())
			{
				if(event.type == Type.CLOSED){
					HangarWindow.close();
				}
				
				//HangarWindow.clear();
				
				HangarWindow.display();
			}
		}
		
		
	}
}
