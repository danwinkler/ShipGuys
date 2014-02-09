package com.danwink.shipguys.es;

import org.newdawn.slick.Graphics;

public abstract class EntitySystem
{
	public abstract void update( float d, Entity e, EntityList list );
	
	public void render( Graphics g, Entity e, EntityList list )
	{
		
	}
}
