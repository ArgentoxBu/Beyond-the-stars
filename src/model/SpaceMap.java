package model;

public class SpaceMap extends Graph {
	// Noeud Planete
	private class PlanetNode extends Node<Planet> {
		public PlanetNode() {
			super();
		}
	}
	
	private final int planetNumber = 10;
	
	public SpaceMap() {
		super();
		for(int i = 0; i<planetNumber; i++) {
			super.addNode(new PlanetNode());
		}
	}
}
