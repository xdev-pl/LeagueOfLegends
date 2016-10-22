package pl.luxdev.lol.util.element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import pl.luxdev.lol.util.reflect.PacketUtils;
import pl.luxdev.lol.util.reflect.Reflection;

public class TabTitle {
	
	private static Class<?> ChatSerializer = Reflection.getCraftClass("IChatBaseComponent");
	private static Class<?> headerfooter = Reflection.getCraftClass("PacketPlayOutPlayerListHeaderFooter");
	private Object object;
	
	public TabTitle(String h, String f){
		sendHeaderFooter(h, f);
	}

	public void sendHeaderFooter(String h, String f) {
		try {
			Object header = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + h + "\"}");
			Object footer = ChatSerializer.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + f + "\"}");
			Constructor<?> constructor = headerfooter.getConstructor(ChatSerializer);
			object = constructor.newInstance(header);
			Field field = object.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(object, footer);
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
