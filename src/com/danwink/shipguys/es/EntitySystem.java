package com.danwink.shipguys.es;

public abstract class EntitySystem<E>
{
	public abstract void update( float d, EntityList list, E e );
}
