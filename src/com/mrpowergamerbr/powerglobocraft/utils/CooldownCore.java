package com.mrpowergamerbr.powerglobocraft.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class CooldownCore {
	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	
	public boolean hasCooldown(Player p, int seconds) {
		return hasCooldown(p.getName(), seconds);
	}
	
	public boolean hasCooldown(String p, int seconds) {
		if (cooldowns.containsKey(p)) {
			if(cooldowns.get(p) < (System.currentTimeMillis() - seconds * 1000))
				return false;
			else
				return true;
		}
		return false;
	}
	
	public void activateCooldown(String p) {
		cooldowns.put(p, System.currentTimeMillis());
	}
	
	public void activateCooldown(String p, long time) {
		cooldowns.put(p, time);
	}
}
