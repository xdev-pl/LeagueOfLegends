package pl.luxdev.lol.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pl.luxdev.lol.basic.game.Turret;

public class PlayerAttackTurretEvent extends Event implements Cancellable {

	public static final HandlerList handlers = new HandlerList();

	private final Player p;
	private final Turret t;
	private final Block a;

	private boolean cancelled;
	
	public PlayerAttackTurretEvent(Turret t, Player p, Block a){
		this.t = t;
		this.p = p;
		this.a = a;
		this.cancelled = false;
	}

	public Block getAttackedBlock(){
		return this.a;
	}

	public Player getPlayer(){
		return this.p;
	}

	public Turret getAttackedTurret(){
		return this.t;
	}
	
	public HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
}