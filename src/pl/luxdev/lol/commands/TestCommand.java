package pl.luxdev.lol.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pl.luxdev.lol.basic.CommandAPI;

public class TestCommand extends CommandAPI {
	
	public TestCommand(){
		super("TestCommand", "TestCommand", "/TestCommand", "test", Arrays.asList("tt"));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		switch(args.length){
		case 0 :
			
		}
		
	}

}
