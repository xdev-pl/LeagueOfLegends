package pl.luxdev.lol.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import pl.luxdev.lol.Main;
import pl.luxdev.lol.type.ScoreBoardType;

public class ScoreBoard {
	// TODO: Config ?
	private static final String[] TITLE = { "§eLeague of Legends", "§6League of Legends", "§eLeague of Legends" };
	private static HashMap<ScoreBoardType, String[]> content = new HashMap<ScoreBoardType, String[]>();
	static {
		content.put(ScoreBoardType.LOBBY, new String[]{"§aLobby", "§22", "§33", "§44", "§55", "§66", "§77", "§88", "§99", "§010", "§a11", "§b12", "§c13", "§d14", "§e15                            "});
		content.put(ScoreBoardType.GAME, new String[]{"§aGame", "§22", "§33", "§44", "§55", "§66", "§77", "§88", "§99", "§010", "§a11", "§b12", "§c13", "§d14", "§e15                            "});
	} 
	
	private static final int MAXCHARS = 32;
	private static final int CONTENT_UPDATE_INTERVAL = 20;
	private static final int TITLE_UPDATE_INTERVAL = 10;
	private static final boolean UPDATE_CONTENT = true;
	
	public static int contentTask = 0;
	public static int titleTask = 0;
	private static List<String> chlist = new ArrayList<String>();
	private static HashMap<Player, ScoreBoard> boards = new HashMap<Player, ScoreBoard>();
	
	private Player player;
	private Scoreboard board;
	private Objective score;
	private ScoreBoardType type;
	private LinkedHashMap<Team, String> lines = new LinkedHashMap<Team, String>();
	private int titleindex = 0;
	
	@SuppressWarnings("deprecation")
	private ScoreBoard(Player p, ScoreBoardType t) {
		if(chlist.isEmpty()) addChars();
		player = p;
		type = t;
		boards.put(p, this);
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		score = board.registerNewObjective("score", "dummy");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		score.setDisplayName(replaceString(TITLE[titleindex]));
		
		int index = 15;
		for(String s : content.get(t)) {
			Team team = board.registerNewTeam("Team:" + index);
			OfflinePlayer op = Bukkit.getOfflinePlayer((String)chlist.get(index - 1));
			team.addPlayer(op);
			lines.put(team, s);
			setContent(team, s);
			score.getScore(op).setScore(index--);
		}
		player.setScoreboard(board);
	}
		
	
	public ScoreBoardType getType() {
		return type;
	}
	
	public void setType(ScoreBoardType t) {
		int index = 0;
		Team[] team = lines.keySet().toArray(new Team[lines.size()]);
		for(String s : content.get(t)) {
			lines.remove(team);
			lines.put(team[index], s);
			setContent(team[index++], s);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void remove() {
		boards.remove(player);
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	
	private void setContent(Team t, String s) {
		s = replaceString(s);
		if (s.length() > MAXCHARS) s = s.substring(0, MAXCHARS);
		String s2 = "";
		if (s.length() > 16) {
			s2 = s.substring(16, s.length());
			s = s.substring(0, 16);
		}
		setPrefix(t, s);
		setSuffix(t, ChatColor.getLastColors(s) + s2);
	}
	
	private String replaceString(String s) {
		//TODO: Podmiana zmiennych
		return s;
	}
	
	private void setSuffix(Team t, String s) {
		if (s.length() > 16) s = s.substring(0, 16);
		t.setSuffix(s);
	}
	
	private void setPrefix(Team t, String s) {
		if (s.length() > 16) s = s.substring(0, 16);
		t.setPrefix(s);
	}
	
	private void addChars() {
		for(int i = 1; i < 10; i++) chlist.add("§" + i);
		for(char c = 'a'; c < 'g'; c++) chlist.add("§" + c);
	}
	
	public static ScoreBoard get(Player p, ScoreBoardType t){
		if(boards.containsKey(p)) {
			ScoreBoard b = boards.get(p);
			if(b.getType() != t) b.setType(t);
			return b;
		}
		return new ScoreBoard(p, t);
	}
	
	public static ScoreBoard get(Player p){
		return get(p, ScoreBoardType.LOBBY);
	}
	
	private void updateContent() {
		for (Team t : lines.keySet()) {
			setContent(t, lines.get(t));
		}
	}
	
	private void updateTitle() {
		if (titleindex > TITLE.length - 1) titleindex = 0;
		String s = replaceString(TITLE[titleindex++]);
		if (s.length() > MAXCHARS) s = s.substring(0, MAXCHARS);
		score.setDisplayName(s);
	}
	
	public static void runTasks() {
		if(contentTask == 0) contentTask = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				for (ScoreBoard b : boards.values()) {
					if (UPDATE_CONTENT) b.updateContent();
				}
				for (Player player : Bukkit.getOnlinePlayers()) {
					if ((boards.containsKey(player)) && ((player.getScoreboard() == null) || boards.get(player) == null)) {
						get(player).remove();
					}
				}
			}
		}, 0L, CONTENT_UPDATE_INTERVAL).getTaskId();
			
		if(titleTask == 0) titleTask = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				for (ScoreBoard b : boards.values()) {
					b.updateTitle();
				}
			}
		}, 0L, TITLE_UPDATE_INTERVAL).getTaskId();
	}
}
