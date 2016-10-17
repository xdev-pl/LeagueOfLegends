package pl.luxdev.lol.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.manager.UserManager;

public class PlayerInvClickList implements Listener {
	
	@EventHandler
	public void onclick(InventoryClickEvent e){
		if(e.getCurrentItem() == null) return;
		Player p = (Player) e.getWhoClicked();
		User u = UserManager.getUser(p.getName());
	}
}
