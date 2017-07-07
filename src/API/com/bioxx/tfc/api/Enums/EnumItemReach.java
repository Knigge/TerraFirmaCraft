package com.bioxx.tfc.api.Enums;

public enum EnumItemReach {
	SHORT("Short", /*reach*/0.75),

	MEDIUM("Medium", /*reach*/1),

	FAR("Far", /*reach*/1.25);

	private static final EnumItemReach DISTANCES[] = new EnumItemReach[]{
			SHORT, MEDIUM, FAR};
	public final double multiplier;
	private final String name;

	EnumItemReach(String s, double i) {
		name = s;
		multiplier = i;
	}

	public String getName() {
		return name;
	}
}
