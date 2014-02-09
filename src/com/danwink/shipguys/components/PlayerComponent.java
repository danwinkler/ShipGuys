package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class PlayerComponent extends EntityComponent 
{
	static 
	{
		componentName = "player";
	}
	
	public PlayerComponent( int playerId )
	{
		this.playerId = playerId;
	}
	
	public int playerId;
}
