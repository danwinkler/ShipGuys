package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class MapComponent extends EntityComponent 
{
	{
		componentName = "map";
	}
	
	Tile[][] map;
	int width;
	int height;
	
	public MapComponent( int width, int height )
	{
		this.width = width;
		this.height = height;
	}
	
	public class Tile
	{
		boolean passable;
	}
}
