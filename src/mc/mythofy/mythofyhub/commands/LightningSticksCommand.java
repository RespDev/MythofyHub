package mc.mythofy.mythofyhub.commands;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mc.mythofy.mythofycommands.rank.Rank;
import mc.mythofy.mythofycommands.rank.RankManager;

public class LightningSticksCommand implements CommandExecutor {

	ItemStack ls;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();

		if (RankManager.getRank(uuid).getRankId() >= Rank.ADMIN.getRankId()) {
			if (args.length > 0) {
				createLightningStick();
				if (args[0].equalsIgnoreCase("all")) {
					Bukkit.getOnlinePlayers().forEach(player -> player.getInventory().addItem(ls));
					p.sendMessage(ChatColor.GREEN + "Everyone has been given a lightning stick!");
				}
				if (args[0].equalsIgnoreCase("me")) {
					p.getInventory().addItem(ls);
					p.sendMessage(ChatColor.GREEN + "You have been given a lightning stick!");
				}
				p.sendMessage(ChatColor.RED + "Usage:");
				p.sendMessage(ChatColor.RED + "/lightningstick all");
				p.sendMessage(ChatColor.RED + "/lightningstick me");
			} else {
				p.sendMessage(ChatColor.RED + "Usage:");
				p.sendMessage(ChatColor.RED + "/lightningstick all");
				p.sendMessage(ChatColor.RED + "/lightningstick me");
			}
		} else {
			p.sendMessage(ChatColor.RED + "You must be admin or higher to execute this command!");
		}
		return true;
	}

	private void createLightningStick() {
		ItemStack lightningStick = new ItemStack(Material.STICK);
		ItemMeta meta = lightningStick.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Lightning Stick");
		lightningStick.setItemMeta(meta);

		ls = lightningStick;
	}
}