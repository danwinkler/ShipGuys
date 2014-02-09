package com.danwink.shipguys.entities;

import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.components.PlayerComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.es.Entity;

public class Player extends Entity
{
	{
		add( new PositionComponent() );
		add( new MoveComponent() );
	}
	
	public Player()
	{
		super();
	}
	
	public Player( int sender )
	{
		this();
		((PlayerComponent)getComponent( PlayerComponent.componentName )).playerId = sender;
	}
}
