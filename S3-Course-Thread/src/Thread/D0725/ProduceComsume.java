package Thread.D0725;

import java.util.ArrayList;
import java.util.List;

/*
 * 		生产消费模式
 */
public class ProduceComsume {
	
	private List<Integer> list = new ArrayList<>();
	
	public synchronized void produce() throws InterruptedException {
		
		while(true) {
			if(list.isEmpty()) {
				for (int i = 0; i < 10; i++) {
					list.add(i);
					System.out.println("生产了一个产品"+i);
					Thread.sleep(200);
				}
			} else {
				/*
				 * 如果list不为空
				 */
				//通知等待线程（消费线程）
				notify(); 
				//当前线程等待，一旦进入等待状态，那么会释放锁对象
				wait(); 
			}
		}
	}
	
	public synchronized void comsume() throws InterruptedException {
		while(true) {
			if(list.isEmpty() == false) {
				for (int i = 0; i < 10; i++) {
					list.remove(0);
					System.out.println("消费了一个产品"+i);
					Thread.sleep(200);
				}
			} else {
				/*
				 * 如果list不为空
				 */
				//通知等待线程（生产线程）
				notify();
				//当前线程等待，一旦进入等待状态，那么会释放锁对象
				wait();
			}
		}
	}
	public static void main(String[] args) {
		ProduceComsume pc = new ProduceComsume();
		new Thread() {
			public void run() {
				try {
					pc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				try {
					pc.comsume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
