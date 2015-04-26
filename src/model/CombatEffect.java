package model;

// Stun sur un vaisseau en jeu
public class CombatEffect {
	
	private int duree;
	private String name;
	
	// boosters
	private int fixMobility;
	private int percentMobility;
	private int fixAttack;
	private int percentAttack;
	private int fixDefense;
	private int percentDefense;
	private int fixConstitution;
	private int percentConstitution;
	
	public CombatEffect(String name, int duree) {
		this.duree = duree;
		this.name = name;
		
		this.fixMobility = 0;
		this.percentMobility = 0;
		this.fixAttack = 0;
		this.percentAttack = 0;
		this.fixDefense = 0;
		this.percentDefense = 0;
		this.fixConstitution = 0;
		this.percentConstitution = 0;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFixMobility() {
		return fixMobility;
	}

	public void setFixMobility(int fixMobility) {
		this.fixMobility = fixMobility;
	}

	public int getPercentMobility() {
		return percentMobility;
	}

	public void setPercentMobility(int percentMobility) {
		this.percentMobility = percentMobility;
	}

	public int getFixAttack() {
		return fixAttack;
	}

	public void setFixAttack(int fixAttack) {
		this.fixAttack = fixAttack;
	}

	public int getPercentAttack() {
		return percentAttack;
	}

	public void setPercentAttack(int percentAttack) {
		this.percentAttack = percentAttack;
	}

	public int getFixDefense() {
		return fixDefense;
	}

	public void setFixDefense(int fixDefense) {
		this.fixDefense = fixDefense;
	}

	public int getPercentDefense() {
		return percentDefense;
	}

	public void setPercentDefense(int percentDefense) {
		this.percentDefense = percentDefense;
	}

	public int getFixConstitution() {
		return fixConstitution;
	}

	public void setFixConstitution(int fixConstitution) {
		this.fixConstitution = fixConstitution;
	}

	public int getPercentConstitution() {
		return percentConstitution;
	}

	public void setPercentConstitution(int percentConstitution) {
		this.percentConstitution = percentConstitution;
	}
}
