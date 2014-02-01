package com.danwink.shipguys.es;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityList {
	int nextKey = 0;
	
	ArrayList<Entity> list = new ArrayList<Entity>();
	HashMap<Integer, Entity> map = new HashMap<Integer, Entity>();
	
	public void add( Entity e )
	{
		e.id = nextKey++;
		map.put( e.id, e );
		list.add( e );
	}
	
	public Entity getIndex( int index )
	{
		return list.get( index );
	}
	
	public Entity get( int key )
	{
		return map.get( key );
	}
	
	public int size()
	{
		return list.size();
	}
	
	public void remove( Entity e )
	{
		list.remove( map.remove( e.id ) );
	}
}
