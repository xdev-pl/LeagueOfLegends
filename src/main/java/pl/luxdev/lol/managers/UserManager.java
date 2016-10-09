package pl.luxdev.lol.managers;

import java.util.ArrayList;
import java.util.List;

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

}
