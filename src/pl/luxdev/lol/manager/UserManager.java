package pl.luxdev.lol.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import pl.luxdev.lol.basic.User;

public class UserManager {
	
	private static List<User> users = new ArrayList<>();
	
	public static void addUser(User u){
		users.add(u);
	}

	public static void removeUser(User u){
		users.remove(u);
	}

	public static User getUser(String name) {
		for (User u : users) {
			if (u.getPlayer().getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	public static User getUser(Player player) {
		for (User u : users) {
			if (u.getPlayer().equals(player)) {
				return u;
			}
		}
		return null;
	}

}
