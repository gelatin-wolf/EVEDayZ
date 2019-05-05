package io.github.geletinwolf.player;

import io.github.geletinwolf.player.effects.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class EVEPlayer {

	private OfflinePlayer player;

	public EVEPlayer(String uuid) {
		this.player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
	}

	public EVEPlayer(UUID uuid) { this.player = Bukkit.getOfflinePlayer(uuid); }

	public EVEPlayer(OfflinePlayer p) {
		this.player = p;
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	public void giveBuff(EffectType type, int duration) {
		try {
			Object o = type.getTarget().getDeclaredConstructor().newInstance();
			type.getTarget().getMethod("Do", OfflinePlayer.class, int.class).invoke(o, player, duration);
		} catch ( NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}

}
