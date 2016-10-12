package pl.luxdev.lol.basic;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import pl.luxdev.lol.utils.PacketUtils;
import pl.luxdev.lol.utils.Reflection;

public class TabTitle {

	
	private Class<?> ChatSerializer = Reflection.getCraftClass("IChatBaseComponent$ChatSerializer");
	private Class<?> headerfooter = Reflection.getCraftClass("PacketPlayOutPlayerListHeaderFooter");
	private Field header;
	private Field footer;

	public TabTitle(String h){
		Class<?> headerfooter = Reflection.getCraftClass("PacketPlayOutPlayerListHeaderFooter");
		try {
			header = headerfooter.getDeclaredField("a");
			footer = headerfooter.getDeclaredField("b");
		} catch (Exception e) {
			e.printStackTrace();
		}
		header.setAccessible(true);
	    footer.setAccessible(true);
	    setHeader(h);
	}
	
	public TabTitle(String h, String f){
		this(h);
	    setFooter(f);
	}
	
	public void setHeader(String s){
		try {
			header.set(headerfooter, ChatSerializer.getMethod("a").invoke(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFooter(String s){
		try {
			footer.set(headerfooter, ChatSerializer.getMethod("a").invoke(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(Player p){
		PacketUtils.sendPacket(p, headerfooter);
	}
}