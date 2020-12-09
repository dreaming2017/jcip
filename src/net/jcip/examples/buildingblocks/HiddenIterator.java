package net.jcip.examples.buildingblocks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.jcip.annotations.GuardedBy;

/**
 * 
* @ClassName: HiddenIterator  
* @Description: 隐藏在字符串拼接中的迭代操作  
* @author 去恶  
* @date 2020年12月7日
 */
public class HiddenIterator {
	@GuardedBy("this")
	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i)
	{
		set.add(i);
	}

	public synchronized void remove(Integer i)
	{
		set.remove(i);
	}

	public void addTenThings()
	{
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			add(r.nextInt());
		System.out.println("DEBUG: added ten elements to " + set);
	}
}
