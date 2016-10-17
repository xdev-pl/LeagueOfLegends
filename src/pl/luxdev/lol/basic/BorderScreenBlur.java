package pl.luxdev.lol.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import pl.luxdev.lol.util.PacketUtils;
import pl.luxdev.lol.util.Reflection;

public class BorderScreenBlur {
	
	private static Class<?> EnumPacketPlayOutBorder;
	private static Class<?> WorldBorder;
	private static Class<?> PacketPlayOutBorder;
	private static Method center, distance, time, movement;
	private static Constructor<?> borderConstructor, constructor;
	private static Object constant;
	private static Object packet;

	static{
		try{
			EnumPacketPlayOutBorder = Reflection.getCraftClass("PacketPlayOutWorldBorder$EnumWorldBorderAction");
			WorldBorder = Reflection.getCraftClass("WorldBorder");
			PacketPlayOutBorder = Reflection.getCraftClass("PacketPlayOutWorldBorder");
			borderConstructor = WorldBorder.getConstructor();
			constructor = PacketPlayOutBorder.getConstructor(WorldBorder, EnumPacketPlayOutBorder);
			Method[] methods = WorldBorder.getMethods();
			String setCenter = "setCenter";
			String setWarningDistance = "setWarningDistance";
			String setWarningTime = "setWarningTime";
			String transitionSizeBetween = "transitionSizeBetween";
			if (!Reflection.isInClass(methods, setCenter))
				setCenter = "c";
			if (!Reflection.isInClass(methods, setWarningDistance))
				setWarningDistance = "c";
			if (!Reflection.isInClass(methods, setWarningTime))
				setWarningTime = "b";
			if (!Reflection.isInClass(methods, transitionSizeBetween))
				transitionSizeBetween = "a";
			center = WorldBorder.getMethod(setCenter, double.class, double.class);
			distance = WorldBorder.getMethod(setWarningDistance, int.class);
			time = WorldBorder.getMethod(setWarningTime, int.class);
			movement = WorldBorder.getMethod(transitionSizeBetween, double.class, double.class, long.class);
			for(Object m : EnumPacketPlayOutBorder.getEnumConstants()){
				if(m.toString().equals("INITIALIZE")){
					constant = m;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void sendTintScreen(Player p, int dist, double oldradius, double newradius, long delay){
		try{
			Object worldBorder = borderConstructor.newInstance();
			center.invoke(worldBorder, p.getLocation().getX(), p.getLocation().getY());
			distance.invoke(worldBorder, dist);
			time.invoke(worldBorder, 15);
			movement.invoke(worldBorder, oldradius,newradius,delay);
			packet = constructor.newInstance(worldBorder,constant);
			PacketUtils.sendPacket(p, packet);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void setBorder(Player p, int percentage){
		int dist = -10000 * percentage + 1300000;
		sendTintScreen(p, dist, 200000D, 200000D, 0);
	}
	public static void sendBorder(Player p, int percentage){
		setBorder(p, percentage);
	}

}
