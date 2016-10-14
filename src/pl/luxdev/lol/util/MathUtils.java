package pl.luxdev.lol.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class MathUtils {

	public static double trim(int degree, double d) {
        String format = "#.#";

        for (int i = 1; i < degree; i++)
            format += "#";

        DecimalFormatSymbols symb = new DecimalFormatSymbols(Locale.US);
        DecimalFormat twoDForm = new DecimalFormat(format, symb);
        return Double.valueOf(twoDForm.format(d));
    }

    public static Random random = new Random();

    public static int r(int i) {
        return random.nextInt(i);
    }

    public static double offset2d(Entity a, Entity b) {
        return offset2d(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset2d(Location a, Location b) {
        return offset2d(a.toVector(), b.toVector());
    }

    public static double offset2d(Vector a, Vector b) {
        a.setY(0);
        b.setY(0);
        return a.subtract(b).length();
    }

    public static double offset(Entity a, Entity b) {
        return offset(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset(Location a, Location b) {
        return offset(a.toVector(), b.toVector());
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }

    public static double offsetSquared(Entity a, Entity b) {
        return offsetSquared(a.getLocation(), b.getLocation());
    }

    public static double offsetSquared(Location a, Location b) {
        return offsetSquared(a.toVector(), b.toVector());
    }

    public static double offsetSquared(Vector a, Vector b) {
        return a.distanceSquared(b);
    }

    public static Location toSpecify(Location l1, Location l2) {
        int x = l1.getBlockX();
        int y = l1.getBlockY();
        int z = l1.getBlockZ();
        int x1 = l2.getBlockX();
        int y1 = l2.getBlockY();
        int z1 = l2.getBlockZ();
        if (l1.getWorld().getName() != l2.getWorld().getName())
            return null;
        float yaw = (float) (Math.atan2((x - x1), (z - z1)) * (180.0 / Math.PI));
        float pitch = (float) Math.asin((y - y1) / l2.distance(l1));
        return new Location(l1.getWorld(), x1, y1, z1, yaw, pitch);
    }

    public static double rr(double d, boolean bidirectional) {
        if (bidirectional)
            return Math.random() * (2 * d) - d;

        return Math.random() * d;
    }
}
