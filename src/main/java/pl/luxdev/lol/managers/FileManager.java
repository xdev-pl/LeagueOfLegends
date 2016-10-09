package pl.luxdev.lol.managers;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.utils.Utils;


public class FileManager {

	private static final File mainDir = Main.getInst().getDataFolder();
	private static final File cfgFile = new File(mainDir, "config.yml");
	private static YamlConfiguration ymlconfig;
	
	public static void checkFiles(){
		if(!mainDir.exists()){
			mainDir.mkdir();
			Utils.info("Main directory successfully created");
		}
	    if (!cfgFile.exists()) {
	    	Utils.copy(Main.getInst().getResource("config.yml"), cfgFile);
	    	Utils.info("Successfully created configuration file 'config.yml'");
	    }
	    ymlconfig = YamlConfiguration.loadConfiguration(cfgFile);
	}
	
	public static YamlConfiguration getCfg(){
		return ymlconfig;
	}
	
}
