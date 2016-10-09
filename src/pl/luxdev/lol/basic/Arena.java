package pl.luxdev.lol.basic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.luxdev.lol.types.GameState;

public class Arena {
	//chyba narazie jest ok
	private final String name;
	private String displayName;
	private GameState state;
	private List<User> users = new ArrayList<>();
	private List<Location> spawns = new ArrayList<Location>(2);
	
	public Arena(String n){
		name = n;
		state = GameState.WAITING;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDisplayName(String s){
		displayName = s;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public void setState(GameState s){
		state = s;
	}
	
	public GameState getState(){
		return state;
	}
	
	public void setSpawns(List<Location> l){
		spawns.clear();
		for (int i = 0; i < l.size(); i++) {
			spawns.add(l.get(i));
		}
	}
	
	public void setSpawn(int i, Location l){
		spawns.set(i, l);
	}
	
	public List<Location> getLocs(){
		return spawns;
	}
	
	public Location getLoc(int i){
		return spawns.get(i);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public void addUser(User u){
		users.add(u);
	}
	
	public void removeUser(User u){
		users.remove(u);
	}
	
	public void clearUsers(){
		users.clear();
	}
	
	public boolean containsPlayer(Player p){
		for(User u : users){
			if(u.getPlayer().equals(p)) return true;
		}
		return false;
	}
	
	
}
