package pl.luxdev.lol.basic.game;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import pl.luxdev.lol.type.LineType;
import pl.luxdev.lol.type.TeamType;
import pl.luxdev.lol.util.element.Hologram;

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
		name = s;
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
	public Location getLocation(){
		return location;
	}
	public void setLocation(Location l){
		location = l;
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
