package pl.luxdev.lol.data;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.game.Arena;

public class Config {
	
	public static String MESSAGE_PREFIX;
	public static String MESSAGE_LEFT;
	public static String MESSAGE_JOIN;
	public static String MESSAGE_TEAM_WON;
	public static String MESSAGE_JOIN$ALL;
	public static String MESSAGE_GAME_STARTING$ALL;
	public static String MESSAGE_GAME_COUNTING;
	public static String MESSAGE_LEFT$ALL;
	
	private static FileConfiguration config;
	private static File file;
	static{
		//TODO: Poprawa ladowania, "NIE W LOCIE".
		file = new File(Main.getInstance().getDataFolder(), "langs.yml");
		config = YamlConfiguration.loadConfiguration(file);
		MESSAGE_PREFIX = config.getString("message-prefix");
		
	}
	public static String parseMessage(String s, Arena a){
		//TODO: Zrobic to.
		return s;
	}

}
