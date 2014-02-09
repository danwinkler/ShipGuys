package com.danwink.shipguys;

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
	
	public void begin()
	{
		ns = new NetworkServer( StaticFiles.classes );
		
		sl = new ServerLoop();
		t = new Thread( sl );
		t.start();
	
		reset();
	}
	
	public void reset()
	{
		esm = new EntitySystemManager();
		esm.addUpdate( new PlayerSystem() );
		esm.addUpdate( ucs = new UpdateClientsSystem() );
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
				esm.add( new Player( m.sender ) );
				break;
			}
		}
		
		esm.update( t );
		
		ns.sendToAllClients( new Message( "ENTITY_UPDATE_LIST", ucs.toUpdate ) );
		
		ucs.clearList();
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
