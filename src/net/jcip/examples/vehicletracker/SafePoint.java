package net.jcip.examples.vehicletracker;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * get：同时获取x和y的值
* @ClassName: SafePoint  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author 去恶  
* @date 2020年12月7日
 */
@ThreadSafe
public class SafePoint {

	@GuardedBy("this")
	private int x, y;

	private SafePoint(int[] a)
	{
		this(a[0], a[1]);
	}

	public SafePoint(SafePoint p)
	{
		this(p.get());
	}

	public SafePoint(int x, int y)
	{
		this.set(x, y);
	}
	
	public synchronized int[] get()
	{
		return new int[] { x, y };
	}
	
	public synchronized void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
