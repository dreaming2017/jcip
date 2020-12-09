package net.jcip.examples.buildingblocks;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IndexingService {
	private static final int CAPACITY = 1000;
	private  final BlockingQueue<File> queue;
	private final FileFilter fileFilter;
	private static final int N_CONSUMERS = 10;
//	private final File root;
	private static final File POISON = new File("");
	
	public IndexingService(/*File root,*/ final FileFilter fileFilter)
	{
//		this.root = root;
		this.queue = new LinkedBlockingQueue<>(CAPACITY);
		this.fileFilter = new FileFilter()
		{
			public boolean accept(File f)
			{
				return f.isDirectory() || fileFilter.accept(f);
			}
		};
	}
	
	public   void startIndexing(File[] roots,FileFilter fileFilter)
	{
		new IndexingService(fileFilter);
		for(File root:roots){
			new Thread(new FileCrawler(queue, fileFilter, root, POISON)).start();
		}
		for(int i=0;i<N_CONSUMERS;i++){
			new Thread(new Indexer(queue, POISON)).start();
		}
	}

	public static void main(String[] args) {
		//startIndexing(); 
	}
}
