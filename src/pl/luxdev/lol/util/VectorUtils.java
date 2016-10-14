package pl.luxdev.lol.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VectorUtils {
	
	public static TreeSet<String> sortKey(Set<String> toSort) {
		TreeSet<String> sortedSet = new TreeSet<String>();
		for (String cur : toSort)
			sortedSet.add(cur);

		return sortedSet;
	}

	public static Location getMidpoint(Location a, Location b) {
		return a.add(b.subtract(a).multiply(0.5));
	}

	public static Vector getTrajectory(Entity from, Entity to) {
		return getTrajectory(from.getLocation().toVector(), to.getLocation().toVector());
	}

	public static Vector getTrajectory(Location from, Location to) {
		return getTrajectory(from.toVector(), to.toVector());
	}

	public static Vector getTrajectory(Vector from, Vector to) {
		return to.subtract(from).normalize();
	}

	public static Vector getTrajectory2d(Entity from, Entity to) {
		return getTrajectory2d(from.getLocation().toVector(), to.getLocation().toVector());
	}

	public static Vector getTrajectory2d(Location from, Location to) {
		return getTrajectory2d(from.toVector(), to.toVector());
	}

	public static Vector getTrajectory2d(Vector from, Vector to) {
		return to.subtract(from).setY(0).normalize();
	}

	public static boolean hasSight(Location from, Player to) {
		return hasSight(from, to.getLocation()) || hasSight(from, to.getEyeLocation());
	}

	public static boolean hasSight(Location from, Location to) {
		Location cur = new Location(from.getWorld(), from.getX(), from.getY(), from.getZ());

		double rate = 0.1;
		Vector vec = getTrajectory(from, to).multiply(0.1);

		while (MathUtils.offset(cur, to) > rate) {
			cur.add(vec);

			if (!BlockUtils.airFoliage(cur.getBlock()))
				return false;
		}

		return true;
	}

	public static float getPitch(Vector vec) {
		double x = vec.getX();
		double y = vec.getY();
		double z = vec.getZ();
		double xz = Math.sqrt((x * x) + (z * z));

		double pitch = Math.toDegrees(Math.atan(xz / y));
		if (y <= 0)
			pitch += 90;
		else
			pitch -= 90;
		if (pitch == 180)
			pitch = 0;

		return (float) pitch;
	}

	public static float getYaw(Vector vec) {
		double x = vec.getX();
		double z = vec.getZ();

		double yaw = Math.toDegrees(Math.atan((-x) / z));
		if (z < 0)
			yaw += 180;

		return (float) yaw;
	}

	public static Vector normalize(Vector vec) {
		if (vec.length() > 0)
			vec.normalize();

		return vec;
	}

	public static Vector clone(Vector vec) {
		return new Vector(vec.getX(), vec.getY(), vec.getZ());
	}

	public static <T> T random(Set<T> set) {
		List<T> list = new ArrayList<T>();

		list.addAll(set);

		return random(list);
	}

	public static <T> T random(List<T> list) {
		if (list.isEmpty())
			return null;

		return list.get(MathUtils.r(list.size()));
	}

	public static boolean inBoundingBox(Location loc, Location cornerA, Location cornerB) {
		if (loc.getX() <= Math.min(cornerA.getX(), cornerB.getX()))
			return false;
		if (loc.getX() >= Math.max(cornerA.getX(), cornerB.getX()))
			return false;

		if (cornerA.getY() != cornerB.getY()) {
			if (loc.getY() <= Math.min(cornerA.getY(), cornerB.getY()))
				return false;
			if (loc.getY() >= Math.max(cornerA.getY(), cornerB.getY()))
				return false;
		}

		if (loc.getZ() <= Math.min(cornerA.getZ(), cornerB.getZ()))
			return false;
		if (loc.getZ() >= Math.max(cornerA.getZ(), cornerB.getZ()))
			return false;

		return true;
	}

	public static Vector cross(Vector a, Vector b) {
		double x = a.getY() * b.getZ() - a.getZ() * b.getY();
		double y = a.getZ() * b.getX() - a.getX() * b.getZ();
		double z = a.getX() * b.getY() - a.getY() * b.getX();

		return new Vector(x, y, z).normalize();
	}

	public static Vector getRight(Vector vec) {
		return cross(vec.clone().normalize(), new Vector(0, 1, 0));
	}

	public static Vector getLeft(Vector vec) {
		return getRight(vec).multiply(-1);
	}

	public static Vector getBehind(Vector vec) {
		return vec.clone().multiply(-1);
	}

	public static Vector getUp(Vector vec) {
		return getDown(vec).multiply(-1);
	}

	public static Vector getDown(Vector vec) {
		return cross(vec, getRight(vec));
	}

	public static Location getAverageLocation(ArrayList<Location> locs) {
		if (locs.isEmpty())
			return null;

		Vector vec = new Vector(0, 0, 0);
		double amount = 0;

		for (Location loc : locs) {
			vec.add(loc.toVector());
			amount++;
		}

		vec.multiply(1d / amount);

		return vec.toLocation(locs.get(0).getWorld());
	}

	public static Vector getAverageBump(Location source, ArrayList<Location> locs) {
		if (locs.isEmpty())
			return null;

		Vector vec = new Vector(0, 0, 0);
		double amount = 0;

		for (Location loc : locs) {
			vec.add(VectorUtils.getTrajectory(loc, source));
			amount++;
		}

		vec.multiply(1d / amount);

		return vec;
	}

	public static Location findClosest(Location mid, ArrayList<Location> locs) {
		Location bestLoc = null;
		double bestDist = 0;

		for (Location loc : locs) {
			double dist = MathUtils.offset(mid, loc);

			if (bestLoc == null || dist < bestDist) {
				bestLoc = loc;
				bestDist = dist;
			}
		}

		return bestLoc;
	}

	public static boolean isInPyramid(Vector a, Vector b, double angleLimit) {
		return (Math.abs(getPitch(a) - getPitch(b)) < angleLimit) && (Math.abs(getYaw(a) - getYaw(b)) < angleLimit);
	}

	public static boolean isTargetInPlayerPyramid(Player player, Player target, double angleLimit) {
		return isInPyramid(player.getLocation().getDirection(),
				VectorUtils.getTrajectory(player.getEyeLocation(), target.getEyeLocation()), angleLimit)
				|| isInPyramid(player.getLocation().getDirection(),
						VectorUtils.getTrajectory(player.getEyeLocation(), target.getLocation()), angleLimit);
	}

	public static Location getLocationAwayFromPlayers(ArrayList<Location> locs, ArrayList<Player> players) {
		Location bestLoc = null;
		double bestDist = 0;

		for (Location loc : locs) {
			double closest = -1;

			for (Player player : players) {
				if (!player.getWorld().equals(loc.getWorld()))
					continue;

				double dist = MathUtils.offsetSquared(player.getLocation(), loc);

				if (closest == -1 || dist < closest) {
					closest = dist;
				}
			}

			if (closest == -1)
				continue;

			if (bestLoc == null || closest > bestDist) {
				bestLoc = loc;
				bestDist = closest;
			}
		}

		return bestLoc;
	}

	public static Location getLocationNearPlayers(ArrayList<Location> locs, ArrayList<Player> players,
			ArrayList<Player> dontOverlap) {
		Location bestLoc = null;
		double bestDist = 0;

		for (Location loc : locs) {
			double closest = -1;

			boolean valid = true;

			for (Player player : dontOverlap) {
				if (!player.getWorld().equals(loc.getWorld()))
					continue;

				double dist = MathUtils.offsetSquared(player.getLocation(), loc);

				if (dist < 0.8) {
					valid = false;
					break;
				}
			}

			if (!valid)
				continue;

			for (Player player : players) {
				if (!player.getWorld().equals(loc.getWorld()))
					continue;

				double dist = MathUtils.offsetSquared(player.getLocation(), loc);

				if (closest == -1 || dist < closest) {
					closest = dist;
				}
			}

			if (closest == -1)
				continue;

			if (bestLoc == null || closest < bestDist) {
				bestLoc = loc;
				bestDist = closest;
			}
		}

		return bestLoc;
	}
}
