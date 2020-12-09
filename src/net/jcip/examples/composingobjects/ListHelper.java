package net.jcip.examples.composingobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

/**
 * 
* @ClassName: ListHelper  
* @Description: 通过客户端加锁来实现“如果没有则添加的功能”
* @author 去恶  
* @date 2020年12月7日
 */
public class ListHelper {
	@NotThreadSafe
	class BadListHelper<E>
	{
		public List<E> list = Collections.synchronizedList(new ArrayList<E>());

		public synchronized boolean putIfAbsent(E x)
		{
			boolean absent = !list.contains(x);
			if (absent)
				list.add(x);
			return absent;
		}
	}
	
	@ThreadSafe
	class GoodListHelper<E>
	{
		public List<E> list = Collections.synchronizedList(new ArrayList<E>());

		public boolean putIfAbsent(E x)
		{
			synchronized (list)
			{
				boolean absent = !list.contains(x);
				if (absent)
					list.add(x);
				return absent;
			}
		}
	}
}
