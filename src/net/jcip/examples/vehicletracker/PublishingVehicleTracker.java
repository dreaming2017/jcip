package net.jcip.examples.vehicletracker;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.jcip.annotations.GuardedBy;

/**
 * 
 * @ClassName: PublishingVehicleTracker
 * @Description: 安全发布底层状态的车辆追踪器
 * @author 去恶
 * @date 2020年12月7日
 */
public class PublishingVehicleTracker {
	@GuardedBy("this")
	private final ConcurrentMap<String, SafePoint> locations;
	private final Map<String, SafePoint> unmodifiableMap;

	public PublishingVehicleTracker(Map<String, SafePoint> locations) {
		this.locations = new ConcurrentHashMap<>(locations);
		this.unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	public Map<String, SafePoint> getLocations() {
		return unmodifiableMap;
	}

	public SafePoint getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException("invalid vehicle name: " + id);
		}

		locations.get(id).set(x, y);
	}
}
