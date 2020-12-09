package net.jcip.examples.resultcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
* @ClassName: Memoizer2  
* @Description: 重复计算问题
* @author 去恶  
* @date 2020年12月9日  
* @param <A>
* @param <V>
 */
public class Memoizer2<A, V> implements Computable<A, V>
{
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> c;

	public Memoizer2(Computable<A, V> c)
	{
		this.c = c;
	}

	public V compute(A arg) throws InterruptedException
	{
		V result = cache.get(arg);
		if (result == null)
		{
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
