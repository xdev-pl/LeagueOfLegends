/*
 * 
 * Projekt pisany przez:
 * AdversTM, luxDev, ProgrammingWizard (_an0)
 * Aktualnie piszemy wszystko od podstaw, a an0 sie oper*ala (jeszcze C:)
 * Zobaczymy co z tego wyjdzie, liczymy na cos ciekawego.
 * Wszystko co tu jest, moze ulec zmianie w kazdej chwili, lux zdaje sobie sprawe z optymalnosci kodu (RIP).
 * 
 * 
 */
package pl.luxdev.lol;

import org.bukkit.plugin.java.JavaPlugin;

import pl.luxdev.lol.listeners.EntityExplodeList;
import pl.luxdev.lol.listeners.PlayerAttackTurretList;
import pl.luxdev.lol.listeners.PlayerInteractList;
import pl.luxdev.lol.listeners.PlayerInvClickList;
import pl.luxdev.lol.listeners.PlayerJoinList;
import pl.luxdev.lol.managers.ConfigManager;
import pl.luxdev.lol.managers.DataManager;
import pl.luxdev.lol.tasks.MainGameLoop;
import pl.luxdev.lol.utils.Utils;

public class Main extends JavaPlugin {
	
	private static Main instance;

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable(){
		Utils.getLogger();
		ConfigManager.load();
		DataManager.load();
		this.getServer().getPluginManager().registerEvents(new PlayerInteractList(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinList(), this);
		this.getServer().getPluginManager().registerEvents(new EntityExplodeList(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInvClickList(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerAttackTurretList(), this);
		MainGameLoop.start();
	}

	@Override
	public void onDisable(){
		// TODO: save
	}
	
	public static Main getInstance(){
		return instance;
	}
	
}
