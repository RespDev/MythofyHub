package mc.mythofy.mythofyhub.commands;

import org.bukkit.entity.Player;
import mc.mythofy.mythofycommands.rank.Rank;
import mc.mythofy.mythofyhub.listeners.BlockListener;
import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;

@CommandMeta(rank = Rank.DEVELOPER)
public class BuildmodeCommand extends CoreCommand {
	
    public BuildmodeCommand() {
        super("buildmode");
    }

    @Override
    protected void handleCommand(Player player, String[] args) throws CommandException {
		BlockListener.toggleBuild(player);
    }
}