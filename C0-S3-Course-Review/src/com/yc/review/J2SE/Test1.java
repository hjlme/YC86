package com.yc.review.J2SE;
/**
 * 递归调用求 阶乘
 * @author 贺井龙
 *
 */
public class Test1 {
	
	public static void main(String[] args) {
		
		System.out.println(test(4));
	}
	
	public static int test(int i) {
		if(i==1) {
			return 1;
		} else {
			return i*test(i-1);
		}
	}
}
