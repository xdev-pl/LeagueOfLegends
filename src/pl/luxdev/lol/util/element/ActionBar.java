package pl.luxdev.lol.util.element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.util.reflect.PacketUtils;
import pl.luxdev.lol.util.reflect.Reflection;

public class ActionBar {

	private final static Class<?> actionBarPacket = Reflection.getCraftClass("PacketPlayOutChat");
	private final static Class<?> iChatBaseComponent = Reflection.getCraftClass("IChatBaseComponent");
	private static int task = 0;
	private static ArrayList<ActionBar> bars = new ArrayList<ActionBar>(); 
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Object packet;
	
	public ActionBar(String s, Player p){
		this(s);
		addPlayer(p);
	}
	
	public ActionBar(String s) {
		this();
		setActionBar(s);
	}
	
	public ActionBar() {
		bars.add(this);
	}
	
	public void setActionBar(String bar) {
		try {
			Constructor<?> constructor = actionBarPacket.getConstructor(iChatBaseComponent, byte.class);
			Method serializerMethod = iChatBaseComponent.getDeclaredClasses()[0].getMethod("a", String.class);
			Object compt = serializerMethod.invoke(iChatBaseComponent, "{\"text\": \"" + bar + "\"}" );
			packet = constructor.newInstance(compt, (byte) 2);
		} catch(Exception e){
			e.printStackTrace();
		}
		runTask();
	}
	
	public void send() {
		if(packet != null) {
			for(Player p : players) PacketUtils.sendPacket(p, packet);
		}
	}
	
	public void removeBar() {
		if(bars.contains(this)) bars.remove(this);
	}

	public Object getPacket() {
		return packet;
	}
	
	public void addPlayer(Player p) {
		if(!players.contains(p)) players.add(p);
	}
	
	public void removePlayer(Player p) {
		if(players.contains(p)) players.remove(p);
	}
	
	public static void runTask() {
		if(task == 0) task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
			@Override
			public void run() {
				for(ActionBar b : bars) {
					b.send();
				}
			}
		}, 0, 20);
	}
}
