package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class MapComponent extends EntityComponent 
{
	{
		componentName = "map";
	}
	
	public Tile[][] map;
	public int width;
	public int height;
	
	public MapComponent()
	{
		
	}
	
	public MapComponent( int width, int height )
	{
		this.width = width;
		this.height = height;
		
		map = new Tile[width][height];
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				map[x][y] = new Tile();
			}
		}
	}
	
	public static class Tile
	{
		public boolean passable;
		
		public Tile()
		{
			
		}
	}
}
