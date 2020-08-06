package com.yc.review.J2SE;
/**
 * 判断回文改进版
 * @author 贺井龙
 *
 */
public class Test4 {

	public static void main(String[] args) {
		System.out.println("adcdedcba是回文吗"+isP("abcdedcba"));
	}
	
	private static boolean judge(String s, int low, int high) {
		
		if(high<low) {
			return true;
		} else if(s.charAt(low)!=s.charAt(high)) {
			return false;
		} else {
			return judge(s,low+1,high-1);
		}
		
	}
	
	public static boolean isP(String s) {
		return judge(s,0,s.length()-1);
	}
	
}