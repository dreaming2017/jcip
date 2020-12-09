package net.jcip.examples.composingobjects;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;

/**
* @ClassName: PersonSet  
* @Description: Using confinement to ensure thread safety
* @author 去恶  
* @date 2020年12月7日
 */
public class PersonSet {
	@GuardedBy("this")
	private final Set<Person> mySet = new HashSet<Person>();
	
	public synchronized void addPerson(Person p)
	{
		mySet.add(p);
	}

	public synchronized boolean containsPerson(Person p)
	{
		return mySet.contains(p);
	}
	
	interface Person
	{
	}
}
