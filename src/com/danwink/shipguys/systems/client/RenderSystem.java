package com.danwink.shipguys.systems.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.es.Entity;
import com.danwink.shipguys.es.EntityList;
import com.danwink.shipguys.es.EntitySystem;

public class RenderSystem extends EntitySystem
{
	public void render( Graphics g, Entity e, EntityList list )
	{
		PositionComponent pc = (PositionComponent)e.getComponent( "position" );
		g.setColor( Color.white );
		g.drawOval( pc.pos.x - 5, pc.pos.y - 5, 10, 10 );
	}

	public void update( float d, Entity e, EntityList list ) {}
}
