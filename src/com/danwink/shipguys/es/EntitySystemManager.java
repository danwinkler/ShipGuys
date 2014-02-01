package com.danwink.shipguys.es;

import java.util.ArrayList;

public class EntitySystemManager<E>
{
	EntityList list = new EntityList();
	ArrayList<EntitySystem<E>> systems = new ArrayList<EntitySystem<E>>();
	
	public void update( float d, E e )
	{
		for( EntitySystem<E> es : systems )
		{
			es.update( d, list, e );
		}
	}
}
