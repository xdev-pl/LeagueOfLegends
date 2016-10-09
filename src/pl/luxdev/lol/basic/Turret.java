package pl.luxdev.lol.basic;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;

import pl.luxdev.lol.managers.ConfigManager;
import pl.luxdev.lol.types.LineType;
import pl.luxdev.lol.types.TeamType;

public class Turret {
	
	private final String name;
	private int hp;
	private int attackStrength;
	private TeamType team;
	private LineType line;
	private Location location;
	private boolean destroyed;
	private Entity target;
	private Hologram hologram;
	
	public Turret(String s){
		FileConfiguration yml = ConfigManager.getCfg();
		name = yml.getString("turrets." + s + ".location");
//		hp = yml.getInt("turrets." + s + ".hp");
//		attackStrength = yml.getInt("turrets." + s + ".attackStrength");
//		team = TeamType.valueOf(yml.getString("turrets." + s + ".allyteam"));
//		location = Utils.locFromString(yml.getString("turrets." + s + ".location"));
	}
	public String getName() {
		return name;
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
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	public TeamType getTeam() {
		return team;
	}
	public void setTeam(TeamType t) {
		team = t;
	}
	
	public LineType getLine() {
		return line;
	}
	public void setLine(LineType l) {
		line = l;
	}
	public Entity getTarget(){
		return target;
	}
	public void setTarget(Entity t){
		target = t;
	}
	public Hologram getHologram() {
		return hologram;
	}
	public void setHologram(Hologram hologram) {
		this.hologram = hologram;
	}
	
}
