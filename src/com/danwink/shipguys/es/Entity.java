package com.danwink.shipguys.es;

import java.util.HashMap;

public abstract class Entity 
{
	static int nextId = 0;
	
	HashMap<String, EntityComponent> components = new HashMap<String, EntityComponent>();
	
	public int id = nextId++;
	
	@SuppressWarnings( "static-access" )
	public void add( EntityComponent gc )
	{
		components.put( gc.componentName, gc );
	}

	public EntityComponent getComponent( String name )
	{
		return components.get( name );
	}

	public void update( Entity e )
	{
		this.components = e.components;
	}
}
