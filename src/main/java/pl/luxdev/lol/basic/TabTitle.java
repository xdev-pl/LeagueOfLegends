package pl.luxdev.lol.basic;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;

public class TabTitle {
	
	private PacketPlayOutPlayerListHeaderFooter headerfooter;
	private Field header;
	private Field footer;

	public TabTitle(String h){
		headerfooter = new PacketPlayOutPlayerListHeaderFooter();
		try {
			header = headerfooter.getClass().getDeclaredField("a");
			footer = headerfooter.getClass().getDeclaredField("b");
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
			header.set(headerfooter, ChatSerializer.a(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFooter(String s){
		try {
			footer.set(headerfooter, ChatSerializer.a(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(Player p){
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(headerfooter);
	}
}