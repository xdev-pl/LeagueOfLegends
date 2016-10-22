package pl.luxdev.lol.basic.manager;

import java.util.ArrayList;

import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.basic.game.Arena;
import pl.luxdev.lol.type.GameState;

public class ArenaManager {
	
	private static ArrayList<Arena> arenas = new ArrayList<>();
	
	public static void addArena(Arena a) {
		arenas.add(a);
	}

	public static void removeArena(Arena a) {
		arenas.remove(a);
	}

	public static void clearArenas() {
		arenas.clear();
	}
	
	public static Arena getFreeArena(){
		for(Arena a : arenas){
			if(a.getState() == GameState.WAITING){
				return a;
			}
		}
		return null;
	}

	public static Arena[] getAllArenas() {
		return arenas.toArray(new Arena[arenas.size()]);
	}

	public static ArrayList<Arena> getArenas(){
		return arenas;
	}

	public static Arena getArenaByName(String s) {
		for (Arena a : arenas) {
			if (a.getName().equalsIgnoreCase(s)) {
				return a;
			}
		}
		return null;	
	}
	public static Arena getArenaByUser(User u) {
		for (Arena a : arenas) {
			if (a.containsPlayer(u.getPlayer())) {
				return a;
			}
		}
		return null;
	}

}
