package net.jcip.examples.vehicletracker;

import net.jcip.annotations.Immutable;

/**
 * 
* @ClassName: Point  
* @Description: Immutable Point class used by DelegatingVehicleTracker
* @author 去恶  
* @date 2020年12月7日
 */
@Immutable
public class Point
{
	public final int x, y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
