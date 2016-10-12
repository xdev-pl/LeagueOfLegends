package pl.luxdev.lol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.luxdev.lol.basic.TabTitle;
import pl.luxdev.lol.basic.Title;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.managers.UserManager;
import pl.luxdev.lol.types.ChampType;
import pl.luxdev.lol.utils.ItemCrafter;

public class PlayerJoinList implements Listener {
	
	public static ItemStack BlueTeamPicker = ItemCrafter.createItem(Material.INK_SACK, 1, (short) 12, ("§8[§6Wybierz Team§8]"), ("§7Druzyna: §bNiebiescy"));
	public static ItemStack RedTeamPicker = ItemCrafter.createItem(Material.INK_SACK, 1, (short) 1, ("§8[§6Wybierz Team§8]"), ("§7Druzna: §cCzerowni"));
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		User u = new User(p);
		UserManager.addUser(u);
		u.setChampion(ChampType.NONE);
		if(u.getChampion() == ChampType.NONE) p.sendMessage("Witaj " + p.getName() + "! Nie wybrales/as klasy.");
		else p.sendMessage("Witaj " + p.getName() + " Twoja klasa zostala ustawiona na: " + u.getChampion());
		addItems(p);
		TabTitle tabtitle = new TabTitle("§7» Witaj na serwerze", "§7» §6League Of Legends!");
		//Title title = new Title("§6%player% Witaj na serwerze Lola!", "§aWklad: 80 GBps, 23423434 Godzin, 80 Kaw, Mapa: 0% Plugin: 0,0000001%", 3, 20, 4);
		//title.send(p);
		try{
		tabtitle.send(p);
		}catch(Exception ee){
			ee.printStackTrace();
			Bukkit.broadcastMessage("NIE MA KURAWAWAWA");
		}
		
	}
	private void addItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		inv.setItem(0, BlueTeamPicker);
		inv.setItem(8, RedTeamPicker);
	}

}
