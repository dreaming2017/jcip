package net.jcip.examples.composingobjects;

import java.util.Vector;

import net.jcip.annotations.ThreadSafe;

/**
 * 
* @ClassName: BetterVector  
* @Description: 通过继承Vector，并添加"若没有，则添加的功能"
* @author 去恶  
* @date 2020年12月7日  
* @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E>{
	// When extending a serializable class, you should redefine serialVersionUID
	private static final long serialVersionUID = -3963416950630760754L;
	
	public synchronized boolean putIfAbsent(E x)
	{
		boolean absent = !contains(x);
		if (absent)
			add(x);
		return absent;
	}
}
