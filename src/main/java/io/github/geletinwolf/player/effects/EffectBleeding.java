package io.github.geletinwolf.player.effects;


public class EffectBleeding extends EffectBase {

	@Override
	EffectType getType() {
		return EffectType.BLEEDING;
	}

	@Override
	void onStart() {
		if(player.isOnline()) {
			player.getPlayer().sendMessage("Start");
		}
	}

	@Override
	void onEnd() {
		if(player.isOnline()) {
			player.getPlayer().sendMessage("End");
		}
	}

	@Override
	void onTask() {
		if(player.isOnline()) {
			player.getPlayer().sendMessage("Tasked");
		}
	}
}
