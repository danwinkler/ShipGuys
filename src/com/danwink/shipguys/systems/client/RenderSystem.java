package com.danwink.shipguys.systems.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.danwink.shipguys.components.MapComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.entities.Player;
import com.danwink.shipguys.entities.Ship;
import com.danwink.shipguys.es.Entity;
import com.danwink.shipguys.es.EntityList;
import com.danwink.shipguys.es.EntitySystem;

public class RenderSystem extends EntitySystem
{
	public static int tileSize = 10;
	
	public void render( Graphics g, Entity e, EntityList list )
	{
		if( e instanceof Player )
		{
			PositionComponent pc = (PositionComponent)e.getComponent( "position" );
			g.setColor( Color.white );
			g.drawOval( pc.pos.x - 5, pc.pos.y - 5, 10, 10 );
		}
		else if( e instanceof Ship )
		{
			MapComponent mc = (MapComponent)e.getComponent( "map" );
			for( int y = 0; y < mc.height; y++ )
			{
				for( int x = 0; x < mc.width; x++ )
				{
					if( !mc.map[x][y].passable )
					{
						g.drawRect( x*tileSize, y*tileSize, tileSize, tileSize );
					}
				}
			}
		}
	}

	public void update( float d, Entity e, EntityList list ) {}
}
