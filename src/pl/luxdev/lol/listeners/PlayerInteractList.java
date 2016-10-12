package pl.luxdev.lol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.luxdev.lol.basic.game.Turret;
import pl.luxdev.lol.events.PlayerAttackTurretEvent;
import pl.luxdev.lol.managers.TurretManager;

@SuppressWarnings("deprecation")
public class PlayerInteractList implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		String turretName = TurretManager.getNearestTurretName(e.getPlayer());
		Turret turret = TurretManager.getTurretByName(turretName);
		if(turret == null) {
			return;
		}
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() != Material.GRASS) {
				Bukkit.getPluginManager().callEvent(new PlayerAttackTurretEvent(turret, e.getPlayer(), e.getClickedBlock()));
			}
		}
	}
}