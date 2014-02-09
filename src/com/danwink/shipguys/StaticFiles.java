package com.danwink.shipguys;

import java.util.ArrayList;

import javax.vecmath.Point2f;

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
		
		Point2f.class,
		
		Entity.class,
		
		Player.class,
		Ship.class,
		
		MapComponent.class,
		MapComponent.Tile.class,
		MoveComponent.class,
		PositionComponent.class,
		UpdateComponent.class
	};
}
