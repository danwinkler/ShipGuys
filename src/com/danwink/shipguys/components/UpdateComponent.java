package com.danwink.shipguys.components;

import com.danwink.shipguys.es.EntityComponent;

public class UpdateComponent extends EntityComponent
{
	static 
	{
		componentName = "update";
	}
	
	public boolean update = true;
}
