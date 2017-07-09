package com.bioxx.tfc.api.Util;

import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SameParameterValue", "WeakerAccess", "Convert2Diamond"})
public class KeyBindings {
	public static List<KeyBinding> keyBindingsList = new ArrayList<KeyBinding>();
	public static List<Boolean> isRepeatingList = new ArrayList<Boolean>();

	public static void addKeyBinding(String name, int value, String category) {
		keyBindingsList.add(new KeyBinding(name, value, category));
	}

	public static void addKeyBinding(KeyBinding binding) {
		keyBindingsList.add(binding);
	}

	public static void addIsRepeating(boolean value) {
		isRepeatingList.add(value);
	}

	public static KeyBinding[] gatherKeyBindings() {
		return keyBindingsList.toArray(new KeyBinding[keyBindingsList.size()]);
	}

	public static boolean[] gatherIsRepeating() {
		boolean[] isRepeating = new boolean[isRepeatingList.size()];
		for (int x = 0; x < isRepeating.length; x++)
			isRepeating[x] = isRepeatingList.get(x);
		return isRepeating;
	}
}
