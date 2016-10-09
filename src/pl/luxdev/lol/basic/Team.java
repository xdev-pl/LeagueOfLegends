package pl.luxdev.lol.basic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.luxdev.lol.types.TeamType;

public class Team {
	
	private final String name;
	private Location spawnLocation;
	private TeamType type;
	private Nexus nexus;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Inhibitor> inhibitors = new ArrayList<Inhibitor>();
	private ArrayList<Turret> turrets = new ArrayList<Turret>();
	
	
	public Team(String s){
		name = s;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public int getPlayersAmount() {
		return players.size();
	}
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public void setSpawnLocation(Location spawnLoc) {
		this.spawnLocation = spawnLoc;
	}

	public String getName() {
		return name;
	}

	public TeamType getType() {
		return type;
	}

	public void setType(TeamType type) {
		this.type = type;
	}
	
	public void setNexus(Nexus n){
		nexus = n;
	}
	
	public Nexus getNexus(){
		return nexus;
	}
	
	public ArrayList<Inhibitor> getInhibitors(){
		return inhibitors;
	}
	
	public void addInhibitor(Inhibitor i){
		inhibitors.add(i);
	}
	
	public void removeInhibitor(Inhibitor i){
		if(inhibitors.contains(i)) inhibitors.remove(i);
	}
	
	public void clearInhibitors(){
		inhibitors.clear();
	}
	
	public ArrayList<Turret> getTurrrets(){
		return turrets;
	}
	
	public void addTurret(Turret t){
		turrets.add(t);
	}
	
	public void removeTurret(Turret t){
		if(turrets.contains(t)) turrets.remove(t);
	}
	
	public void clearTurrets(){
		turrets.clear();
	}

}
