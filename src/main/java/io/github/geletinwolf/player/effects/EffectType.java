package io.github.geletinwolf.player.effects;

public enum EffectType {

	BLEEDING("BLEEDING", EffectBleeding.class, 2000.0D);

	String name;
	Class<EffectBleeding> target;
	Double repeatTime;

	EffectType(String name, Class<EffectBleeding> type, Double repeatTime) {
		this.name = name;
		this.target = type;
		this.repeatTime = repeatTime;
	}

	public Class<EffectBleeding> getTarget() {
		return target;
	}

	public Double getRepeatTime() {
		return repeatTime;
	}

	public String getName() {
		return name;
	}
}
