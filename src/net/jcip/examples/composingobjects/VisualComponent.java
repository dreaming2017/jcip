package net.jcip.examples.composingobjects;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
* @ClassName: VisualComponent  
* @Description: 将线程安全性委托给多个状态变量
* @author 去恶  
* @date 2020年12月7日
 */
public class VisualComponent {
	private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
	private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();

	public void addKeyListener(KeyListener listener)
	{
		keyListeners.add(listener);
	}

	public void addMouseListener(MouseListener listener)
	{
		mouseListeners.add(listener);
	}

	public void removeKeyListener(KeyListener listener)
	{
		keyListeners.remove(listener);
	}

	public void removeMouseListener(MouseListener listener)
	{
		mouseListeners.remove(listener);
	}
}
