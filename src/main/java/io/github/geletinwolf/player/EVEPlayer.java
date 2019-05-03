package io.github.geletinwolf.player;

import io.github.geletinwolf.player.effects.EffectBleeding;
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

	public EVEPlayer(OfflinePlayer p) {
		this.player = p;
	}

	public void giveBuff(EffectType type, int duration) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Object o = type.getTarget().getDeclaredConstructor().newInstance();
		((EffectBleeding) o).Do(player, duration);
	}

}
