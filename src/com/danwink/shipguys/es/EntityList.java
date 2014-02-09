package com.danwink.shipguys.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityList {
	int nextKey = 0;
	
	ArrayList<Entity> list = new ArrayList<Entity>();
	HashMap<Integer, Entity> map = new HashMap<Integer, Entity>();
	
	public void add( Entity e )
	{
		map.put( e.id, e );
		list.add( e );
	}
	
	public void update( Entity e )
	{
		if( map.containsKey( e.id ) )
		{
			map.get( e.id ).update( e );
		}
		else
		{
			add( e );
		}
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
	
	public void addAll( List<Entity> el )
	{
		for( Entity e : el )
		{
			add( e );
		}
	}
}
