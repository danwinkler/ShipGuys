package com.danwink.shipguys.components;

import javax.vecmath.Point2f;

import com.danwink.shipguys.es.EntityComponent;

public class PositionComponent extends EntityComponent
{
	static 
	{
		componentName = "position";
	}
	
	public Point2f pos = new Point2f();
}
