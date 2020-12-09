package net.jcip.examples.resultcache;

interface Computable<A, V>
{
	V compute(A arg) throws InterruptedException;
}
