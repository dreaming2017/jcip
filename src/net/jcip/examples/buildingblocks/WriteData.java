package net.jcip.examples.buildingblocks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟多个线程写操作:
 * 运行一遍可以看到所有的结果
 * 1 每个写入线程执行完写数据操作之后，就在等待其他线程写入操作完毕
 * 2 当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable
 * 3 
 */
public class WriteData {
	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N,new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始执行合并操作...");
				//模拟合并操作
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 System.out.println("合并操作完成,当前线程"+Thread.currentThread().getName());
				
			}
		});
		for (int i = 0; i < N; i++){
			new Writer(barrier).start();
		}
		
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println("CyclicBarrier重用");
         
        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }
			
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
	}
}
