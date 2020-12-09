package net.jcip.examples.vehicletracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

/**
 * 
 * @ClassName: MonitorVehicleTracker
 * @Description: Monitor-based vehicle tracker implementation
 * @author 去恶
 * @date 2020年12月7日
 */
public class MonitorVehicleTracker {
	@GuardedBy("this")
	private final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	private static Map<String, MutablePoint> deepCopy(
			Map<String, MutablePoint> m) {
		Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();

		for (String id : m.keySet())
			result.put(id, new MutablePoint(m.get(id)));

		return Collections.unmodifiableMap(result);
	}
	
	public synchronized Map<String, MutablePoint> getLocations()
	{
		return deepCopy(locations);
	}

	public synchronized MutablePoint getLocation(String id)
	{
		MutablePoint loc = locations.get(id);
		return loc == null ? null : new MutablePoint(loc);
	}

	/**
	 * 修改操作
	 * @param id
	 * @param x
	 * @param y
	 */
	public synchronized void setLocation(String id, int x, int y)
	{
		MutablePoint loc = locations.get(id);
		if (loc == null)
			throw new IllegalArgumentException("No such ID: " + id);
		loc.x = x;
		loc.y = y;
	}
}
