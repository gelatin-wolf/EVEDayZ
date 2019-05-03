package io.github.geletinwolf.events;

import io.github.geletinwolf.Main;
import io.github.geletinwolf.player.effects.EffectBase;
import io.github.geletinwolf.player.effects.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit {

	Main plugin;
	EventListener listener;

	public PlayerQuit(Main plugin, EventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}

	public void Do(PlayerQuitEvent event) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());
		if(EffectBase.repeatingTasks.keySet().contains(p)) {
			stopEffectTasks(p);
		}
	}

	private void stopEffectTasks(OfflinePlayer p) {
		for(EffectType type : EffectBase.repeatingTasks.get(p).keySet()) {
			Main.getScheduler().cancelTask(EffectBase.repeatingTasks.get(p).get(type));
			EffectBase.repeatingTasks.get(p).put(type, -1);
		}
		for(EffectType type2 : EffectBase.endTasks.get(p).keySet()) {
			Main.getScheduler().cancelTask(EffectBase.endTasks.get(p).get(type2));
			EffectBase.repeatingTasks.get(p).put(type2, -1);
		}
		EffectBase.disabledPlayers.add(p);
	}

}
