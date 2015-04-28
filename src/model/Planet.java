package model;

import java.util.ArrayList;
import java.util.Random;

import model.planetevents.Battle;
import model.planetevents.PlanetEvent;


public class Planet {
	public enum PlanetType {
		Desert("Desert", "planetEvent2.png"),
		Merchant("Merchant", "planetMerchant.png"),
		Rest("Rest", "planetRest.png"),
		Void("Void", "planetEvent.png"),
		Arene("Arene", "planetArene.png");
		
		private String type;
		private String file;
		
		PlanetType(String type, String file) {
			this.type = type;
			this.file = file;
		}

		public String getType() {
			return type;
		}

		public String getFile() {
			return file;
		}
	}
	
	private PlanetEvent event;
	private PlanetType type;
	private Random random;
	
	public Planet() {
		random = new Random();
		type = PlanetType.values()[random.nextInt(PlanetType.values().length)];
		event = getRandomEvent();
	}
	
	public Planet(PlanetType type) {
		this.type = type;
		if(type == PlanetType.Arene)
			event = new Battle();
		else
			event = getRandomEvent();
	}
	
	public PlanetType getType() {
		return type;
	}
	
	public void run(Vaisseau v) {
		event.run(v);
	}
	
	private PlanetEvent getRandomEvent() {
		// Utiliser le luck d'un vaisseau
		ArrayList<String> events = PlanetEvent.getAllEvents();
		int index = random.nextInt(events.size());
		String randomEvent = events.get(index);
		PlanetEvent event = null;
		try {
			Class<?> clazz = Class.forName("model.planetevents."+randomEvent);
			event = (PlanetEvent) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return event;
	}
}
