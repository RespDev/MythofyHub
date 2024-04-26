package mc.mythofy.mythofyhub;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mc.mythofy.mythofycommands.MythofyCommands;
import mc.mythofy.mythofyhub.commands.BuildmodeCommand;
import mc.mythofy.mythofyhub.commands.FlyCommand;
import mc.mythofy.mythofyhub.commands.LightningSticksCommand;
import mc.mythofy.mythofyhub.listeners.ArrowListener;
import mc.mythofy.mythofyhub.listeners.BlockListener;
import mc.mythofy.mythofyhub.listeners.ClickListener;
import mc.mythofy.mythofyhub.listeners.DamageListener;
import mc.mythofy.mythofyhub.listeners.DropListener;
import mc.mythofy.mythofyhub.listeners.HungerListener;
import mc.mythofy.mythofyhub.listeners.InventoryClickListener;
import mc.mythofy.mythofyhub.listeners.ItemMoveListener;
import mc.mythofy.mythofyhub.listeners.JoinListener;
import mc.mythofy.mythofyhub.managers.BarManager;
import mc.mythofy.mythofyhub.managers.SidebarManager;

public class MythofyHub extends JavaPlugin {
	
	private static MythofyHub plugin;
	private static FileConfiguration config;
	public String lobbyID;
	private static BarManager bossbar;
	
	@Override
	public void onEnable() {
		// Plugin Instance
		plugin = this;
		bossbar = new BarManager();
		
		// Save Config
		plugin.saveDefaultConfig();
		
		// Load-Variables
		config = plugin.getConfig();
		lobbyID = config.getString("lobby-id");
		
		// Load Plugin
		startScoreboardUpdater();
		bossbar.createBossbar();
		registerCommands();
		registerListeners();
		MythofyCommands.logMessage("Hub", "Enabled");
	}
	
	@Override
	public void onDisable() {
		// Unload Plugin
		bossbar.getBossbar().removeAll();
		MythofyCommands.logMessage("Hub", "Disabled");
	}
	
	private void registerCommands() {
		this.getCommand("fly").setExecutor(new FlyCommand());
		this.getCommand("lightningstick").setExecutor(new LightningSticksCommand());
		this.getCommand("buildmode").setExecutor(new BuildmodeCommand());
		MythofyCommands.logMessage("Hub", "Commands registered");
	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ArrowListener(), this);
		Bukkit.getPluginManager().registerEvents(new ClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new DropListener(), this);
		Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new ItemMoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
		MythofyCommands.logMessage("Hub", "Listeners registered");
	}
	
	private void startScoreboardUpdater() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MythofyHub.getInstance(), new Runnable() {
			@Override
			public void run() {
				Integer size = Bukkit.getOnlinePlayers().size();		
				if (!(size > 0)) return;		
				for (Player o : Bukkit.getOnlinePlayers()) {
					SidebarManager.refreshBoard(o);
				}
			}
		}, 20L, 60L);
	}
	
	public static BarManager bar() {
		return bossbar;
	}
	
	public static MythofyHub getInstance() {
		return plugin;
	}
}
