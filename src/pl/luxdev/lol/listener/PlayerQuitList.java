package pl.luxdev.lol.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.luxdev.lol.basic.ScoreBoard;

public class PlayerQuitList implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		ScoreBoard.get(p).remove();
	}
}
