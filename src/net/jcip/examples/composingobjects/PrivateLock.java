package net.jcip.examples.composingobjects;

import net.jcip.annotations.GuardedBy;

/**
 * 
* @ClassName: PrivateLock  
* @Description: Guarding state with a private lock
* @author 去恶  
* @date 2020年12月7日
 */
public class PrivateLock
{
	private final Object myLock = new Object();
	@GuardedBy("myLock")
	Widget widget;

	void someMethod()
	{
		synchronized (myLock)
		{
			// Access or modify the state of widget
		}
	}
	
	private class Widget{}
}
