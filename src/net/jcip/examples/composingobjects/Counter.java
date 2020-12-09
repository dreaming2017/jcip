package net.jcip.examples.composingobjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 
 * @ClassName: Counter
 * @Description: Simple thread-safe counter using the Java monitor pattern
 * @author 去恶
 * @date 2020年12月7日
 */
@ThreadSafe
public class Counter {
	
	@GuardedBy("this")
	private long value = 0;

	public synchronized long getValue() {
		return value;
	}

	public synchronized long increment() {
		if (value == Long.MAX_VALUE)
			throw new IllegalStateException("counter overflow");
		return ++value;
	}

}
