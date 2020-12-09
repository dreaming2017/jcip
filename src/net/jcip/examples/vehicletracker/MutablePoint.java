package net.jcip.examples.vehicletracker;

import net.jcip.annotations.NotThreadSafe;

/**
 * 
* @ClassName: MutablePoint  
* @Description:与java.awt.Point类似的可变的Point的类
* @author 去恶  
* @date 2020年12月7日
 */
@NotThreadSafe
public class MutablePoint
{
	public int x, y;

	public MutablePoint()
	{
		x = 0;
		y = 0;
	}

	public MutablePoint(MutablePoint p)
	{
		this.x = p.x;
		this.y = p.y;
	}
}
