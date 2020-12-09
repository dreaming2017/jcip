package net.jcip.examples.buildingblocks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import net.jcip.examples.util.LaunderThrowable;
//使用FutureTask来提前加载稍后需要的数据
public class Preloader {
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(
			new Callable<ProductInfo>() {

				@Override
				public ProductInfo call() throws Exception {
					return loadProductInfo();
				}
			});

	ProductInfo loadProductInfo() throws DataLoadException {
		return null;
	}

	private final Thread thread = new Thread(future);

	/**
	 * 启动
	 */
	public void start() {
		thread.start();
	}

	/**
	 * 
	 * @return 获取结果
	 * @throws DataLoadException
	 * @throws InterruptedException
	 */
	public ProductInfo get() throws DataLoadException, InterruptedException {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException) {
				throw (DataLoadException) cause;
			}

			else {
				throw LaunderThrowable.launderThrowable(cause);
			}

		}
	}

	interface ProductInfo {
	}
}

class DataLoadException extends Exception {
}
