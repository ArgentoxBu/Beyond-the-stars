
import Gameview.Hangar2View;
import Gameview.HangarView;
import controller.Game;

public class Main {

	public static void main(String[] args) {

		String Etat = "Hangar";

		Game P = new Game();
		P.start();

		while(Etat!="Engame")
		{
			switch(Etat){

				case "Hangar" :
					HangarView monHangar = new HangarView(P);
					Etat = monHangar.run();
					break;
					
				case "Hangar2" :
//					Hangar2View monHangar2 = new Hangar2View(P);
//					Etat = monHangar2.run();
					break;


			}
			HangarView monHangar = new HangarView(P);

			Etat = monHangar.run();

			System.out.println(Etat);
		}
	}
}

