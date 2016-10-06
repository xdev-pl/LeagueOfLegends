package pl.luxdev.lol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pl.luxdev.lol.basic.Team;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.managers.TeamManager;
import pl.luxdev.lol.managers.UserManager;
import pl.luxdev.lol.types.TeamType;

public class PlayerInvClickList implements Listener{
	
	@EventHandler
	public void onclick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		User u = UserManager.getUser(p.getName());
		if(e.getCurrentItem().equals(PlayerJoinList.BlueTeamPicker)){
			
		}
	}
}
