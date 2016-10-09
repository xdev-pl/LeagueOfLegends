package pl.luxdev.lol.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pl.luxdev.lol.basic.Turret;

public class PlayerAttackTurretEvent extends Event implements Cancellable{

	Player p;
	Turret t;
	Block a;
	
	public PlayerAttackTurretEvent(Turret t, Player p, Block a){
		this.t = t;
		this.p = p;
		this.a = a;
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
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}