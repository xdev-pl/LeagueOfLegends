package pl.luxdev.lol.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.luxdev.lol.basic.ActionBar;
import pl.luxdev.lol.basic.TabTitle;
import pl.luxdev.lol.basic.Title_new;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.manager.UserManager;
import pl.luxdev.lol.type.ChampType;
import pl.luxdev.lol.type.TeamType;
import pl.luxdev.lol.util.ItemCrafter;

public class PlayerJoinList implements Listener {
	
	public static ItemStack BlueTeamPicker = ItemCrafter.createItem(Material.WOOL, 1, (short) 3, ("§8[§6Wybierz Team§8]"), ("§7Druzyna: §bNiebiescy"));
	public static ItemStack RedTeamPicker = ItemCrafter.createItem(Material.WOOL, 1, (short) 14, ("§8[§6Wybierz Team§8]"), ("§7Druzna: §cCzerowni"));
	private static ActionBar bar = new ActionBar("§dMorda w ziemie i kielkujesz.");
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20); //TODO
		User u = new User(p);
		UserManager.addUser(u);
		u.setChampion(ChampType.YASUO);
		u.setTeam(TeamType.BLUE);
		p.sendMessage("Witaj " + p.getName() + " Twoje ustawienia narzucone przez serwer: ");
		p.sendMessage("Postac: " + u.getChampion());
		p.sendMessage("Druzyna " + u.getTeam());
		addItems(p);
		TabTitle tab = new TabTitle("§dWitaj na serwerze", "§5League Of Legends!");
		Title_new title = new Title_new("§dWitamy", "§5XD Prodakszyn", 10, 10, 30);
		bar.addPlayer(p);
		tab.send(p);
		title.send(p);
		//Title title = new Title("§6%player% Witaj na serwerze Lola!", "§aWklad: 80 GBps, 23423434 Godzin, 802232 Kaw, Mapa: 000000,000% Plugin: 0,0000001%", 3, 20, 4);
		
	}
	private void addItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		inv.setItem(0, BlueTeamPicker);
		inv.setItem(8, RedTeamPicker);
	}
}