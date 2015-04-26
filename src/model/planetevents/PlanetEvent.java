package model.planetevents;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PlanetEvent {
	public abstract void run();
	
	// Ecrire le nom de chaque classe PlanetEvent
	private static ArrayList<String> allEvent = new ArrayList<>(Arrays.asList(
			"LostTurtle",
			"SpaceGoatAttack",
			"UnexpectedMessiah"));
	public static ArrayList<String> getAllEvents() {
		return allEvent;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
