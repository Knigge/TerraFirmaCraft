package com.bioxx.tfc.api.Events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class EntityArmorCalcEvent extends EntityEvent {
	public final EventType eventType;
	public float incomingDamage;
	/**
	 * incomingDamage can be modified and is used for further calculations in the armor code
	 */
	public EntityArmorCalcEvent(EntityLivingBase p, float damage, EventType eType) {
		super(p);
		incomingDamage = damage;
		eventType = eType;
	}

	public enum EventType {PRE, POST}
}
