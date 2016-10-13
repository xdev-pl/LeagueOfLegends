package pl.luxdev.lol.command;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
