package com.danwink.shipguys.entities;

import com.danwink.shipguys.components.MapComponent;
import com.danwink.shipguys.components.UpdateComponent;
import com.danwink.shipguys.es.Entity;

public class Ship extends Entity 
{
	public Ship() 
	{
		add( new MapComponent( 60, 60 ) );
		add( new UpdateComponent() );
		
		generate();
	}
	
	public void generate()
	{
		MapComponent mc = getComponent( MapComponent.class );
		for( int y = 1; y < mc.height-1; y++ )
		{
			for( int x = 1; x < mc.width-1; x++ )
			{
				mc.map[x][y].passable = true;
			}
		}
	}
}
