package planetevents;

public class LostTurtle extends PlanetEvent {

	@Override
	public void run() {
		System.out.println("Une tortue g�ante qui semble d�river ne semble pas vous avoir vu");
		System.out.println("A�e ! Elle vous rentre dedans...");
		System.out.println("Par chance, votre bouclier a peu souffert. Bouclier -2");
	}

}
