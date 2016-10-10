package pl.luxdev.lol.managers;

import java.util.ArrayList;
import java.util.List;

import pl.luxdev.lol.basic.Champion;

public class ChampionManager {
	
	private static List<Champion> champions = new ArrayList<Champion>();
	
	public static void addChampion(Champion c) {
		champions.add(c);
	}

	public static void removeChampion(Champion c) {
		champions.remove(c);
	}

	public static List<Champion> getChampions() {
		return champions;
	}

	public static void clearChampions() {
		champions.clear();
	}

}
