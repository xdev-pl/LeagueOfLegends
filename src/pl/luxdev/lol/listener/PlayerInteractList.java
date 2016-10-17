package pl.luxdev.lol.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import pl.luxdev.lol.basic.game.Turret;
import pl.luxdev.lol.draw.DrawSystem;
import pl.luxdev.lol.event.PlayerAttackTurretEvent;
import pl.luxdev.lol.manager.TurretManager;
import pl.luxdev.lol.manager.UserManager;

public class PlayerInteractList implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		PlayerInventory i = p.getInventory();
		if(i.getItemInMainHand().equals(PlayerJoinList.JOIN_ITEM) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
			DrawSystem.addUser(UserManager.getUser(p));
			i.clear();
			// TODO ?
			return;
		}
		
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