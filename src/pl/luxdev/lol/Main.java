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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.luxdev.lol.command.CommandImpl;
import pl.luxdev.lol.command.TestCommand;
import pl.luxdev.lol.listener.EntityExplodeList;
import pl.luxdev.lol.listener.PlayerAchievementAwardedList;
import pl.luxdev.lol.listener.PlayerInteractList;
import pl.luxdev.lol.listener.PlayerInvClickList;
import pl.luxdev.lol.listener.PlayerJoinList;
import pl.luxdev.lol.manager.ConfigManager;
import pl.luxdev.lol.manager.DataManager;
import pl.luxdev.lol.task.MainGameTask;
import pl.luxdev.lol.util.PacketUtils;
import pl.luxdev.lol.util.Reflection;
import pl.luxdev.lol.util.Utils;

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
		this.getServer().getPluginManager().registerEvents(new PlayerAchievementAwardedList(), this);
		MainGameTask.startTask();	
		for(Player p : Bukkit.getOnlinePlayers()){
			Location loc = p.getLocation();
			spawnTheShit(loc, p);
		}
	}
	private void spawnTheShit(Location loc, Player all){
		Class<?> EntityLiving = Reflection.getCraftClass("EntityLiving");
		Class<?> EntityZombie = Reflection.getCraftClass("EntityZombie");
		Class<?> packetLivingClass = Reflection.getCraftClass("PacketPlayOutSpawnEntityLiving");
		try{
			Object zombie = EntityZombie.getConstructor(Reflection.getCraftClass("World")).newInstance(loc.getWorld());
			Reflection.getMethod(EntityZombie, "setLocation", double.class, double.class, double.class, float.class,
					float.class).invoke(zombie, loc.getX(), loc.getY() + 1, loc.getZ(), 0, 0);
			Reflection.getMethod(EntityZombie, "setCustomName", String.class).invoke(zombie, "§6Zombie!!!!!!");
			Reflection.getMethod(EntityZombie, "setCustomNameVisible", boolean.class).invoke(zombie, true);
			Object packedt = packetLivingClass.getConstructor(new Class<?>[] { EntityLiving }).newInstance(zombie);
			PacketUtils.sendPacket(all, packedt);
			Bukkit.broadcastMessage("ŻOMBIE SPAWNED KIERWA");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public void onDisable(){
		// TODO: save
		// TODO: Przestan robic "TODO" tylko cos zrob! iksde
	}
	private void regCommands(){
		CommandImpl.registerCommands(new TestCommand());
	}
	
	public static Main getInstance(){
		return instance;
	}
}
