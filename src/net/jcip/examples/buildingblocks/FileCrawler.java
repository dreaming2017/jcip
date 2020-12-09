package net.jcip.examples.buildingblocks;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {

	private final BlockingQueue<File> queue;
	private final FileFilter fileFilter;
	private final File root;
	private final File poison;


	public FileCrawler(BlockingQueue<File> queue, FileFilter fileFilter,
			File root,File poison) {
		this.root = root;
		this.queue = queue;
		this.fileFilter = fileFilter;
		this.poison = poison;
	}


	@Override
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) { /* fall through */
		} finally {
			while (true) {
				try {
					queue.put(poison);
					break;
				} catch (InterruptedException e1) { /* retry */
				}
			}
		}
	}

	private boolean alreadyIndexed(File f) {
		return false;
	}

	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			// 深度优先搜索
			for (File entry : entries) {
				if (entry.isDirectory()) {
					crawl(entry);
				} else if (!alreadyIndexed(entry)) {
					queue.put(entry);
				}

			}
		}
	}

}
