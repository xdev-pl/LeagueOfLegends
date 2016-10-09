package pl.luxdev.lol.managers;

import java.util.ArrayList;

import pl.luxdev.lol.basic.Arena;
import pl.luxdev.lol.basic.User;

public class ArenaManager {
	
	private static ArrayList<Arena> arenas = new ArrayList<Arena>();
	
	public static void addArena(Arena a){
		arenas.add(a);
	}
	public static void removeArena(Arena a){
		arenas.remove(a);
	}
	public static void clearArenas(){
		arenas.clear();
	}
	public static Arena[] getAllArenas(){
		return arenas.toArray(new Arena[arenas.size()]);
	}
	public static ArrayList<Arena> getArenas(){
		return arenas;
	}
	public static Arena getArenaByName(String s){
		for(Arena a : arenas){
			if(a.getName().equalsIgnoreCase(s)) return a;
		}
		return null;	
	}
	public static Arena getArenaByUser(User u){
		for(Arena a : arenas){
			if(a.getUsers().contains(u)) return a;
		}
		return null;
	}

}
