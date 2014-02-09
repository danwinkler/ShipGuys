package com.danwink.shipguys.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.danwink.shipguys.Client;
import com.danwink.shipguys.es.*;
import com.danwink.shipguys.network.Message;
import com.danwink.shipguys.systems.client.*;
import com.phyloa.dlib.renderer.DScreen;
import com.phyloa.dlib.renderer.DScreenHandler;

public class PlayState extends DScreen<GameContainer, Graphics>
{
	Client c;
	EntitySystemManager esm;
	
	@SuppressWarnings( "unchecked" )
	public void update( GameContainer gc, int delta )
	{
		while( c.nc.hasClientMessages() )
		{
			Message m = c.nc.getNextClientMessage();
			
			switch( m.messageType )
			{
			case "ENTITY_UPDATE_LIST":
			{
				ArrayList<Entity> el = (ArrayList<Entity>)m.message;
				esm.updateEntities( el );
				break;
			}
			}
		}
	}

	public void render( GameContainer gc, Graphics g )
	{
		esm.render( g );
	}

	public void onActivate( GameContainer gc, DScreenHandler<GameContainer, Graphics> dsh )
	{
		c = Client.get();
		c.nc.sendToServer( new Message( "hello", "hello" ) );
		
		resetGame();
	}
	
	public void resetGame()
	{
		esm = new EntitySystemManager();
		esm.addRender( new RenderSystem() );
	}

	public void onExit()
	{
		
	}

	public void message( Object o )
	{
		
	}	
}
