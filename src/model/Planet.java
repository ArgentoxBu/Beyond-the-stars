package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import planetevents.PlanetEvent;

public class Planet {
	public enum PlanetType {
		Desert("Desert"),
		Merchant("Merchant"); // A rajouter
		
		private String type;
		
		PlanetType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
		
	}
	
	private PlanetEvent event;
	private PlanetType type;
	private Random random;
	
	public Planet() {
		ArrayList<String> events = PlanetEvent.getAllEvents();
		random = new Random();
		type = PlanetType.values()[random.nextInt(PlanetType.values().length)];
		
		// Utiliser le luck d'un vaisseau
		int index = random.nextInt(events.size());
		String randomEvent = events.get(index);
		try {
			Class<?> clazz = Class.forName("planetevents."+randomEvent);
			event = (PlanetEvent) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PlanetType getType() {
		return type;
	}
}
