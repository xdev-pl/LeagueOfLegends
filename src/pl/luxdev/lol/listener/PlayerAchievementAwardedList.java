package pl.luxdev.lol.listener;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.inventory.Inventory;

public class PlayerAchievementAwardedList implements Listener {
	
	@EventHandler
	public void inventoryOpen(PlayerAchievementAwardedEvent event) {
		event.setCancelled(true);
		if (event.getAchievement() == Achievement.OPEN_INVENTORY) {
			if (!(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
				Inventory inv = Bukkit.createInventory(null, 54, "NIGDY NIE DOSTANIESZ SW. EQ!");
				inv.setContents(event.getPlayer().getInventory().getContents());
				event.getPlayer().openInventory(inv);
			}
		}
	}
}