package com.danwink.shipguys.systems.server;

import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.components.UpdateComponent;
import com.danwink.shipguys.es.Entity;
import com.danwink.shipguys.es.EntityList;
import com.danwink.shipguys.es.EntitySystem;

public class MoveSystem extends EntitySystem
{
	public void update( float d, Entity e, EntityList list )
	{
		PositionComponent pc = (PositionComponent)e.getComponent( "position" );
		MoveComponent mc = (MoveComponent)e.getComponent( "move" );
		UpdateComponent uc = (UpdateComponent)e.getComponent( "update" );
		if( pc != null && mc != null && uc != null )
		{
			if( mc.speedVec.x != 0 || mc.speedVec.y != 0 )
			{
				pc.pos.x += mc.speedVec.x * mc.speed;
				pc.pos.y += mc.speedVec.y * mc.speed;
				uc.update = true;
			}
		}
	}

}
