package pl.luxdev.lol.basic;

import org.bukkit.entity.Player;

import pl.luxdev.lol.util.PacketUtils;
import pl.luxdev.lol.util.Reflection;

public class Title {
	
	private static Class<?> PacketPlayOutTitle = Reflection.getCraftClass("PacketPlayOutTitle");
	private static Class<?> IChatBaseComponent = Reflection.getCraftClass("IChatBaseComponent");
	private static Class<?> PacketActions = Reflection.getCraftClass("PacketPlayOutTitle$EnumTitleAction");
	private static Class<?> ChatSerializer = Reflection.getCraftClass("ChatComponentText");
	private int fadeIn;
	private int fadeOut;
	private int time;
	private String title;
	private String subtitle;
	
	private Object packet;
	
	public Title(String title, String subtitle, int fadeIn, int fadeOut, int time){
		this.fadeIn = fadeIn;
		this.fadeOut = fadeOut;
		this.time = time;
		this.title = title;
		this.subtitle = subtitle;
	}
	public void send(Player p){
		try{
			
			
			
			Object[] actions = PacketActions.getEnumConstants();
			packet = PacketPlayOutTitle.getConstructor(PacketActions, IChatBaseComponent, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(actions[2], null, fadeIn * 20, time * 20, fadeOut * 20);
			PacketUtils.sendPacket(p, packet);
			Object serialized = ChatSerializer.getConstructor(String.class).newInstance(title);
			packet = PacketPlayOutTitle.getConstructor(PacketActions, IChatBaseComponent).newInstance(actions[0], serialized);
			PacketUtils.sendPacket(p, packet);
			if(subtitle != ""){
				serialized = ChatSerializer.getConstructor(String.class).newInstance(subtitle);
				packet = PacketPlayOutTitle.getConstructor(PacketActions, IChatBaseComponent).newInstance(actions[1], serialized);
				PacketUtils.sendPacket(p, packet);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void resetTitleSubTitle(Player p){
		try{
			Object[] actions = PacketActions.getEnumConstants();
			packet = PacketPlayOutTitle.getConstructor(PacketActions, IChatBaseComponent, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(actions[2], null, null, null, null);
			PacketUtils.sendPacket(p, packet);
			packet = PacketPlayOutTitle.getConstructor(PacketActions, IChatBaseComponent).newInstance(actions[0], null);
			PacketUtils.sendPacket(p, packet);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
