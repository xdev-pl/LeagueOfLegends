package pl.luxdev.lol.basic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title {

	/*public void send(Player player) {
		if (packetTitle != null) {
			// First reset previous settings
			resetTitle(player);
			try {
				// Send timings first
				Object handle = getHandle(player);
				Object connection = getField(handle.getClass(),
						"playerConnection").get(handle);
				Object[] actions = packetActions.getEnumConstants();
				Method sendPacket = getMethod(connection.getClass(),
						"sendPacket");
				Object packet = packetTitle.getConstructor(packetActions,
						chatBaseComponent, Integer.TYPE, Integer.TYPE,
						Integer.TYPE).newInstance(actions[2], null,
						fadeInTime * (ticks ? 1 : 20),
						stayTime * (ticks ? 1 : 20),
						fadeOutTime * (ticks ? 1 : 20));
				// Send if set
				if (fadeInTime != -1 && fadeOutTime != -1 && stayTime != -1)
					sendPacket.invoke(connection, packet);

				// Send title
				Object serialized = nmsChatSerializer.getConstructor(
						String.class).newInstance(
						ChatColor.translateAlternateColorCodes('&', title));
				packet = packetTitle.getConstructor(packetActions,
						chatBaseComponent).newInstance(actions[0], serialized);
				sendPacket.invoke(connection, packet);
				if (subtitle != "") {
					// Send subtitle if present
					serialized = nmsChatSerializer.getConstructor(String.class)
							.newInstance(
									ChatColor.translateAlternateColorCodes('&',
											subtitle));
					packet = packetTitle.getConstructor(packetActions,
							chatBaseComponent).newInstance(actions[1],
							serialized);
					sendPacket.invoke(connection, packet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void clearTitle(Player player) {
		try {
			// Send timings first
			Object handle = getHandle(player);
			Object connection = getField(handle.getClass(), "playerConnection")
					.get(handle);
			Object[] actions = packetActions.getEnumConstants();
			Method sendPacket = getMethod(connection.getClass(), "sendPacket");
			Object packet = packetTitle.getConstructor(packetActions,
					chatBaseComponent).newInstance(actions[3], null);
			sendPacket.invoke(connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void resetTitle(Player player) {
		try {
			// Send timings first
			Object handle = getHandle(player);
			Object connection = getField(handle.getClass(), "playerConnection")
					.get(handle);
			Object[] actions = packetActions.getEnumConstants();
			Method sendPacket = getMethod(connection.getClass(), "sendPacket");
			Object packet = packetTitle.getConstructor(packetActions,
					chatBaseComponent).newInstance(actions[4], null);
			sendPacket.invoke(connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/}
