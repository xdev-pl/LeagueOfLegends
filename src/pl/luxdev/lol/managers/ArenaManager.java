package pl.luxdev.lol.managers;

import java.util.concurrent.CopyOnWriteArrayList;

import pl.luxdev.lol.basic.Arena;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.types.TeamType;

public class ArenaManager {
	
	private static volatile CopyOnWriteArrayList<Arena> arenas = new CopyOnWriteArrayList<Arena>();
	
	public static void addArena(Arena paramArena){
		arenas.add(paramArena);
	}
	public static void removeArena(Arena paramArena){
		arenas.remove(paramArena);
	}
	public static Arena[] getAllArenas(){
		return arenas.toArray(new Arena[arenas.size()]);
	}
	public static Arena getArenaByName(String paramString){
		for(Arena paramArena : arenas){
			if(paramArena.getName().equalsIgnoreCase(paramString)) return paramArena;
		}
		return null;	
	}
	public static Arena getArenaByUser(User u){
		for(Arena paramArena : arenas){
			if(paramArena.getUsers().contains(u)) return paramArena;
		}
		return null;
	}

}
