package com.danwink.shipguys.entities;

import com.danwink.shipguys.components.MapComponent;
import com.danwink.shipguys.es.Entity;

public class Ship extends Entity 
{
	public Ship() 
	{
		add( new MapComponent( 60, 60 ) );
	}
}
