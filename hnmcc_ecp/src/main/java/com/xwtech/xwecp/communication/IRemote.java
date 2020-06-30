package com.xwtech.xwecp.communication;



public interface IRemote
{
	Object callRemote(IStreamableMessage request) throws CommunicateException;
//	Object callJsonRemote(IStreamableMessage request) throws CommunicateException;
}
