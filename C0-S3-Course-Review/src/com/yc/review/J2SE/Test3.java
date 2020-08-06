package com.yc.review.J2SE;
/**
 * 判断回文
 * @author 贺井龙
 *
 */
public class Test3 {

	public static void main(String[] args) {
		System.out.println("adcdedcba是回文吗"+isP("abcdedcba"));
	}
	
	public static boolean isP(String s) {
		if(s.length()<=1) {
			return true;
		} else if(s.charAt(0)!=s.charAt(s.length()-1)) {
			return false;
		} else {
			String newString = s.substring(1,s.length()-1);
			return isP(newString);
		}
	}
}
