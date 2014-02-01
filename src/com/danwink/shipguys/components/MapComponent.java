package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class MapComponent extends EntityComponent 
{
	Tile[][] map;
	int width;
	int height;
	
	{
		name = "map";
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
