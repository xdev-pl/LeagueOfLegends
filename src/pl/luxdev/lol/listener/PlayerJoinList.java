package pl.luxdev.lol.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
import pl.luxdev.lol.util.Utils;

public class PlayerJoinList implements Listener {

	public static final ItemStack JOIN_ITEM = ItemCrafter.createItem(Material.NETHER_STAR, 1, (short) 0, Utils.fixColors("&8&l[ &a&lDolacz &8&l]"), Utils.fixColors("&7Kliknij, aby dolaczyc do kolejki."));
	private final ActionBar bar = new ActionBar("§c§lMorda w ziemie i kielkujesz.");
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20); //TODO
		PlayerInventory i = p.getInventory();
		i.clear();
		i.setHeldItemSlot(4);
		i.setItem(4, JOIN_ITEM);
		
		User u = new User(p);
		UserManager.addUser(u);
		u.setChampion(ChampType.YASUO);
		u.setTeam(TeamType.NONE);
		TabTitle tab = new TabTitle("§2Witaj na serwerze \n Grzybie", "§5League Of Legends!");
		Title title = new Title("§6§lWitamy", "§a§lXD Prodakshön", 10, 10, 30);
		bar.addPlayer(p);
		tab.send(p);
		title.send(p);
		runTask(ScoreBoard.get(p)); //test
		ProfileLoader profile = new ProfileLoader(p.getUniqueId().toString(), p.getName(), "skkf");
		profile.loadProfile();
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