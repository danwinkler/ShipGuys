package com.danwink.shipguys.systems.server;

import java.util.ArrayList;

import com.danwink.shipguys.components.UpdateComponent;
import com.danwink.shipguys.es.Entity;
import com.danwink.shipguys.es.EntityList;
import com.danwink.shipguys.es.EntitySystem;

public class UpdateClientsSystem extends EntitySystem
{
	public ArrayList<Entity> toUpdate = new ArrayList<Entity>();
	
	public void update( float d, Entity e, EntityList list )
	{
		UpdateComponent uc = (UpdateComponent)e.getComponent( "update" );
		if( uc != null )
		{
			if( uc.update )
			{
				toUpdate.add( e );
				uc.update = false;
			}
		}
	}

	public void clearList()
	{
		toUpdate = new ArrayList<Entity>();
	}	
}
