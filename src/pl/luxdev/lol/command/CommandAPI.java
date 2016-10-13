package pl.luxdev.lol.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import pl.luxdev.lol.util.Utils;

public abstract class CommandAPI extends Command {
	
	private String perm;
	
	public CommandAPI(String name, String description, String usage, String permission, List<String> aliases){
		super(name, description, usage, aliases);
		
		this.perm = ("leagueoflegends." + permission);
	}
	
	public final boolean execute(CommandSender sender, String s, String[] args){
		if(!(sender.hasPermission(this.perm))){
			sender.sendMessage(Utils.fixColors("&8» &cNie masz uprawnien! &7(" + this.perm + ")"));
			return true;
		}
		try{
			onExecute(sender, args);
		}
		catch (Exception ex){
			sender.sendMessage(Utils.fixColors("I dont know what went wrong my friend :( \n Cause: \n" + ex.getCause()));
			ex.printStackTrace();
		}
		return true;
	}
	
	public abstract void onExecute(CommandSender sender, String[] args);
}
