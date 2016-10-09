package pl.luxdev.lol.listeners;

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

public class PlayerJoinList implements Listener{
	
	public static ItemStack BlueTeamPicker = ItemCrafter.createItem(Material.INK_SACK, 1, (short) 12, ("§8[§6Wybierz TeamType§8]"), ("§7Team: §bBLUE"));
	public static ItemStack RedTeamPicker = ItemCrafter.createItem(Material.INK_SACK, 1, (short) 1, ("§8[§6Wybierz TeamType§8]"), ("§7Team: §cRED"));
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		User u = new User(p);
		UserManager.addUser(u);
		u.setChampion(ChampType.NONE);
		if(u.getChampion() == ChampType.NONE) p.sendMessage("Witaj " + p.getName() + "! Nie wybrales/as klasy.");
		else p.sendMessage("Witaj " + p.getName() + " Twoja klasa zostala ustawiona na: " + u.getChampion());
		addItems(p);
		
//		TitleAPI.sendTabTitle(p, "§7» Witaj na serwerze", "§6League Of Legends !");
//		TitleAPI.sendFullTitle(p, 20*3, 20*20, 20*4, "§6%player% Witaj na serwerze Lola!", "§aWklad Prac: 35 KB, 28 Godzin, 3 Kawy, 3k lini kodu, Mapa: 3% Plugin: 15%");
		TabTitle tabtitle = new TabTitle("§7» Witaj na serwerze", "§6League Of Legends!");
		tabtitle.send(p);
		Title title = new Title("§6%player% Witaj na serwerze Lola!", "§aWklad Prac: 35 GB, 28 Godzin, 3 Kawy, 3k lini kodu, Mapa: 3% Plugin: 15%", 3, 20, 4);
		title.send(p);
		
	}
	private void addItems(Player p){
		Inventory inv = p.getInventory();
		inv.clear();
		inv.setItem(0, BlueTeamPicker);
		inv.setItem(8, RedTeamPicker);
		
	}

}
