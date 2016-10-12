package pl.luxdev.lol.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import com.google.common.collect.Lists;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.game.Turret;
import pl.luxdev.lol.types.LineType;

public class Utils {
	
	public static Location locFromString(String s) {
		String[] ss = s.split(" ");
		return new Location(Bukkit.getWorld(ss[0]), Double.parseDouble(ss[1]), Double.parseDouble(ss[2]), Double.parseDouble(ss[3]), Float.parseFloat(ss[4]), Float.parseFloat(ss[5]));
	}

	public static String stringFromLoc(Location l) {
		return new String(l.getWorld().getName() + " " + l.getX() + " " + l.getY() + " " + l.getZ() + " " + l.getYaw() + " " + l.getPitch());
	}
	
	public static void copy(InputStream source, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = source.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			source.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String toJson(String s){
		return "{\"text\": \"" + s + "\"}";
	}
	
	public static String fixColors(String msg) {
		if(msg == null) return msg;
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		msg = msg.replace("%ok", "✓");
		msg = msg.replace("%nieok", "✗");
		msg = msg.replace("%>", "»");
		return msg;
	}
	
	public static List<String> fixColoredList(List<String> coloredList) {
		final List<String> list = Lists.newArrayList();
		for (String str : coloredList) list.add(fixColors(str));
		return list;
	}
	
	private static Logger logger;

	public static Logger getLogger(){
		if(logger == null) logger = Main.getInstance().getLogger();
		return logger;
	}
	
	public static void info(final String... logs) {
		for (final String s : logs) {
			log(Level.INFO, s);
		}
	}

	public static void warning(final String... logs) {
		for (final String s : logs) {
			log(Level.WARNING, s);
		}
	}

	public static void severe(final String... logs) {
		for (final String s : logs) {
			log(Level.SEVERE, s);
		}
	}

	public static void log(final Level level, final String l) {
		getLogger().log(level, l);
	}

	public static void printStackTrace() {
		for (StackTraceElement trace : Thread.currentThread().getStackTrace())
			info(trace.toString());
	}

	public static void printStackTrace(StackTraceElement[] elements) {
		for (StackTraceElement trace : elements)
			info(trace.toString());
	}
	
	public static LineType getTurretLine(Turret turret){
		Location loc = turret.getLocation();
		Material mat = loc.subtract(0, 1,0).getBlock().getType();
		switch(mat){
			case DIAMOND_BLOCK:
				return LineType.TOP;
			case GOLD_BLOCK:
				return LineType.BOT;
			case EMERALD_BLOCK:
				return LineType.MID;
			case COAL_BLOCK:
				return LineType.BASE;
		default:
			break;
		}
		return null;
	}//troche bez sensu..
}
