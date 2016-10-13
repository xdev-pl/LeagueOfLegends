package pl.luxdev.lol.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import pl.luxdev.lol.util.PacketUtils;
import pl.luxdev.lol.util.Reflection;

public class TabTitle {
	
	private static Class<?> ChatSerializer = Reflection.getCraftClass("IChatBaseComponent");
	private static Class<?> headerfooter = Reflection.getCraftClass("PacketPlayOutPlayerListHeaderFooter");
	private Object object;
	
	public TabTitle(String h, String f){
		setHeaderFooter(h, f);
	}
	
	public void setHeaderFooter(String h, String f) {
		// TODO
	}

	public static void sendHeaderFooter(String h, String f, Player p) {
		try {
			Object header = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + h + "\"}");
			Object footer = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + f + "\"}");
			Constructor<?> constructor = headerfooter.getConstructor(ChatSerializer);
			
			Object packet = constructor.newInstance(header);
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, footer);
			PacketUtils.sendPacket(p, packet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void send(Player p){
		if(object != null) PacketUtils.sendPacket(p, object);
	}
	public void startRandomColors() {
		// TODO Some fun stuff, maybe later, idk yet :(
		
	}
}
