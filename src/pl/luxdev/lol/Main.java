package pl.luxdev.lol;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.luxdev.lol.command.CommandImpl;
import pl.luxdev.lol.command.TestCommand;
import pl.luxdev.lol.data.ConfigManager;
import pl.luxdev.lol.data.DataManager;
import pl.luxdev.lol.listener.AsyncPlayerChatList;
import pl.luxdev.lol.listener.EntityExplodeList;
import pl.luxdev.lol.listener.PlayerAchievementAwardedList;
import pl.luxdev.lol.listener.PlayerInteractList;
import pl.luxdev.lol.listener.PlayerInvClickList;
import pl.luxdev.lol.listener.PlayerJoinList;
import pl.luxdev.lol.system.DrawSystem;
import pl.luxdev.lol.system.task.MainGameTask;
import pl.luxdev.lol.util.Utils;
import pl.luxdev.lol.util.element.ScoreBoard;
import pl.luxdev.lol.util.reflect.PacketUtils;
import pl.luxdev.lol.util.reflect.Reflection;

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
		this.getServer().getPluginManager().registerEvents(new AsyncPlayerChatList(), this);
		MainGameTask.startTask();
		ScoreBoard.runTasks();
		DrawSystem.runTask();
		regCommands();
		
	}
	public void spawnTheShit(Location loc, Player all){
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
	}
	private void regCommands(){
		CommandImpl.registerCommands(new TestCommand());
	}
	
	public static Main getInstance(){
		return instance;
	}
}
