package net.jcip.examples.resultcache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger>
{
	public BigInteger compute(String arg)
	{
		//模拟耗时操作
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// after deep thought...
		return new BigInteger(arg);
	}
}
