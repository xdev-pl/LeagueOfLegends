package pl.luxdev.lol.basic.game;

import java.util.ArrayList;

import org.bukkit.Location;

import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.type.TeamType;

public class Team {
	
	private final String name;
	private Location spawnLocation;
	private TeamType type;
	private Nexus nexus;
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Inhibitor> inhibitors = new ArrayList<Inhibitor>();
	private ArrayList<Turret> turrets = new ArrayList<Turret>();
	
	
	public Team(String s){
		name = s;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void addUser(User u) {
		users.add(u);
	}

	public int getPlayersAmount() {
		return users.size();
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

	public void addUsers(ArrayList<User> us) {
		for(User u : us){
			users.add(u);
		}
		
	}

}
