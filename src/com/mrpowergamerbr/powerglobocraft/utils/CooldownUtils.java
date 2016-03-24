package com.mrpowergamerbr.powerglobocraft.utils;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class CooldownUtils {
	private static HashMap<String, CooldownCore> cooldowns = new HashMap<String, CooldownCore>();

	public static boolean hasCooldown(String type, Player p, int seconds) {
		return hasCooldown(type, p.getName(), seconds);
	}

	public static boolean hasCooldown(String type, String p, int seconds) {
		createType(type);

		return cooldowns.get(type).hasCooldown(p, seconds);
	}

	public static void activateCooldown(String type, Player p) {
		activateCooldown(type, p.getName());
	}

	public static void activateCooldown(String type, String p) {
		createType(type);
		
		cooldowns.get(type).activateCooldown(p);
	}

	public static void activateCooldown(String type, String p, long time) {
		createType(type);
		
		cooldowns.get(type).activateCooldown(p, time);
	}

	public static void removeCooldown(String type, Player p) {
		removeCooldown(type, p.getName());
	}

	public static void removeCooldown(String type, String p) {
		cooldowns.get(type).cooldowns.remove(p);
	}

	public static long getCooldownTime(String type, Player p) {
		return getCooldownTime(type, p.getName());
	}

	public static long getCooldownTime(String type, String p) {
		return cooldowns.get(type).cooldowns.get(p);
	}

	private static void createType(String type) {
		if (!cooldowns.containsKey(type)) {
			cooldowns.put(type, new CooldownCore());
		}
	}
}
