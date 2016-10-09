package pl.luxdev.lol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.connorlinfoot.titleapi.TitleAPI;

import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.events.PlayerAttackTurretEvent;
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
		if(e.getAction() == Action.LEFT_CLICK_BLOCK){
			if (e.getClickedBlock().getType() != Material.GRASS) {
				Bukkit.getPluginManager().callEvent(new PlayerAttackTurretEvent(turret, e.getPlayer(), e.getClickedBlock()));
			}
		}
	}
}