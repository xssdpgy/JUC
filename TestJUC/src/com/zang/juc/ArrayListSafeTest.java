package com.zang.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListSafeTest {

	public static void main(String[] args) throws InterruptedException {

		final List<Integer> list = new ArrayList<Integer>();


		// 线程A将1-1000添加到列表
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i < 1000; i++) {
					list.add(i);

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();

		// 线程B将1001-2000添加到列表
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1001; i < 2000; i++) {
					list.add(i);

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();

		Thread.sleep(1000);

		// 打印所有结果
		for (int i = 0; i < list.size(); i++) {
			System.out.println("第" + (i + 1) + "个元素为：" + list.get(i));
		}
	}
}
