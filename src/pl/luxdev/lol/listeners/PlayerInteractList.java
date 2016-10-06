package pl.luxdev.lol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.titleapi.TitleAPI;

import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.managers.TurretManager;
import pl.luxdev.lol.managers.UserManager;
import pl.luxdev.lol.types.ChampType;
import pl.luxdev.lol.utils.Utils;

public class PlayerInteractList implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		String turretName = TurretManager.getNearestTurretName(e.getPlayer());
		Turret turret = TurretManager.getTurretByName(turretName);
		if(turret == null){
			return;
		}
		User u = UserManager.getUser(e.getPlayer().getName());
		if(turret.getTeam() == u.getTeam()){
			e.getPlayer().sendMessage("§cNie mozesz zniszczyc swojej wiezy.");
			return;
		}
		Location loc = turret.getLocation();
		World world = loc.getWorld();
		if(e.getAction() == Action.LEFT_CLICK_BLOCK){
			if (e.getClickedBlock().getType() != Material.GRASS) {
				if (turret.isDestroyed()) {
					e.getPlayer().sendMessage("§cTa wieza jest juz zniszczona.");
					return;
				}
				if(turret.getHp() < 10){
					turret.setDestroyed(true);
					TNTPrimed tnt = (TNTPrimed) world.spawnEntity(loc.add(0, 2,0), EntityType.PRIMED_TNT);
					tnt.setCustomName("Zaraz wybuchne");
					for(Player p : Bukkit.getOnlinePlayers()){
						TitleAPI.sendFullTitle(p, 20, 20*5, 20*3, "§6Wieza wroga zniszona! §7(§6Linia: " + Utils.getTurretLine(turret) + "§7)", "§a+400 Gold");
					}
				}
				if(turret.getHp() >= 10){
					turret.setHp(turret.getHp() - 10);
					e.getPlayer().sendMessage("§7Turret's Health: §6" + turret.getHp());
				}
			}
		}
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		String[] wiadomosc = e.getMessage().split(" ");
		for(String mess : wiadomosc){
			for(ChampType type : ChampType.values()){
				if(mess.equals(type.toString())){
					User u = UserManager.getUser(e.getPlayer().getName());
					u.setChampion(type);
					e.getPlayer().sendMessage("Twoja klasa zostala ustawiona na: " + u.getChampion());
				}
			}
		}
	}
}