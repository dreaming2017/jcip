package net.jcip.examples.buildingblocks;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
	private final BlockingQueue<File> queue;
	private final File poison;

	public Indexer(BlockingQueue<File> queue, File poison) {
		this.queue = queue;
		this.poison = poison;
	}

	public void run() {
		try {
			while (true) {
				File file = queue.take();
				if (file == poison) {
					break;
				} else {
					indexFile(file);
				}

			}
		} catch (InterruptedException consumed) {
		}
	}

	public void indexFile(File file) {
		/* ... */
	};
}
