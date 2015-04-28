package model;

public class PouvoirSpecial {
	
	private String name;
	private String description;
	
	private int bonusAttack;
	private int bonusMobilite;
	private int bonusDefense;
	private int bonusConstitution;
	
	public PouvoirSpecial ( String name, String description, int att, int mob, int def, int cons ) {
		this.name = name;
		this.description = description;
		
		this.bonusAttack = att;
		this.bonusConstitution = cons;
		this.bonusDefense = def;
		this.bonusMobilite = mob;
	}

	// ajouter les stats qui seront augmentées
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBonusAttack() {
		return bonusAttack;
	}

	public void setBonusAttack(int bonusAttack) {
		this.bonusAttack = bonusAttack;
	}

	public int getBonusMobilite() {
		return bonusMobilite;
	}

	public void setBonusMobilite(int bonusMobilite) {
		this.bonusMobilite = bonusMobilite;
	}

	public int getBonusDefense() {
		return bonusDefense;
	}

	public void setBonusDefense(int bonusDefense) {
		this.bonusDefense = bonusDefense;
	}

	public int getBonusConstitution() {
		return bonusConstitution;
	}

	public void setBonusConstitution(int bonusConstitution) {
		this.bonusConstitution = bonusConstitution;
	}
}
