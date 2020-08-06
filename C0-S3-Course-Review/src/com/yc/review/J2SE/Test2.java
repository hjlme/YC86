package com.yc.review.J2SE;
/**
 * 用递归写斐波那契数列
 * @author 贺井龙
 *
 */
public class Test2 {
	
	public static void main(String[] args) {
		System.out.println(fib(4));
	}
	
	public static int fib(int i) {
		
		if(i==1) {
			return 1;
		} else if(i==2) {
			return 1;
		} else {
			return fib(i-1)+fib(i-2);
		}
	}
}
