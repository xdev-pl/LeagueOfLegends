package pl.luxdev.lol.draw;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.basic.game.Arena;
import pl.luxdev.lol.basic.game.Team;
import pl.luxdev.lol.manager.ArenaManager;
import pl.luxdev.lol.type.GameState;
import pl.luxdev.lol.type.TeamType;

public class DrawSystem {

	private static BukkitTask task = null;
	private static ArrayList<User> ready = new ArrayList<User>();

	public static void addUser(User u) {
		if(!ready.contains(u)) ready.add(u);
	}

	public static void removeUser(User u) {
		if(ready.contains(u)) ready.remove(u);
	}
	
	public static void removeUsers(ArrayList<User> us){
		for(User u : us) removeUser(u);
	}

	public static ArrayList<User> getUsers() {
		return ready;
	}

	public static void shuffle() {
		Arena a = ArenaManager.getFreeArena();
		if(a == null) return;
		
		a.setState(GameState.INGAME);
		ArrayList<User> blue = new ArrayList<User>();
		ArrayList<User> red = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			if(i % 2 == 0) blue.add(ready.get(i));
			else red.add(ready.get(i));
		}
		for (Team t : a.getTeams()) {
			if (t.getType() == TeamType.BLUE) t.addUsers(blue);
			if (t.getType() == TeamType.RED) t.addUsers(red);
			removeUsers(t.getUsers());
		}
	}
	public static void swapTeam(User u) {
		// TODO: DALEKA PRZYSZLOSC MY FRIENDS
	}

	public static void runTask() {
		if(task == null)
			task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new DrawTask(), 0, 40);
	}
}
