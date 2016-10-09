package pl.luxdev.lol.basic;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import pl.luxdev.lol.managers.ConfigManager;
import pl.luxdev.lol.types.TeamType;
import pl.luxdev.lol.utils.Utils;

public class Team {
	
	private boolean inGame;
	private String name;
	private Location spawnLocation;
	private List<Player> players = Lists.newArrayList();
	private TeamType type;
	
	public Team(String s){
		FileConfiguration yml = ConfigManager.getCfg();
		name = yml.getString("teams." + s + ".name");
		type = TeamType.valueOf(yml.getString("teams." + s + ".type"));
		spawnLocation = Utils.locFromString(yml.getString("teams." + s + ".spawnloc"));
		inGame = false;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
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

	public void setName(String name) {
		this.name = name;
	}

	public TeamType getType() {
		return type;
	}

	public void setType(TeamType type) {
		this.type = type;
	}

}
