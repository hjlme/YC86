package Thread.D0725;


import java.util.Scanner;

public class ThreadTest1 {
	public static void main(String[] args) {
		A a = new A("A线程");
		B b = new B();
		Thread t = new Thread(b,"B线程");
		a.start();
		t.start();
		System.out.println("main getName:"+Thread.currentThread().getName());
		System.out.println("main getId:"+Thread.currentThread().getId());
		System.out.println("main getPriority:"+Thread.currentThread().getPriority());
		System.out.println("main getState:"+Thread.currentThread().getState());
	}
	
	public static void a() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入:");
		String s = sc.nextLine();
		System.out.println(s);
		sc.close();
	}
	
	public static void b() {
		System.out.println("b方法");
	}
	
	//内部类
	public static class A extends Thread {
		public A(String name){
			super(name);
		}
		public void run() {
			a();
			System.out.println("a getName:"+Thread.currentThread().getName());
			System.out.println("a getId:"+Thread.currentThread().getId());
			System.out.println("a getPriority:"+Thread.currentThread().getPriority());
			System.out.println("a getState:"+Thread.currentThread().getState());
		}
	}
	
	//内部类
	public static class B implements Runnable {
		@Override
		public void run() {
			System.out.println("b()休眠10秒");
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b();
			System.out.println("b getName:"+Thread.currentThread().getName());
			System.out.println("b getId:"+Thread.currentThread().getId());
			System.out.println("b getPriority:"+Thread.currentThread().getPriority());
			System.out.println("b getState:"+Thread.currentThread().getState());
		}
		
	}
}
