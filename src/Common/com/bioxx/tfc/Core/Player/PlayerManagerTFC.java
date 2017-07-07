package com.bioxx.tfc.Core.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

@SuppressWarnings({"CanBeFinal", "Convert2Diamond"})
public class PlayerManagerTFC
{
	public List<PlayerInfo> players;
	private static final PlayerManagerTFC INSTANCE = new PlayerManagerTFC();

	public static PlayerManagerTFC getInstance()
	{
		return INSTANCE;
	}

	private PlayerManagerTFC()
	{
		players = new ArrayList<PlayerInfo>();
	}

	public PlayerInfo getPlayerInfoFromPlayer(EntityPlayer player)
	{
		for(PlayerInfo pi : players)
		{
			if (pi.playerName.equals(player.getCommandSenderName()) && pi.playerUUID.equals(player.getUniqueID()))
				return pi;
		}
		return null;
	}

	public PlayerInfo getPlayerInfoFromName(String name)
	{
		for(PlayerInfo pi : players)
		{
			if(pi.playerName.equals(name))
				return pi;
		}
		return null;
	}

	public PlayerInfo getPlayerInfoFromUUID(String uuid)
	{
		for(PlayerInfo pi : players)
		{
			// UUID == String? Bug?
			//!TODO: check
			if(pi.playerUUID.equals(UUID.fromString(uuid)))
				return pi;
		}
		return null;
	}

	public PlayerInfo getClientPlayer()
	{
		if (!players.isEmpty())
			return players.get(0);
		return null;
	}
}
