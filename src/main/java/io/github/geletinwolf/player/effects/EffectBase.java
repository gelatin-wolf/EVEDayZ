package io.github.geletinwolf.player.effects;

import io.github.geletinwolf.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class EffectBase {

	OfflinePlayer player;

	public static HashMap<OfflinePlayer, HashMap<EffectType, Integer>> repeatingTasks = new HashMap<>();
	public static HashMap<OfflinePlayer, HashMap<EffectType, Integer>> endTasks = new HashMap<>();

	public static HashMap<OfflinePlayer, HashMap<EffectType, Integer>> leftTimes = new HashMap<>();

	public static List<OfflinePlayer> disabledPlayers = new ArrayList<>();

	public void Do(OfflinePlayer p, int duration){ // ms
		this.player = p;
		BukkitScheduler bs = Main.getScheduler();
		Main plugin = Main.getInstance();

		onStart();

		HashMap<EffectType, Integer> lthash;
		if(!leftTimes.keySet().contains(p)) {
			lthash = new HashMap<>();
			lthash.put(getType(), duration);
		} else {
			lthash = leftTimes.get(p);
			lthash.put(getType(), duration);
		}
		leftTimes.put(p, lthash);

		int rt = getType().getRepeatTime().intValue();
		int repeat = bs.scheduleSyncRepeatingTask(plugin, () -> {
			onTask();
			HashMap<EffectType, Integer> hash = leftTimes.get(p);
			hash.put(getType(), hash.get(getType()) - rt);
			leftTimes.put(p, hash);
		}, rt/500, rt/500);

		HashMap<EffectType, Integer> rthash;
		if(!repeatingTasks.keySet().contains(p)) {
			rthash = new HashMap<>();
			rthash.put(getType(), repeat);
		} else {
			rthash = repeatingTasks.get(p);
			rthash.put(getType(), repeat);
		}
		repeatingTasks.put(p, rthash);

		int end = bs.scheduleSyncDelayedTask(plugin, () -> { // End task
			onEnd();
			bs.cancelTask(repeat);
			repeatingTasks.get(p).remove(getType());
			endTasks.get(p).remove(getType());
		}, duration/500);

		HashMap<EffectType, Integer> ethash;
		if(!endTasks.keySet().contains(p)) {
			ethash = new HashMap<>();
			ethash.put(getType(), end);
		} else {
			ethash = endTasks.get(p);
			ethash.put(getType(), end);
		}
		endTasks.put(p, ethash);
	}

	abstract EffectType getType();
	abstract void onStart();
	abstract void onEnd();
	abstract void onTask();

}
