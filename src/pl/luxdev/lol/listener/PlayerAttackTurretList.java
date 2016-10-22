package pl.luxdev.lol.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import pl.luxdev.lol.basic.User;
import pl.luxdev.lol.basic.game.Turret;
import pl.luxdev.lol.basic.manager.UserManager;
import pl.luxdev.lol.system.event.PlayerAttackTurretEvent;
import pl.luxdev.lol.util.element.Hologram;
import pl.luxdev.lol.util.element.Title;

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
			e.setCancelled(true);
			return;
		}
		if (paramTurret.getHp() <= 10) {
			paramTurret.getHologram().delete();
			paramTurret.setDestroyed(true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				Title title = new Title("§7(§6 " + paramTurret.getLine() + "§7) §6Wieza wroga zniszona!", "§a+400 Gold", 1,5,3);
				title.send(p);
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
