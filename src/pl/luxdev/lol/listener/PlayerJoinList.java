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
		//addItems(p);
		TabTitle tab = new TabTitle("§dWitaj na serwerze", "§5League Of Legends!");
		Title title = new Title("§dWitamy", "§5XD Prodakszyn", 10, 10, 30);
		bar.addPlayer(p);
		tab.send(p);
		title.send(p);
		createScoreboard(p);
	}
	
	private void addItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		inv.setItem(0, BlueTeamPicker);
		inv.setItem(8, RedTeamPicker);
	}
	
	private void createScoreboard(Player p) {
		String[] content = { "§aTEST: 1337", "§22", "§33", "§44", "§55", "§66", "§77", "§88", "§99", "§010", "§a11", "§b12", "§c13", "§d14", "§e15                            "};
		String[] title = { "§eLeague of Legends", "§6League of Legends", "§eLeague of Legends" };
		ScoreBoard sb = new ScoreBoard(p, content, title);
		runTask(sb);
	}
	
	// TEST
	private void runTask(ScoreBoard sb) {
		ArrayList<String[]> content = new ArrayList<String[]>();
		content.add(new String[]{"§1TEST: 1337", "§22", "§33", "§44", "§55", "§66", "§77", "§88", "§99", "§010", "§a11", "§b12", "§c13", "§d14", "§e15                            "});
		content.add(new String[]{"§2TEST: 1337", "§32", "§43", "§54", "§65", "§76", "§87", "§98", "§09", "§a10", "§b11", "§c12", "§d13", "§e14", "§f15                            "});
		content.add(new String[]{"§3TEST: 1337", "§42", "§53", "§64", "§75", "§86", "§97", "§08", "§a9", "§b10", "§c11", "§d12", "§e13", "§f14", "§a15                            "});
		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable(){
			int i = 0;
			@Override
			public void run() {
				if(i > content.size() - 1) i = 0;
				sb.setContent(content.get(i++));
			}
		}, 0, 20);
	}
}