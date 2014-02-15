package com.danwink.shipguys.entities;

import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.components.PlayerComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.components.UpdateComponent;
import com.danwink.shipguys.es.Entity;

public class Player extends Entity
{
	public Player()
	{
		super();
		add( new PositionComponent() );
		add( new MoveComponent() );
		add( new PlayerComponent() );
		add( new UpdateComponent() );
	}
	
	public Player( int sender )
	{
		this();
		getComponent( PlayerComponent.class ).playerId = sender;
	}
}
