package pl.luxdev.lol.managers;

import java.util.ArrayList;
import java.util.List;

import pl.luxdev.lol.basic.Team;
import pl.luxdev.lol.basic.User;

public class TeamManager {
	
	private static List<Team> teams = new ArrayList<Team>();
	
	public static void addTeam(Team team){
		teams.add(team);
	}
	public void removeTeam(Team team){
		teams.remove(team);
	}
	public static List<Team> getTeams(){
		return teams;
	}
	public static Team getTeamByUser(User u){
		for(Team team : teams){
			if(team.getType().equals(u.getTeam())){
				return team;
			}
		}
		return null;
	}
	public static void clearTeams(){
		teams.clear();
	}
}