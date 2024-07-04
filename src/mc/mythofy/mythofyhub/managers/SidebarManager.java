package mc.mythofy.mythofyhub.managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import mc.mythofy.mythofycommands.data.Currency;
import mc.mythofy.mythofycommands.rank.Rank;
import mc.mythofy.mythofycommands.rank.RankManager;
import mc.mythofy.mythofyhub.MythofyHub;
import me.clip.placeholderapi.PlaceholderAPI;

public class SidebarManager implements Listener {

	public static void refreshBoard(Player p) {
		ScoreboardManager SbManager = Bukkit.getScoreboardManager();
		Scoreboard Board = SbManager.getNewScoreboard();
		Objective Obj = Board.registerNewObjective("Board", "dummy");
		Obj.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MYTHOFY");
		Obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		UUID uuid = p.getUniqueId();
		Rank rank = RankManager.getRank(uuid);
		Integer gems = Currency.getGems(uuid);

		Integer index = 9;
		String players = PlaceholderAPI.setPlaceholders(p, "%bungee_total%");

		// Date
		Score Date = Obj.getScore(ChatColor.GRAY + DateTimeFormatter.ofPattern("MM/dd/yy").format(LocalDateTime.now()));
		Date.setScore(index);
		index--;

		// Space
		Score Space1 = Obj.getScore(ChatColor.WHITE + "  " + " ");
		Space1.setScore(index);
		index--;

		// Rank
		Score DisplayRank = Obj.getScore(ChatColor.WHITE + "Rank: " + rank.getScoreboardName());
		DisplayRank.setScore(index);
		index--;

		// Money
		Score Tokens = Obj.getScore(ChatColor.WHITE + "Gems: " + ChatColor.AQUA + gems);
		Tokens.setScore(index);
		index--;

		// Space
		Score Space2 = Obj.getScore(ChatColor.DARK_GREEN + "  ");
		Space2.setScore(index);
		index--;

		// Looby
		Score Lobby = Obj.getScore(ChatColor.WHITE + "Lobby: " + ChatColor.AQUA + "#" + MythofyHub.getInstance().lobbyID);
		Lobby.setScore(index);
		index--;

		// Players
		Score Players = Obj.getScore(ChatColor.WHITE + "Players: " + ChatColor.AQUA + players);
		Players.setScore(index);
		index--;

		// Space
		Score Space3 = Obj.getScore(ChatColor.translateAlternateColorCodes('&', "&f&b &d &c"));
		Space3.setScore(index);
		index--;

		// Footer
		Score Footer = Obj.getScore(ChatColor.GOLD + "play.mythofy.net");
		Footer.setScore(index);
		index--;

		p.setScoreboard(Board);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent j) {
		refreshBoard(j.getPlayer());
	}
}