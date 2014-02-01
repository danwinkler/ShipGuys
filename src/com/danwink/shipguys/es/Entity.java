package com.danwink.shipguys.es;

import java.util.HashMap;

public abstract class Entity 
{
	HashMap<String, EntityComponent> components = new HashMap<String, EntityComponent>();
	
	public int id = -1;
	
	public void add( EntityComponent gc )
	{
		components.put( gc.name, gc );
	}
}
