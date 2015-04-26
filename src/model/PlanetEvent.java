package model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PlanetEvent {
	public abstract void run();
	
	// Ecrire le nom de chaque classe PlanetEvent
	private static ArrayList<String> allEvent = new ArrayList<>(Arrays.asList(
			"Lol",
			"MDR",
			"STP"));
	public static ArrayList<String> getAllEvents() {
		return allEvent;
	}
}
