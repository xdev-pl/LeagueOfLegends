package pl.luxdev.lol.basic;

import org.bukkit.entity.Player;

import pl.luxdev.lol.types.ChampType;
import pl.luxdev.lol.types.TeamType;

public class User {

	private Player player;
	private ChampType champ;
	private TeamType team;
	
	public User(Player p){
		player = p;
		champ = null;
		team = null;
	}
	public ChampType getChampion(){
		return champ;
	}
	public boolean hasChampion(ChampType clazz){
		if(champ.equals(clazz)){
			return true;
		}
		return false;
	}
	public void setChampion(ChampType clazz){
		champ = clazz;
	}
	public Player getPlayer(){
		return player;
	}
	public TeamType getTeam() {
		return team;
	}
	public void setTeam(TeamType t) {
		team = t;
	}
}
