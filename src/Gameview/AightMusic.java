package Gameview;

import java.io.File;
import java.io.IOException;

import org.jsfml.audio.Music;

public class AightMusic {
	
	File f;
	Music m;
	
	// zik al�atoire
	public AightMusic() {
		String name;
		
		int alea = alea(1,2);
		if ( alea == 1 ) name = "swag.ogg";
		else name = "swag2.ogg";
		
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
	
	public AightMusic( String name ) {
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
	
	//retourne un int al�atoire entre a et b compris
	private int alea ( int a, int b) {
		assert(a<b);
		return (int)(Math.random() * (b-a+1)) + a;
	}
}
