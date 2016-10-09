package pl.luxdev.lol.managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.Champion;
import pl.luxdev.lol.basic.Team;
import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.utils.Utils;

public class ConfigManager {
private static FileConfiguration config = null;
	
	public static void load(){
		FileManager.checkFiles();
		config = Main.getInst().getConfig();
		loadTeams();
		loadTurrets();
		loadChampions();
	}
	
	public static FileConfiguration getCfg(){
		if(Main.getInst().getConfig() == null) load();
		return config;
	}

	private static void loadTeams() {
		TeamManager.clearTeams();
		for(String s : getCfg().getConfigurationSection("teams").getKeys(false)){
			TeamManager.addTeam(new Team(s));
			Bukkit.broadcastMessage("Ladowanie teamu: " + s);
			
		}
		Utils.info("Zaladowano "+TeamManager.getTeams().size()+" teamow");	
	}
	
	private static void loadTurrets(){
		TurretManager.clearTurrets();
		for(String s : getCfg().getConfigurationSection("turrets").getKeys(false)){
			TurretManager.addTurret(new Turret(s));
			Bukkit.broadcastMessage("Dodawanie turretu: " + s);
		}
		Utils.info("Zaladowano "+TurretManager.getAllTurrets().size()+" turretow");	
	}
	
	private static void loadChampions(){
		ChampionManager.clearChampions();
		for(String s : getCfg().getConfigurationSection("champions").getKeys(false)){
			ChampionManager.addChampion(new Champion(s));
			Bukkit.broadcastMessage("Dodawanie championu: " + s);
		}
		Utils.info("Zaladowano "+ChampionManager.getChampions().size()+" championow");	
	}
}
