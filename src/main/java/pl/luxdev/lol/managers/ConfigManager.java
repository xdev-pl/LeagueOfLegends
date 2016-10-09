package pl.luxdev.lol.managers;

import org.bukkit.configuration.file.FileConfiguration;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.basic.Arena;
import pl.luxdev.lol.basic.Champion;
import pl.luxdev.lol.basic.Inhibitor;
import pl.luxdev.lol.basic.Nexus;
import pl.luxdev.lol.basic.Team;
import pl.luxdev.lol.basic.Turret;
import pl.luxdev.lol.types.ChampType;
import pl.luxdev.lol.types.GameState;
import pl.luxdev.lol.types.LineType;
import pl.luxdev.lol.types.TeamType;
import pl.luxdev.lol.utils.Utils;

public class ConfigManager {
private static FileConfiguration config = null;
	
	public static void load(){
		FileManager.checkFiles();
		config = Main.getInst().getConfig();
		loadChampions();
		loadArenas();
		count();
	}
	
	public static FileConfiguration getCfg(){
		if(Main.getInst().getConfig() == null) load();
		return config;
	}
	
	private static void loadChampions(){
		ChampionManager.clearChampions();
		for(String s : getCfg().getConfigurationSection("champions").getKeys(false)){
			Utils.info("Loading champion: " + s);
			Champion c = new Champion(s);
			c.setDamage(getCfg().getInt("champions."+s+".damage"));
			c.setDisplayname(getCfg().getString("champions."+s+".name"));
			c.setType(ChampType.valueOf(getCfg().getString("champions."+s+".type")));
			ChampionManager.addChampion(c);
		}
		Utils.info("Loaded "+ChampionManager.getChampions().size()+" champions");
	}
	
	private static void loadArenas() {
		ArenaManager.clearArenas();
		for(String s : getCfg().getConfigurationSection("arenas").getKeys(false)){
			Utils.info("Loading arena: " + s);
			Arena a = new Arena(s);
			a.setDisplayName(getCfg().getString("arenas."+s+".name"));
			a.setState(GameState.WAITING);
			loadTeams(a);
			loadNexus(a);
			loadInhibitors(a);
			loadTurrets(a);
			ArenaManager.addArena(a);
		}
	}
	
	private static void loadTeams(Arena a) {
		for(String s : getCfg().getConfigurationSection("arenas."+a.getName()+".teams").getKeys(false)){
			Utils.info("Loading team: " + s);
			Team t = new Team(getCfg().getString("arenas."+a.getName()+".teams."+s+".name"));
			t.setType(TeamType.valueOf(s));
			t.setSpawnLocation(Utils.locFromString(getCfg().getString("arenas."+a.getName()+".teams"+s+".spawnloc")));
			a.addTeam(t);
		}
	}
	
	private static void loadNexus(Arena a){
		for(Team t : a.getTeams()){
			Utils.info("Loading nexus: " + t.getName());
			Nexus n = new Nexus();
			n.setDestroyed(false);
			n.setTeam(t.getType());
			n.setHp(getCfg().getInt("arenas."+a.getName()+".teams."+t.getType()+".nexus.hp"));
			n.setLocation(Utils.locFromString(getCfg().getString("arenas."+a.getName()+".teams"+t.getType()+".nexus.location")));
			t.setNexus(n);
		}
	}
	
	private static void loadInhibitors(Arena a){
		for(Team t : a.getTeams()){
			for(String s : getCfg().getConfigurationSection("arenas."+a.getName()+".teams."+t.getType()+".inhibitors").getKeys(false)){
				Inhibitor i = new Inhibitor(getCfg().getString("arenas."+a.getName()+".teams."+t.getType()+".inhibitors."+s+".name"));
				Utils.info("Loading inhibitor: " + i.getName());
				i.setDestroyed(false);
				i.setTeam(t.getType());
				i.setHp(getCfg().getInt("arenas."+a.getName()+".teams."+t.getType()+".inhibitors."+s+".hp"));
				i.setLocation(Utils.locFromString(getCfg().getString("arenas."+a.getName()+".teams."+t.getType()+".inhibitors."+s+".location")));
				t.addInhibitor(i);
			}
		}
	}
	
	private static void loadTurrets(Arena a){
		for(Team tm : a.getTeams()){
			for(String s : getCfg().getConfigurationSection("arenas."+a.getName()+".teams."+tm.getType()+".turrets").getKeys(false)){
				Turret t = new Turret(getCfg().getString("arenas."+a.getName()+".teams."+tm.getType()+".turrets."+s+".name"));
				Utils.info("Loading turret: " + t.getName());
				t.setDestroyed(false);
				t.setTarget(null);
				t.setHologram(null);
				t.setTeam(tm.getType());
				t.setAttackStrength(getCfg().getInt("arenas."+a.getName()+".teams."+tm.getType()+".turrets."+s+".attackStrength"));
				t.setHp(getCfg().getInt("arenas."+a.getName()+".teams."+tm.getType()+".turrets."+s+".hp"));
				t.setLine(LineType.valueOf(getCfg().getString("arenas."+a.getName()+".teams."+tm.getType()+".turrets."+s+".line")));
				t.setLocation(Utils.locFromString(getCfg().getString("arenas."+a.getName()+".teams."+tm.getType()+".turrets."+s+".location")));
				tm.addTurret(t);
			}
		}
	}
	
	private static void count(){
		Utils.info("Loaded "+ArenaManager.getArenas().size()+" arenas");
		int teams = 0;
		int nexus = 0;
		int inhibitors = 0;
		int turrets = 0;
		for(Arena a : ArenaManager.getArenas()){
			teams += a.getTeams().size();
			for(Team t : a.getTeams()){
				if(t.getNexus() != null) nexus++;
				inhibitors += t.getInhibitors().size();
				turrets += t.getTurrrets().size();
			}
		}
		Utils.info("Loaded "+teams+" teams");
		Utils.info("Loaded "+nexus+" nexuses");
		Utils.info("Loaded "+inhibitors+" inhibitors");
		Utils.info("Loaded "+turrets+" turrets");
	}
}
