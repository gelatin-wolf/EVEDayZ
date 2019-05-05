package io.github.geletinwolf.player.effects;

import io.github.geletinwolf.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class EffectBase {

	OfflinePlayer player;

	public static HashMap<UUID, HashMap<EffectType, Integer>> repeatingTasks = new HashMap<>();
	public static HashMap<UUID, HashMap<EffectType, Integer>> endTasks = new HashMap<>();
	public static HashMap<UUID, HashMap<EffectType, Integer>> leftTimes = new HashMap<>();

	public static List<UUID> disabledPlayers = new ArrayList<>();

	public void Do(OfflinePlayer p, int duration){ // ms
		this.player = p;
		BukkitScheduler bs = Main.getScheduler();
		Main plugin = Main.getInstance();

		onStart();

		HashMap<EffectType, Integer> lthash;
		if(!leftTimes.keySet().contains(p.getUniqueId())) {
			lthash = new HashMap<>();
			lthash.put(getType(), duration);
		} else {
			lthash = leftTimes.get(p.getUniqueId());
			lthash.put(getType(), duration);
		}
		leftTimes.put(p.getUniqueId(), lthash);

		int rt = getType().getRepeatTime().intValue();
		int repeat = bs.scheduleSyncRepeatingTask(plugin, () -> {
			onTask();
			HashMap<EffectType, Integer> hash = leftTimes.get(p.getUniqueId());
			hash.put(getType(), hash.get(getType()) - rt);
			leftTimes.put(p.getUniqueId(), hash);
		}, rt/100, rt/100);

		HashMap<EffectType, Integer> rthash;
		if(!repeatingTasks.keySet().contains(p.getUniqueId())) {
			rthash = new HashMap<>();
			rthash.put(getType(), repeat);
		} else {
			rthash = repeatingTasks.get(p.getUniqueId());
			rthash.put(getType(), repeat);
		}
		repeatingTasks.put(p.getUniqueId(), rthash);

		int end = bs.scheduleSyncDelayedTask(plugin, () -> { // End task
			onEnd();
			bs.cancelTask(repeat);
			repeatingTasks.get(p.getUniqueId()).remove(getType());
			endTasks.get(p.getUniqueId()).remove(getType());
		}, duration/100);

		HashMap<EffectType, Integer> ethash;
		if(!endTasks.keySet().contains(p.getUniqueId())) {
			ethash = new HashMap<>();
			ethash.put(getType(), end);
		} else {
			ethash = endTasks.get(p.getUniqueId());
			ethash.put(getType(), end);
		}
		endTasks.put(p.getUniqueId(), ethash);
	}

	abstract EffectType getType();
	abstract void onStart();
	abstract void onEnd();
	abstract void onTask();

}
