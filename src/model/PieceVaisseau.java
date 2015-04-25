package model;

public abstract class PieceVaisseau {
	private int weight;
	private int attack;
	private int defense;
	private int constitution;
	private int mobility;
	private int luck;
	private String name;
	private String description;
	
	public PieceVaisseau(String name, int weight, int attack, int defense, int constitution, int mobility, int luck) {
		this.weight = weight;
		this.attack = attack;
		this.defense = defense;
		this.constitution = constitution;
		this.mobility = mobility;
		this.luck = luck;
		this.name = name;
		this.description = "Pas de description disponible";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getMobility() {
		return mobility;
	}

	public void setMobility(int mobility) {
		this.mobility = mobility;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
