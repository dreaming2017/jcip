package net.jcip.examples.composingobjects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @ClassName: NumberRange
 * @Description: NumberRange类并不足以保护它的不变性约束条件
 * @author 去恶
 * @date 2020年12月7日
 */
public class NumberRange {
	// INVARIANT: lower <= upper
	private final AtomicInteger lower = new AtomicInteger(0);
	private final AtomicInteger upper = new AtomicInteger(0);

	public void setLower(int i) {
		// Warning -- unsafe check-then-act
		// 注意 -- 不安全的先检查后执行
		if (i > upper.get())
			throw new IllegalArgumentException("can't set lower to " + i
					+ " > upper");
		lower.set(i);
	}

	public void setUpper(int i) {
		// Warning -- unsafe check-then-act
		if (i < lower.get())
			throw new IllegalArgumentException("can't set upper to " + i
					+ " < lower");
		upper.set(i);
	}

	public boolean isInRange(int i) {
		return (i >= lower.get() && i <= upper.get());
	}
}
