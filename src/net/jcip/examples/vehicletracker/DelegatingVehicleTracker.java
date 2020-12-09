package net.jcip.examples.vehicletracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.jcip.annotations.GuardedBy;

/**
 * 
* @ClassName: DelegatingVehicleTracker  
* @Description: 基于委托的车辆追踪
* @author 去恶  
* @date 2020年12月7日
 */
public class DelegatingVehicleTracker {
	@GuardedBy("this")
	private final ConcurrentMap<String, Point> locations;
	private final Map<String, Point> unmodifiableMap;

	public DelegatingVehicleTracker(Map<String, Point> points) {
		this.locations = new ConcurrentHashMap<String, Point>(points);
		this.unmodifiableMap = Collections.unmodifiableMap(locations);
	}


	
	public Map<String, Point> getLocations()
	{
		return unmodifiableMap;
	}

	public Point getLocation(String id)
	{
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y)
	{
		if (locations.replace(id, new Point(x, y)) == null)
			throw new IllegalArgumentException("invalid vehicle name: " + id);
	}

	// Alternate version of getLocations (Listing 4.8)
	public Map<String, Point> getLocationsAsStatic()
	{
		return Collections
				.unmodifiableMap(new HashMap<String, Point>(locations));
	}
}
