package net.jcip.examples.resultcache;

import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

/**
 * 
* @ClassName: Memoizer1  
* @Description:  Initial cache attempt using HashMap and synchronization
* @author 去恶  
* @date 2020年12月9日  
* @param <A>
* @param <V>
 */
public class Memoizer1<A, V> implements Computable<A, V>{
	@GuardedBy("this")
	private final Map<A, V> cache = new HashMap<A, V>();
	
	private final Computable<A, V> c;

	public Memoizer1(Computable<A, V> c)
	{
		this.c = c;
	}

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result==null){
			 result = c.compute(arg);
			 cache.put(arg, result);
		}
		
		return result;
	}
}
