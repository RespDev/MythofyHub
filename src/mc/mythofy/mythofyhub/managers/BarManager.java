package mc.mythofy.mythofyhub.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import lombok.Getter;
import mc.mythofy.mythofyhub.MythofyHub;

public class BarManager {

	private int task = -1;
	private final MythofyHub mythofyhub = MythofyHub.getInstance();
	@Getter private BossBar bossbar;

	public void addPlayer(Player p) {
		bossbar.addPlayer(p);
	}

	public void createBossbar() {
		bossbar = Bukkit.createBossBar(ChatColor.GOLD + "" + ChatColor.BOLD + "MYTHOFY - MINIGAMES BETA", BarColor.PURPLE, BarStyle.SOLID);
		bossbar.setVisible(true);
		startBarScheduler();
	}

	public void startBarScheduler() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(mythofyhub, new Runnable() {

			Integer message = 0;
			double barProgress = 1.0;
			double time = 1.0 / 120;

			@Override
			public void run() {
				bossbar.setProgress(barProgress);

				switch (message) {
				case 1:
					bossbar.setTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + "MYTHOFY - STORE.MYTHOFY.NET");
					break;
				case 2:
					bossbar.setTitle(ChatColor.AQUA + "" + ChatColor.BOLD + "MYTHOFY - FACTIONS");
					break;
				case 3:
					bossbar.setTitle(ChatColor.GREEN + "" + ChatColor.BOLD + "MYTHOFY - CREATIVE");
					break;
				case 4:
					bossbar.setTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "MYTHOFY - MINIGAMES BETA");
					break;
				case 5:
					bossbar.setTitle(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MYTHOFY - FACTIONS");
					message = 0;
					break;
				}

				barProgress = barProgress - time;
				if (barProgress <= 0) {
					message++;
					barProgress = 1.0;
				}
			}
		}, 0, 0);
	}
}
