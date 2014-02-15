package com.danwink.shipguys.systems.server;

import com.danwink.shipguys.components.MapComponent;
import com.danwink.shipguys.components.MapComponent.Tile;
import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.components.UpdateComponent;
import com.danwink.shipguys.entities.Ship;
import com.danwink.shipguys.es.Entity;
import com.danwink.shipguys.es.EntityList;
import com.danwink.shipguys.es.EntitySystem;

public class MoveSystem extends EntitySystem
{
	public void update( float d, Entity e, EntityList list )
	{
		PositionComponent pc = e.getComponent( PositionComponent.class );
		MoveComponent mc = e.getComponent( MoveComponent.class );
		UpdateComponent uc = e.getComponent( UpdateComponent.class );
		
		Ship s = (Ship)list.getByName( "ship" );
		MapComponent map = s.getComponent( MapComponent.class );
		
		if( pc != null && mc != null && uc != null )
		{
			if( mc.speedVec.x != 0 || mc.speedVec.y != 0 )
			{
				float futureX = pc.pos.x + mc.speedVec.x * mc.speed;
				float futureY = pc.pos.y + mc.speedVec.y * mc.speed;
				
				Tile tile = map.getTile( map.worldToMapSpace( pc.pos.x, futureY ) );
				if( tile.passable ) {
					pc.pos.y = futureY;
					uc.update = true;
				}
				
				tile = map.getTile( map.worldToMapSpace( futureX, pc.pos.y ) );
				if( tile.passable ) {
					pc.pos.x = futureX;
					uc.update = true;
				}
			}
		}
	}

}
