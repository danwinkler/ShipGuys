package com.danwink.shipguys;

import java.util.HashMap;

import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.components.PositionComponent;
import com.danwink.shipguys.entities.Player;
import com.danwink.shipguys.entities.Ship;
import com.danwink.shipguys.es.EntitySystemManager;
import com.danwink.shipguys.network.Message;
import com.danwink.shipguys.network.NetworkServer;
import com.danwink.shipguys.systems.server.*;

public class Server
{
	EntitySystemManager esm;
	
	ServerLoop sl;
	Thread t;
	
	NetworkServer ns;
	
	UpdateClientsSystem ucs;
	
	HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	
	public void begin()
	{
		ns = new NetworkServer( StaticFiles.classes );
		
		reset();
		
		sl = new ServerLoop();
		t = new Thread( sl );
		t.start();
	}
	
	public void reset()
	{
		esm = new EntitySystemManager();
		esm.addUpdate( new PlayerSystem() );
		esm.addUpdate( ucs = new UpdateClientsSystem() );
		esm.addUpdate( new MoveSystem() );
		
		
		esm.add( new Ship(), "ship" );
	}
	
	public void update( float t )
	{
		while( ns.hasServerMessages() )
		{
			Message m = ns.getNextServerMessage();
			
			switch( m.messageType )
			{
			case "CONNECTED":
			{
				Player p = new Player( m.sender );  
				p.getComponent( PositionComponent.class ).pos.set( 20, 20 );
				esm.add( p );
				players.put( m.sender, p );
				
				ns.sendToClient( m.sender, new Message( "ENTITY_UPDATE_LIST", esm.list.list ) );
				break;
			}
			case "DISCONNECTED":
			{
				//TODO: handle
				break;
			}
			case "MOVE_START":
			{
				String dir = (String)m.message;
				MoveComponent mc = players.get( m.sender ).getComponent( MoveComponent.class );
				if( dir.equals( "UP" ) ) { mc.speedVec.y = -1; }
				else if( dir.equals( "DOWN" ) ) { mc.speedVec.y = 1; }
				else if( dir.equals( "LEFT" ) ) { mc.speedVec.x = -1; }
				else if( dir.equals( "RIGHT" ) ) { mc.speedVec.x = 1; }
				break;
			}
			case "MOVE_END":
			{
				String dir = (String)m.message;
				MoveComponent mc = players.get( m.sender ).getComponent( MoveComponent.class );
				if( dir.equals( "UP" ) ) { mc.speedVec.y = 0; }
				else if( dir.equals( "DOWN" ) ) { mc.speedVec.y = 0; }
				else if( dir.equals( "LEFT" ) ) { mc.speedVec.x = 0; }
				else if( dir.equals( "RIGHT" ) ) { mc.speedVec.x = 0; }
				break;
			}
			}
		}
		
		esm.update( t );
		
		if( ucs.toUpdate.size() > 0 )
		{
			ns.sendToAllClients( new Message( "ENTITY_UPDATE_LIST", ucs.toUpdate ) );
			ucs.clearList();
		}
	}
	
	public static void main( String[] args )
	{
		Server ts = new Server();
		ts.begin();
	}
	
	public class ServerLoop implements Runnable 
	{
		long lastTime;
		long frameTime = (1000 / 30);
		long timeDiff = frameTime;
		public boolean running = true;
		public ServerLoop()
		{
			
		}

		public void run() 
		{
			lastTime = System.currentTimeMillis();
			while( running )
			{
				try{
					update( timeDiff / 1000.f );
				} catch( Exception ex )
				{
					ex.printStackTrace();
				}
				long time = System.currentTimeMillis();
				timeDiff = (lastTime + frameTime) - time;
				if( timeDiff > 0 )
				{
					try {
						Thread.sleep( timeDiff );
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				lastTime = System.currentTimeMillis();
			}
		}	
	}
}
