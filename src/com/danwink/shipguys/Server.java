package com.danwink.shipguys;

import java.util.HashMap;

import com.danwink.shipguys.components.MoveComponent;
import com.danwink.shipguys.entities.Player;
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
	}
	
	public void update( float t )
	{
		while( ns.hasServerMessages() )
		{
			Message m = ns.getNextServerMessage();
			
			System.out.println( m.messageType ); 
			
			switch( m.messageType )
			{
			case "CONNECTED":
			{
				Player p = new Player( m.sender );
				esm.add( p );
				players.put( m.sender, p );
				break;
			}
			case "MOVE_START":
			{
				String dir = (String)m.message;
				if( dir.equals( "UP" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.y = -1; }
				else if( dir.equals( "DOWN" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.y = 1; }
				else if( dir.equals( "LEFT" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.x = -1; }
				else if( dir.equals( "RIGHT" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.x = 1; }
				break;
			}
			case "MOVE_END":
			{
				String dir = (String)m.message;
				if( dir == "UP" ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.y = 0; }
				else if( dir.equals( "DOWN" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.y = 0; }
				else if( dir.equals( "LEFT" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.x = 0; }
				else if( dir.equals( "RIGHT" ) ) { ((MoveComponent)players.get( m.sender ).getComponent( "move" )).speedVec.x = 0; }
				break;
			}
			}
		}
		
		esm.update( t );
		
		if( ucs.toUpdate.size() > 0 )
		{
			System.out.println( "ENTITY_UPDATE_LIST" );
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
