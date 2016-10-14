package pl.luxdev.lol.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.ActionBar;
import pl.luxdev.lol.basic.ScoreBoard;
import pl.luxdev.lol.basic.TabTitle;
import pl.luxdev.lol.basic.Title;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.manager.UserManager;
import pl.luxdev.lol.type.ChampType;
import pl.luxdev.lol.type.ScoreBoardType;
import pl.luxdev.lol.type.TeamType;
import pl.luxdev.lol.util.ItemCrafter;
import pl.luxdev.lol.util.ProfileLoader;

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
		//addItems(p);
		TabTitle tab = new TabTitle("§dWitaj na serwerze", "§5League Of Legends!");
		Title title = new Title("§dWitamy", "§5XD Prodakszyn", 10, 10, 30);
		bar.addPlayer(p);
		tab.send(p);
		title.send(p);
		runTask(ScoreBoard.get(p));
		ProfileLoader profile = new ProfileLoader(p.getUniqueId().toString(), p.getName(), "skkf");
		profile.loadProfile();
	}
	
	private void addItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		inv.setItem(0, BlueTeamPicker);
		inv.setItem(8, RedTeamPicker);
	}
	
	//TEST
	private void runTask(ScoreBoard sb) {
		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable(){
			boolean b = true;
			@Override
			public void run() {
				if(b) sb.setType(ScoreBoardType.GAME);
				else sb.setType(ScoreBoardType.LOBBY);
				b = !b;
			}
			
		}, 0, 40);
	}
}