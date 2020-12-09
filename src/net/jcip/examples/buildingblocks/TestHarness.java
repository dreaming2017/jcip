package net.jcip.examples.buildingblocks;

import java.util.concurrent.CountDownLatch;

/**
 * 
* @ClassName: TestHarness  
* @Description: Using CountDownLatch for starting and stopping threads in timing tests
* @author 去恶  
* @date 2020年12月9日
 */
public class TestHarness {
	public long timeTasks(int nThreads, final Runnable task)
			throws InterruptedException {

		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		for (int i = 0; i < nThreads; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;

	}
}
