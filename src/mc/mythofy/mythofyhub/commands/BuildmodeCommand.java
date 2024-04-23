package mc.mythofy.mythofyhub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import mc.mythofy.mythofycommands.rank.Rank;
import mc.mythofy.mythofycommands.rank.RankManager;
import mc.mythofy.mythofyhub.listeners.BlockListener;

public class BuildmodeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("buildmode")) {
			if (!(sender instanceof Player)) return true;
			Player p = (Player) sender;
			Rank rank = RankManager.getRank(p.getUniqueId());
			
			if (rank.getRankId() >= Rank.ADMIN.getRankId()) {
				BlockListener.toggleBuild(p);
			}
			else {
				p.sendMessage(ChatColor.RED + "No permission!");
				return true;
			}
		}
		return false;
	}
}
