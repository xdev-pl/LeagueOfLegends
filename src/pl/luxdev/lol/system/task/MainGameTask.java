package pl.luxdev.lol.system.task;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.basic.game.Turret;
import pl.luxdev.lol.basic.manager.TurretManager;
import pl.luxdev.lol.basic.manager.UserManager;

public class MainGameTask {
	
	public static void startTask(){
		new BukkitRunnable(){
			public void run(){
				for (Turret turret : TurretManager.getAllTurrets()){
					for (Entity en : turret.getLocation().getWorld().getEntities()){
						if (en instanceof Player){
							Player p = (Player) en;
							User u = UserManager.getUser(p.getName());
							if (p.getLocation().distance(turret.getLocation()) <= 10 && p.getGameMode() == GameMode.SURVIVAL) {
								if (turret.isDestroyed()){
									return;
								}
								if (turret.getTeam() == u.getTeam()) {
									return;
								}
								p.setHealth(p.getHealth() -turret.getAttackStrength());
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 2F, 3F);
							}
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 20*20, 15L);
	}
}
