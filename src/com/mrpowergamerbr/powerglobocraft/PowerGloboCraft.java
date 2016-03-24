package com.mrpowergamerbr.powerglobocraft;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.mrpowergamerbr.powerglobocraft.commands.GloboCommand;
import com.mrpowergamerbr.powerglobocraft.utils.TemmieUpdater;

public class PowerGloboCraft extends JavaPlugin implements Listener {
	public static final String prefix = "§8[§c§lGlobo§8] §e";
	
	public static final String pluginName = "PowerGloboCraft";

	public static final String pluginVersion = "v1.0.0";
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		getServer().getPluginCommand("globo").setExecutor(new GloboCommand());
		
		if (getConfig().getBoolean("TemmieUpdater.VerificarUpdates")) {
			new TemmieUpdater(this);
		}
	}

	@Override
	public void onDisable() {
	}

	public PowerGloboCraft getMe() {
		return this;
	}
}

