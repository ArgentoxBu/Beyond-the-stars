package planetevents;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run() {
		System.out.println("Une tortue géante qui semble dériver ne semble pas vous avoir vu");
		System.out.println("Aïe ! Elle vous rentre dedans...");
		System.out.println("Par chance, votre bouclier a peu souffert. Bouclier -2");
	}

}
