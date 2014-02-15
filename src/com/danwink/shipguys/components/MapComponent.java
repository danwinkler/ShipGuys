package com.danwink.shipguys.components;

import javax.vecmath.Point2i;

import com.danwink.shipguys.es.EntityComponent;

public class MapComponent extends EntityComponent 
{
	public Tile[][] map;
	public int width;
	public int height;
	public float tileSize = 10;
	
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
	
	public Point2i worldToMapSpace( float x, float y )
	{
		return new Point2i( (int)( x/tileSize ), (int)( y/tileSize ) );
	}
	
	public Tile getTile( int x, int y )
	{
		return map[x][y];
	}
	
	public Tile getTile( Point2i p )
	{
		return getTile( p.x, p.y );
	}
	
	public static class Tile
	{
		public boolean passable;
		
		public Tile()
		{
			
		}
	}
}
