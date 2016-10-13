package pl.luxdev.lol.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import pl.luxdev.lol.basic.game.Turret;

public class TurretManager {

	private static List<Turret> turrets = new CopyOnWriteArrayList<>();

	public static List<Turret> getAllTurrets() {
		return turrets;
	}
	
	public static void clearTurrets() {
		turrets.clear();
	}

	public static void getPlayersOnTurretArea(Turret turret) {
		
	}

	public static void addTurret(Turret turret) {
		turrets.add(turret);
	}

	public static void removeTurret(Turret turret) {
		turrets.remove(turret);
	}

	public static String getNearestTurretName(Player en) {
		Location enLoc = en.getLocation();
		for (Turret turret : turrets) {
			if (turret.getLocation().distance(enLoc) <= 10) {
				return turret.getName();
			}
		}
		return null;
	}

	public static Turret getTurretByName(String s) {
		for (Turret turret : turrets) {
			if (turret.getName().equals(s)) {
				return turret;
			}
		}
		return null;
	}

	private static List<Location> getArenaBlocks(Location l, int radius) {
		World w = l.getWorld();
		int xCoord = (int) l.getX();
		int zCoord = (int) l.getZ();
		int YCoord = (int) l.getY();
		List<Location> tempList = new ArrayList<>();
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = -radius; y <= radius; y++) {
					tempList.add(new Location(w, xCoord + x, YCoord + y, zCoord + z));
				}

			}
		}
		return tempList;
	}
}
