package com.danwink.shipguys.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class NetworkServer 
{
	Server server;
	LinkedList<Message> messages = new LinkedList<Message>();
	HashMap<Integer, Connection> connections = new HashMap<Integer, Connection>();
	ArrayList<Connection> connectionsArr = new ArrayList<Connection>();
	ServerListener sl;
	
	@SuppressWarnings( "rawtypes" )
	public NetworkServer( Class[] classes )
	{
		server = new Server( 512000, 32000 );
		for( Class c : classes ) { server.getKryo().register( c ); }
		server.getKryo().register( Message.class );
		server.start();
		try {
			server.bind( 54555, 54777 );
		} catch (IOException e) {
			e.printStackTrace();
		}
		sl = new ServerListener();
		server.addListener( sl );
	}
	
	public void sendToClient( int id, Message m )
	{
		synchronized( messages )
		{
			Connection c = connections.get( id );
			if( c != null )
			{
				c.sendTCP( m );
			}
		}
	}

	public void sendToAllClients( Message m ) 
	{
		synchronized( connectionsArr )
		{
			for( int i = 0; i < connectionsArr.size(); i++ )
			{
				if( connectionsArr.get( i ) == null ) break;
				connectionsArr.get( i ).sendTCP( m );
			}
		}
	}

	public Message getNextServerMessage()
	{
		synchronized( messages )
		{
			return messages.pop();
		}
	}

	public boolean hasServerMessages() 
	{
		return !messages.isEmpty();
	}
	
	class ServerListener extends Listener
	{
		public void received( Connection c, Object o ) 
		{
			if( connections.get( c.getID() ) == null )
			{
				connections.put( c.getID(), c );
				connectionsArr.add( c );
			}
			if( o instanceof Message )
			{
				synchronized( messages )
				{
					Message m = (Message)o;
					m.sender = c.getID();
					messages.push( m );
				}
			}
		}
		
		public void connected( Connection c )
		{
			synchronized( messages )
			{
				synchronized( connectionsArr )
				{
					Message m = new Message();
					m.message = c.getID();
					m.sender = c.getID();
					m.messageType = "CONNECTED";
					messages.push( m );
					if( connections.get( c.getID() ) == null )
					{
						connections.put( c.getID(), c );
						connectionsArr.add( c );
					}
				}
			}
		}
		
		public void disconnected( Connection c )
		{
			synchronized( messages )
			{
				synchronized( connectionsArr )
				{
					Message m = new Message();
					m.message = c.getID();
					m.sender = c.getID();
					m.messageType = "DISCONNECTED";
					messages.push( m );
					connections.remove( c.getID() );
					connectionsArr.remove( c );
				}
			}
		}
	}
	
	public void stop()
	{
		server.close();
	}
}