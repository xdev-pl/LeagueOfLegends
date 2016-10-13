package pl.luxdev.lol.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsList implements Listener {
	
	@EventHandler
	public void shieldProtection(PlayerSwapHandItemsEvent event){
		event.setCancelled(true);
	}
}
