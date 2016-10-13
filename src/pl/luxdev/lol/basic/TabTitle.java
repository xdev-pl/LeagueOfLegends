package pl.luxdev.lol.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import pl.luxdev.lol.utils.PacketUtils;
import pl.luxdev.lol.utils.Reflection;

public class TabTitle {

	private static Class<?> ChatSerializer = Reflection.getCraftClass("IChatBaseComponent");
	private static Class<?> headerfooter = Reflection.getCraftClass("PacketPlayOutPlayerListHeaderFooter");

	public static void sendHeaderFooter(String h, String f, Player p) {
		try {
			Object header = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + h + "\"}");
			Object footer = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + f + "\"}");
			Constructor<?> contrustor = headerfooter.getConstructor(ChatSerializer);
			Object packet = contrustor.newInstance(header);
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, footer);;
			PacketUtils.sendPacket(p, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
