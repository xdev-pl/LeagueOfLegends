package pl.luxdev.lol.basic.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.type.GameState;

public class Arena {

	private final String name;
	private String displayName;
	private GameState state;
	//private List<User> users = new ArrayList<>();
	private List<Team> teams = new ArrayList<>(2);
	
	public Arena(String name) {
		this.name = name;
		this.state = GameState.WAITING;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDisplayName(String s){
		displayName = s;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setState(GameState s) {
		state = s;
	}
	
	public GameState getState() {
		return state;
	}
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void addTeam(Team t) {
		teams.add(t);
	}
	
	public void removeTeam(Team t) {
		if(teams.contains(t)) teams.remove(t);
	}
	
/*	public List<User> getUsers() {
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public void removeUser(User u) {
		users.remove(u);
	}
	
	public void clearUsers() {
		users.clear();
	}
*/	
	public boolean containsPlayer(Player p) {
		for(Team t : teams){
			for(User u : t.getUsers()){
				if (u.getPlayer().equals(p)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
