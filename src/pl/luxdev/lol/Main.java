/*
 * 
 * Projekt pisany przez:
 * AdversTM, luxDev, ProgrammingWizard (_an0)
 * Aktualnie piszemy wszystko od podstaw, a an0 sie oper*ala
 * Zobaczymy co z tego wyjdzie, liczymy na cos ciekawego.
 * Wszystko co tu jest, moze ulec zmianie w kazdej chwili, lux zdaje sobie sprawe z optymalnosci kodu (RIP).
 * 
 * 
 */
package pl.luxdev.lol;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Squid;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R1.EntityInsentient;
import pl.luxdev.lol.entities.PathFinderGoalWalkTo;
import pl.luxdev.lol.listeners.EntityExplodeList;
import pl.luxdev.lol.listeners.PlayerAttackTurretList;
import pl.luxdev.lol.listeners.PlayerInteractList;
import pl.luxdev.lol.listeners.PlayerInvClickList;
import pl.luxdev.lol.listeners.PlayerJoinList;
import pl.luxdev.lol.managers.ConfigManager;
import pl.luxdev.lol.managers.DataManager;
import pl.luxdev.lol.tasks.MainGameLoop;
import pl.luxdev.lol.utils.Utils;

public class Main extends JavaPlugin implements Listener {
	
	private static Main inst;
	
	public void onEnable(){
		inst = this;
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
	public void onDisable(){
		inst = null;
	}
	
	/*
	 * TODO
	 * Napewno to usunac, i zaczac pisac minionki!
	 * Nie wiem kto to pisal, ale napewno musi isc do izby wytrzeźwień.
	 */
	
	private static void SpawnMiniontest(World world, Location spawn, Location walkTo){
		Squid squid = world.spawn(spawn, Squid.class);
		
		Bukkit.broadcastMessage("Zrespiono moba, idzie sb tam gdzies xD");
	}
	
	public static Main getInst(){
		return inst;
	}
	
}
