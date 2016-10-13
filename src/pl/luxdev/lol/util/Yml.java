package pl.luxdev.lol.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Yml {

	/*
	 * TODO: change this shit
	 */

	private final File file;
	private final YamlConfiguration cfg;
	
	public Yml(File f) {
		this(f, true);
	}
	
	public Yml(File f, boolean createIfNotExists) {
		file = f;
		if (createIfNotExists && !f.exists()) {
			createIfNotExists();
		}
		cfg = YamlConfiguration.loadConfiguration(f);
	}
	
	public YamlConfiguration getCfg() {
		return cfg;
	}
	
	public void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void createIfNotExists() {
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

