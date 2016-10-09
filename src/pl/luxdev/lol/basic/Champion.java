package pl.luxdev.lol.basic;

import org.bukkit.configuration.file.FileConfiguration;

import pl.luxdev.lol.managers.ConfigManager;
import pl.luxdev.lol.types.ChampType;

public class Champion {
	
	private String name;
	private ChampType type;
	private int damage;

	public Champion(String s) {
		FileConfiguration yml = ConfigManager.getCfg();
		name = yml.getString("champions." + s + ".name");
		type = ChampType.valueOf(yml.getString("champions." + s + ".type"));
		damage = yml.getInt("champions." + s + ".damage");
	}

	public String getName() {
		return name;
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
