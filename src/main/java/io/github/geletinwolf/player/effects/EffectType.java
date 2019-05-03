package io.github.geletinwolf.player.effects;

public enum EffectType {

	BLEEDING(EffectBleeding.class, 2000.0D);

	Class<EffectBleeding> target;
	Double repeatTime;

	EffectType(Class<EffectBleeding> type, Double repeatTime) {
		this.target = type;
		this.repeatTime = repeatTime;
	}

	public Class<EffectBleeding> getTarget() {
		return target;
	}

	public Double getRepeatTime() {
		return repeatTime;
	}
}
