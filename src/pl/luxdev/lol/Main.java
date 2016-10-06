package pl.luxdev.lol;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R1.EntityInsentient;
import pl.luxdev.lol.basic.Team;
import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.customentities.PathFinderGoalWalkTo;
import pl.luxdev.lol.listeners.EntityExplodeList;
import pl.luxdev.lol.listeners.PlayerInteractList;
import pl.luxdev.lol.listeners.PlayerInvClickList;
import pl.luxdev.lol.listeners.PlayerJoinList;
import pl.luxdev.lol.managers.DataManager;
import pl.luxdev.lol.managers.TeamManager;
import pl.luxdev.lol.managers.TurretManager;
import pl.luxdev.lol.managers.UserManager;
import pl.luxdev.lol.tasks.MainGameLoop;
import pl.luxdev.lol.utils.Utils;

public class Main extends JavaPlugin implements Listener {
	
	private static Main inst;
	
	public void onEnable(){
		inst = this;
		Utils.getLogger();
		DataManager.load();
		//saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(new PlayerInteractList(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinList(), this);
		this.getServer().getPluginManager().registerEvents(new EntityExplodeList(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInvClickList(), this);
		MainGameLoop.startCheckerTask();
		
	}
	public void onDisable(){
		inst = null;
	}
	
	private static void SpawnMiniontest(World world, Location spawn, Location walkTo){
		Squid squid = world.spawn(spawn, Squid.class);
		PathFinderGoalWalkTo walk = new PathFinderGoalWalkTo((EntityInsentient) squid, walkTo, 2);
		walk.c();
		Bukkit.broadcastMessage("Zrespiono moba, idzie sb tam gdzies xD");
		//To nie ma prawa dzialac ale ok.. xD
	}
	
	public static Main getInst(){
		return inst;
	}
	
}
