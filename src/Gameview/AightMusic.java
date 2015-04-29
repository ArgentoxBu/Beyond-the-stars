package Gameview;

import java.io.File;
import java.io.IOException;

import org.jsfml.audio.Music;

public class AightMusic {
	
	File f;
	Music m;
	
	// zik aléatoire
	public AightMusic( String type ) {
		String name;
		
		int alea = alea(1,2);
		if ( type == "hangar" || type == "battle") alea = 1;
		if ( type == "space" ) alea = 2;
		name = type + alea + ".ogg";
		
		f = new File("music/"+name);
		if (!f.exists()) {
			System.out.println("MUSIQUE INTROUVABLE ICI : '" + f.toString() + "'");
		}
		else {
			m = new Music();
			try {
				m.openFromFile(f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void balancer() {
		m.play();
	}
	
	public void stopper() {
		m.stop();
	}	
	
	public void pauser() {
		m.pause();
	}
	
	//retourne un int aléatoire entre a et b compris
	private int alea ( int a, int b) {
		assert(a<b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
}
