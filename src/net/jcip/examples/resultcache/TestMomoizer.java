package net.jcip.examples.resultcache;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Memoizer1 花费时间为:6021毫秒
 * Memoizer2 花费时间为:2008毫秒
 * Memoizer3 花费时间为:2015毫秒
 * 
* @ClassName: TestMomoizer  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author 去恶  
* @date 2020年12月9日
 */
public class TestMomoizer {
	public static void main(String[] args) throws Exception {
		int N = 10;
		long start = System.currentTimeMillis();
		final CountDownLatch cdl = new CountDownLatch(N);
	/*	Memoizer1<String, BigInteger> memoizer = new Memoizer1<>(
				new ExpensiveFunction());*/
		/*Memoizer2<String, BigInteger> memoizer = new Memoizer2<>(
				new ExpensiveFunction());*/
		
		Memoizer3<String, BigInteger> memoizer = new Memoizer3<>(
				new ExpensiveFunction());
		for (int i = 0; i < N; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						String arg = String.valueOf(new Random().nextInt(3));
						BigInteger value = memoizer.compute(arg);
						System.out.println("threadName= " +Thread.currentThread().getName() + " -->{arg=" + arg + ",value= " + value + "}");
						cdl.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("花费时间为:" + (end - start) + "毫秒");

	}
}

