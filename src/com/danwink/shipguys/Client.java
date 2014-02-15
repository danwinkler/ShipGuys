package com.danwink.shipguys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.danwink.shipguys.network.NetworkClient;
import com.danwink.shipguys.states.PlayState;
import com.phyloa.dlib.renderer.DScreenHandler;

public class Client extends BasicGame
{
	static Client c;
	
	public static Client get() 
	{
		if( c == null )
		{
			c = new Client();
		}
		return c;
	}
	
	public NetworkClient nc;
	DScreenHandler<GameContainer, Graphics> dsh = new DScreenHandler<GameContainer, Graphics>();
	
	public Client() 
	{
		super( "ShipGuys" );
	}

	public void render( GameContainer gc, Graphics g ) throws SlickException 
	{
		dsh.render( gc, g );
	}

	public void init( GameContainer gc ) throws SlickException 
	{
		dsh.register( "play", new PlayState() );
		
		try
		{
			nc = new NetworkClient( "127.0.0.1", StaticFiles.classes );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
		dsh.activate( "play", gc );
	}

	public void update( GameContainer gc, int t ) throws SlickException 
	{
		dsh.update( gc, t );
	}
	
	public static void main( String[] args )
	{
		try
		{
			//Attempt to avoid sealed exception errors on zoe's mac
			Class.forName( "javax.vecmath.Point2i" );
			
			AppGameContainer app = new AppGameContainer( Client.get() );
			app.setMultiSample( StaticFiles.options.getI( "multisample" ) );
			app.setDisplayMode( StaticFiles.options.getI( "windowWidth" ), StaticFiles.options.getI( "windowHeight" ), StaticFiles.options.getB( "fullscreen" ) );
			app.setVSync( StaticFiles.options.getB( "vsync" ) );
			app.setUpdateOnlyWhenVisible( false );
			app.setAlwaysRender( true );
			app.start(); 
		} catch( Exception ex )
		{
			ex.printStackTrace();
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter( new FileOutputStream( new File( "tmp/error.log" ), true ) );
			} catch (FileNotFoundException e) {
				System.exit( 0 );
				e.printStackTrace();
			}
			pw.append( System.currentTimeMillis() + "\n" );
			ex.printStackTrace( pw );
			
			pw.flush();
			pw.close();
			
			System.exit( 1 );
		}
	}
}
