
import java.io.File;
import java.io.IOException;

import org.jsfml.audio.Music;

import Gameview.AightMusic;
import Gameview.HangarView;
import controller.Game;

public class Main {
	
	public static void main(String[] args) {

		Game P = new Game();
		P.start();
		
		HangarView monHangar = new HangarView(P);
		
		monHangar.run();
	}
}

