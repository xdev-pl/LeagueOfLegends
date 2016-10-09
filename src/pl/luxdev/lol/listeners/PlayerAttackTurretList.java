package pl.luxdev.lol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.connorlinfoot.titleapi.TitleAPI;

import pl.luxdev.lol.basic.Hologram;
import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.events.PlayerAttackTurretEvent;
import pl.luxdev.lol.managers.UserManager;
import pl.luxdev.lol.utils.Utils;

public class PlayerAttackTurretList implements Listener {

	@EventHandler
	public void onAttackTurret(PlayerAttackTurretEvent e) {
		Player paramPlayer = e.getPlayer();
		Turret paramTurret = e.getAttackedTurret();
		User u = UserManager.getUser(e.getPlayer().getName());
		if (paramTurret.getTeam() == u.getTeam()) {
			e.getPlayer().sendMessage("§cNie mozesz zniszczyc swojej wiezy.");
			return;
		}
		if (paramTurret.isDestroyed()) {
			e.getPlayer().sendMessage("§cTa wieza jest juz zniszczona.");
			return;
		}
		if (paramTurret.getHp() <= 10) {
			paramTurret.getHologram().delete();
			paramTurret.setDestroyed(true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				TitleAPI.sendFullTitle(p, 20, 20 * 5, 20 * 3, "§6Wieza wroga zniszona! §7(§6Linia: " + Utils.getTurretLine(paramTurret) + "§7)",
						"§a+400 Gold");
			}
		}
		if (paramTurret.getHp() >= 10) {
			paramTurret.setHp(paramTurret.getHp() - 10);
			paramPlayer.sendMessage("§7Turret's Health: §6" + paramTurret.getHp());
			if(paramTurret.getHologram() !=null){
				Hologram holo = paramTurret.getHologram();
				holo.change(new String[] { "Hp wiezy: " + paramTurret.getHp()});
				return;
			}
			Hologram holo = new Hologram(paramTurret.getName(), e.getAttackedBlock().getLocation());
			holo.change(new String[] { "Hp wiezy: " + paramTurret.getHp()});
			paramTurret.setHologram(holo);
		}
	}

}
