package model;

import java.util.Vector;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

public class SpaceMap extends Graph {
	// Noeud Planete	
	private class PlanetNode extends Node<Planet> {
		public PlanetNode() {
			super(new Planet());
		}
		
		public PlanetNode(Planet.PlanetType type) {
			super(new Planet(type));
		}
	}
	
	private final int planetNumber = 2;
	
	public SpaceMap() {
		super();
		super.addNode(new PlanetNode(Planet.PlanetType.Arene));
		for(int i = 0; i<planetNumber; i++) {
			PlanetNode node;
			boolean found;
			do {
				found = false;
				node = new PlanetNode();
				for(Node<Planet> n : getNodes())
					if(node.getData().getType() == n.getData().getType())
						found = true;
			} while(found);
			super.addNode(new PlanetNode());
		}
		
		for(Node<Planet> node : super.getNodes())
			for(Node<Planet> noder : super.getNodes())
				super.addEdge(new Edge(node, noder));
	}
	
	public Vector<Planet> getPlanets() {
		Vector<Planet> planets = new Vector<Planet>();
		for(Node<Planet> n : getNodes())
			planets.add(n.getData());
		return planets;
	}
}
