package pl.luxdev.lol.basic;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import pl.luxdev.lol.managers.DataManager;
import pl.luxdev.lol.types.TeamType;
import pl.luxdev.lol.utils.Utils;

public class Turret {
	
	private String name;
	private int hp;
	private int attackStrength;
	private TeamType allyTeam;
	private Location location;
//	private Entity target;
	
	private boolean destroyed;
	
	public Turret(String s){
		FileConfiguration yml = DataManager.getCfg();
		name = yml.getString("turrets." + s + ".location");
		hp = yml.getInt("turrets." + s + ".hp");
		attackStrength = yml.getInt("turrets." + s + ".attackStrength");
		allyTeam = TeamType.valueOf(yml.getString("turrets." + s + ".allyteam"));
		location = Utils.locFromString(yml.getString("turrets." + s + ".location"));
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Location getLocation() {
		return location;
	}
	public int getAttackStrength() {
		return attackStrength;
	}
	public void setAttackStrength(int attackStrength) {
		this.attackStrength = attackStrength;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	public TeamType getTeam() {
		return allyTeam;
	}
	public void setTeam(TeamType team) {
		allyTeam = team;
	}
}
