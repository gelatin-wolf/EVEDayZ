package io.github.geletinwolf.events;

import io.github.geletinwolf.Main;
import io.github.geletinwolf.player.EVEPlayer;
import io.github.geletinwolf.player.effects.EffectBase;
import io.github.geletinwolf.player.effects.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerJoin {

	Main plugin;
	EventListener listener;

	public PlayerJoin(Main plugin, EventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}

	public void Do(PlayerJoinEvent event) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());
		if(EffectBase.disabledPlayers.contains(p)) {
			runEffectTasks(p);
		}
	}

	private void runEffectTasks(OfflinePlayer p) {
		try {
		for(EffectType type : EffectBase.leftTimes.get(p).keySet()) {
			EVEPlayer ep = new EVEPlayer(p);
			ep.giveBuff(type, EffectBase.leftTimes.get(p).get(type));
		}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}

}
