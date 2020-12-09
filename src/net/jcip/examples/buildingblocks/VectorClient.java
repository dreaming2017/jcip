package net.jcip.examples.buildingblocks;

import java.util.Vector;

/**
 * 
 * @ClassName: VectorClient
 * @Description: 同步容器的问题
 * @author 去恶
 * @date 2020年12月7日
 */
public class VectorClient {
	static class BadVectorClient {

		public static Object getLast(Vector<?> list) {
			int lastIndex = list.size() - 1;
			return list.get(lastIndex);
		}

		public static void deleteLast(Vector<?> list) {
			int lastIndex = list.size() - 1;
			list.remove(lastIndex);
		}

	}

	// 使用客户端加锁保护vector的复合操作
	static class GoodVectorClient {
		public static Object getLast(Vector<?> list) {
			synchronized (list) {
				int lastIndex = list.size() - 1;
				return list.get(lastIndex);
			}

		}

		public static void deleteLast(Vector<?> list) {
			synchronized (list) {
				int lastIndex = list.size() - 1;
				list.remove(lastIndex);
			}

		}
	}

	static class BadVectorClient2 {
		Vector<?> vector = new Vector<>();

		// vector赋值操作
		// ....
		public void doSomething() {

			for (int i = 0; i < vector.size(); i++) {
				// doSomething(vector.get(i));
			}
		}
	}

	static class GoodVectorClient2 {
		Vector<?> vector = new Vector<>();

		// vector赋值操作
		// ....
		public void doSomething() {
			synchronized (vector) {

				for (int i = 0; i < vector.size(); i++) {
					// doSomething(vector.get(i));
				}
			}
		}
	}
}
