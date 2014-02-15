package com.danwink.shipguys.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

import com.danwink.shipguys.Client;
import com.danwink.shipguys.es.*;
import com.danwink.shipguys.network.Message;
import com.danwink.shipguys.systems.client.*;
import com.phyloa.dlib.renderer.DScreen;
import com.phyloa.dlib.renderer.DScreenHandler;

public class PlayState extends DScreen<GameContainer, Graphics> implements InputListener
{
	Client c;
	EntitySystemManager esm;
	
	Input input;
	
	@SuppressWarnings( "unchecked" )
	public void update( GameContainer gc, int delta )
	{
		float d = delta/1000.f;
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
		esm.update( d );
	}

	public void render( GameContainer gc, Graphics g )
	{
		esm.render( g );
	}

	public void onActivate( GameContainer gc, DScreenHandler<GameContainer, Graphics> dsh )
	{
		input = gc.getInput();
		input.addListener( this );
		
		c = Client.get();
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
	
	public void keyPressed( int keyCode, char arg1 )
	{
		if( keyCode == Input.KEY_W )
		{
			c.nc.sendToServer( new Message( "MOVE_START", "UP" ) );
		}
		if( keyCode == Input.KEY_S )
		{
			c.nc.sendToServer( new Message( "MOVE_START", "DOWN" ) );
		}
		if( keyCode == Input.KEY_A )
		{
			c.nc.sendToServer( new Message( "MOVE_START", "LEFT" ) );
		}
		if( keyCode == Input.KEY_D )
		{
			c.nc.sendToServer( new Message( "MOVE_START", "RIGHT" ) );
		}
	}

	public void keyReleased( int keyCode, char arg1 )
	{
		if( keyCode == Input.KEY_W )
		{
			c.nc.sendToServer( new Message( "MOVE_END", "UP" ) );
		}
		if( keyCode == Input.KEY_S )
		{
			c.nc.sendToServer( new Message( "MOVE_END", "DOWN" ) );
		}
		if( keyCode == Input.KEY_A )
		{
			c.nc.sendToServer( new Message( "MOVE_END", "LEFT" ) );
		}
		if( keyCode == Input.KEY_D )
		{
			c.nc.sendToServer( new Message( "MOVE_END", "RIGHT" ) );
		}
	}

	public void mouseClicked( int arg0, int arg1, int arg2, int arg3 )
	{
		
	}

	public void mouseDragged( int arg0, int arg1, int arg2, int arg3 )
	{
		
	}

	public void mouseMoved( int arg0, int arg1, int arg2, int arg3 )
	{
		
	}

	public void mousePressed( int arg0, int arg1, int arg2 )
	{
		
	}

	public void mouseReleased( int arg0, int arg1, int arg2 )
	{
		
	}

	public void mouseWheelMoved( int arg0 )
	{
		
	}

	public void inputEnded()
	{
		
	}

	public void inputStarted()
	{
		
	}

	public boolean isAcceptingInput()
	{
		return true;
	}

	public void setInput( Input arg0 )
	{
		
	}

	public void controllerButtonPressed( int arg0, int arg1 )
	{
		
	}

	@Override
	public void controllerButtonReleased( int arg0, int arg1 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased( int arg0 )
	{
		// TODO Auto-generated method stub
		
	}	
}
