package pl.luxdev.lol.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import pl.luxdev.lol.util.Reflection;

public class BorderScreenBlur {
	
	private static Class<?> EnumPacketPlayOutBorder;// Reflection.getCraftClass("PacketPlayOutWorldBorder$EnumWorldBorderAction");
	private static Class<?> WorldBorder; //= Reflection.getCraftClass("WorldBorder");
	private static Method center, distance, time, movement;
	private static Constructor<?> border_constructor, constructor;
	private static Object constant;
	private Object packet;

	
	public static void sendTintScreen(Player p, int dist, double oldradius, double newradius, long delay){
		
	}

}
