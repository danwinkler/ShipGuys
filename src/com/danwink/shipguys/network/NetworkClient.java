package com.danwink.shipguys.network;

import java.io.IOException;
import java.util.LinkedList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkClient
{
	public Client client;
	public LinkedList<Message> messages = new LinkedList<Message>();
	
	@SuppressWarnings( "rawtypes" )
	public NetworkClient( String address, Class[] classes ) throws IOException
	{
		client = new Client( 128000, 32000 );
		for( Class c : classes ) { client.getKryo().register( c ); }
		client.getKryo().register( Message.class );
		client.start();
		client.connect( 2500, address, 54555, 54777 );
		
		
		client.addListener( new Listener() {
			public void received( Connection c, Object o ) 
			{
				if( o instanceof Message )
				{
					synchronized( messages )
					{
						messages.push( (Message)o );
					}
				}
			}
			
			public void disconnected( Connection c )
			{
				
			}
		});
	}

	public void sendToServer( Message m ) 
	{
		client.sendTCP( m );
	}

	public Message getNextClientMessage()
	{
		synchronized( messages )
		{
			return messages.pop();
		}
	}

	public boolean hasClientMessages() 
	{
		return !messages.isEmpty();
	}

	public void stop()
	{
		client.close();
	}
	
	public String getServerAddr()
	{
		return client.getRemoteAddressTCP().getHostName();
	}
}
