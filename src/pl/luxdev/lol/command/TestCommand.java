package pl.luxdev.lol.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.luxdev.lol.basic.BorderScreenBlur;

public class TestCommand extends CommandAPI {
	
	public TestCommand(){
		super("TestCommand", "TestCommand", "/TestCommand", "test", Arrays.asList("TestCommand"));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		int i = Integer.valueOf(args[1]);
		BorderScreenBlur.sendBorder((Player)sender, i);
		Bukkit.broadcastMessage("[TestCommand.java:31] BorderScreenBlur sent successfully. percentage: "+i);
	}
}
