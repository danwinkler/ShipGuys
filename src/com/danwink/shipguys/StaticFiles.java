package com.danwink.shipguys;

import java.util.ArrayList;
import java.util.HashMap;

import javax.vecmath.Point2f;
import javax.vecmath.Point2i;
import javax.vecmath.Vector2f;

import com.danwink.shipguys.components.*;
import com.danwink.shipguys.entities.*;
import com.danwink.shipguys.es.Entity;
import com.phyloa.dlib.util.DOptions;

public class StaticFiles 
{
	public static DOptions options = new DOptions( "options.txt" );
	
	@SuppressWarnings( "rawtypes" )
	public static Class[] classes = {
		String.class,
		ArrayList.class,
		HashMap.class,
		
		Point2f.class,
		Vector2f.class,
		Point2i.class,
		
		Entity.class,
		
		Player.class,
		Ship.class,
		
		MapComponent.class, MapComponent.Tile.class, MapComponent.Tile[].class, MapComponent.Tile[][].class,
		MoveComponent.class,
		PositionComponent.class,
		UpdateComponent.class,
		PlayerComponent.class,
	};
}
