package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class MapComponent extends EntityComponent 
{
	Tile[][] map;
	int width;
	int height;
	
	static 
	{
		componentName = "map";
	}
	
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
