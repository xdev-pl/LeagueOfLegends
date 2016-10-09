package pl.luxdev.lol.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

public class EntityExplodeList implements Listener {

	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		for(Block b : e.blockList()){
			@SuppressWarnings("deprecation")
			FallingBlock fb = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
			b.setType(Material.AIR);
			float x = (float) 0.1 + (float) (Math.random() * 0.4);
			double y = 0.5;// (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
			float z = (float) 0.1 + (float) (Math.random() * 0.4);
			fb.setVelocity(new Vector(x, y, z));
		}
	}
}
