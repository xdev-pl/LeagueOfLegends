package pl.luxdev.lol.basic.game;

import pl.luxdev.lol.type.ChampType;

public class Champion {
	
	private final String name;
	private String displayName;
	private ChampType type;
	private int damage;

	public Champion(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}
	
	public String getDisplayname() {
		return displayName;
	}

	public void setDisplayname(String s) {
		displayName = s;
	}
	
	public ChampType getType() {
		return type;
	}

	public void setType(ChampType type) {
		this.type = type;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
