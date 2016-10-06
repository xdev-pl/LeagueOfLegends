package pl.luxdev.lol.tasks;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.managers.TurretManager;
import pl.luxdev.lol.managers.UserManager;

public class MainGameLoop {
	
	public static void startCheckerTask(){
		new BukkitRunnable(){
			public void run(){
				for(Turret turret : TurretManager.getAllTurrets()){
					for(Entity en : turret.getLocation().getWorld().getEntities()){
						if(en instanceof Player){
							Player p = (Player) en;
							User u = UserManager.getUser(p.getName());
							if(p.getLocation().distance(turret.getLocation()) <= 10 && p.getGameMode() == GameMode.SURVIVAL){
								if(turret.isDestroyed()){
									return;
								}
								if(turret.getTeam() == u.getTeam()){
									return;
								}
								p.setHealth(p.getHealth() -turret.getAttackStrength());
								p.playSound(p.getLocation(), Sound.HURT_FLESH, 2F, 3F);
							}
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInst(), 20, 15);
	}
}
