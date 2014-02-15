package com.danwink.shipguys.components;

import javax.vecmath.Vector2f;

import com.danwink.shipguys.es.EntityComponent;

public class MoveComponent extends EntityComponent 
{
	public Vector2f speedVec = new Vector2f();
	public float speed = 3;
}
