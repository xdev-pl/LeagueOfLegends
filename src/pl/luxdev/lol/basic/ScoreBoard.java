package pl.luxdev.lol.basic;

import java.util.ArrayList;
import java.util.HashMap;
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

public class ScoreBoard {
	
	// TODO: do poprawy
	
	private static final int MAXCHARS = 32;
	private static int CONTENT_UPDATE_INTERVAL = 20;
	private static int TITLE_UPDATE_INTERVAL = 10;
	private static boolean UPDATE_CONTENT = true;
	
	private static List<String> chlist = new ArrayList<String>();
	private static HashMap<Player, ScoreBoard> boards = new HashMap<Player, ScoreBoard>();
	public static int contentTask = 0;
	public static int titleTask = 0;
	
	private Scoreboard board;
	private Objective score;
	private Player player;
	private List<Team> teams = new ArrayList<Team>();
	private HashMap<Team, String> lines = new HashMap<Team, String>();
	private List<String> content = new ArrayList<String>();
	private List<String> title = new ArrayList<String>();
	private int index = 15;
	private int titleindex = 0;
	
	@SuppressWarnings("deprecation")
	public ScoreBoard(Player p, String[] content, String[] title) {
		if(chlist.isEmpty()) addChars();
		for(int i = 0; i < 15 ; i++) {
			this.content.add(content[i]);
		}
		for(String s : title) {
			this.title.add(s);
		}
		player = p;
		titleindex = title.length;
		if(boards.containsKey(p)) boards.remove(p);
		boards.put(p, this);
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		score = board.registerNewObjective("score", "dummy");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		score.setDisplayName(replaceString(this.title.get(0), player));
		for(String s1 : content) {
			Team t = board.registerNewTeam("Team:" + index);
			OfflinePlayer op = Bukkit.getOfflinePlayer((String)chlist.get(this.index - 1));
			t.addPlayer(op);
			
			lines.put(t, s1);
			s1 = replaceString(s1, player);
			if (s1.length() > MAXCHARS) s1 = s1.substring(0, MAXCHARS);
			
			String s2 = "";
			if (s1.length() > 16) {
				s2 = s1.substring(16, s1.length());
				s1 = s1.substring(0, 16);
				setSuffix(t, ChatColor.getLastColors(s1) + s2);
			}
			setPrefix(t, s1);
			score.getScore(op).setScore(index);
			teams.add(t);
			index--;
		}
		player.setScoreboard(board);
		runTasks();
	}
	
	private void addChars() {
		for(int i = 1; i < 10; i++) chlist.add("§" + i);
		for(char c = 'a'; c < 'g'; c++) chlist.add("§" + c);
	}
	
	private String replaceString(String s, Player p) {
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
	
	public void setContent(String[] content) {
		this.content.clear();
		for(int i = 0; i < 15 ; i++) {
			this.content.add(content[i]);
		}
		for(String s1 : content) {
			lines.clear();
			teams.clear();
			
			Team t = board.registerNewTeam("Team:" + index);
			OfflinePlayer op = Bukkit.getOfflinePlayer((String)chlist.get(this.index - 1));
			t.addPlayer(op);
			
			lines.put(t, s1);
			s1 = replaceString(s1, player);
			if (s1.length() > MAXCHARS) s1 = s1.substring(0, MAXCHARS);
			
			String s2 = "";
			if (s1.length() > 16) {
				s2 = s1.substring(16, s1.length());
				s1 = s1.substring(0, 16);
				setSuffix(t, ChatColor.getLastColors(s1) + s2);
			}
			setPrefix(t, s1);
			score.getScore(op).setScore(index);
			teams.add(t);
			index--;
		}
	}
	
	public void updateContent() {
		for (Team t : teams) {
			String s1 = lines.get(t);
			s1 = replaceString(s1, player);
			if (s1.length() > MAXCHARS) s1 = s1.substring(0, MAXCHARS);
			
			String s2 = "";
			if (s1.length() > 16) {
				s2 = s1.substring(16, s1.length());
				s1 = s1.substring(0, 16);
				setSuffix(t, ChatColor.getLastColors(s1) + s2);
			}
			setPrefix(t, s1);
		}
	}
	
	public void updateTitle() {
		if (titleindex > title.size() - 1) titleindex = 0;
		String s = replaceString(title.get(titleindex++), player);
		if (s.length() > MAXCHARS) s = s.substring(0, MAXCHARS);
		score.setDisplayName(s);
	}

	public void remove() {
		boards.remove(player);
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	
	public void runTasks() {
		if(contentTask == 0) contentTask = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				for (ScoreBoard b : boards.values()) {
					if (UPDATE_CONTENT) b.updateContent();
				}
				for (Player player : Bukkit.getOnlinePlayers()) {
					if ((boards.containsKey(player)) && (player.getScoreboard() == null)) {
						new ScoreBoard(player, content.toArray(new String[content.size()]), title.toArray(new String[title.size()]));
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
