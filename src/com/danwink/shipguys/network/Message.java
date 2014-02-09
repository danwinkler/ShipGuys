package com.danwink.shipguys.network;

public class Message
{
	public int sender = -1;
	public String messageType;
	public Object message;
	
	public Message()
	{
		
	}
	
	public Message( String messageType, Object message )
	{
		this.messageType = messageType;
		this.message = message;
	}
}

