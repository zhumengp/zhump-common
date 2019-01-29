package com.zhump.stringCommon;

import java.util.List;


/**
 * 字符判断类
 * @author zhump
 * @Date 2018/12/1
 *
 */
public class StringUtils {
	
	
	/**
	 * 判断字符串为空
	 * @param String param = ""  true
	 * 
	 * @param String param = null  true
	 * 
	 * @param String param = " "  true
	 * 
	 */
	public static boolean isBlank(String str){
		if(str == null || str.length() == 0 || str.trim().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * String param = "dfd"  true
	 * 
	 * String param = 1215  true
	 * 
	 * String param = " dfd"  true
	 * 
	 * String param = ""	false
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	/**
	 * 判断集合是否为空
	 * @param list
	 * 如果集合为空则为true，集合有值时为false
	 * @return
	 */
	public static boolean isEmpty(List list){
		if(list == null || list.size() == 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 判断集合不为空
	 * @param args
	 * 集合里面有值为true，如果为空就显示false
	 */
	public static boolean isNotEmpty(List list){
		return !isEmpty(list);
	}
	
	/**
	 * 判断对象是否为空
	 * @param args
	 */
	public static boolean isObject(Object obj){
		if(obj == null || obj.toString() == null){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		Object obj = new Object();
		System.out.println(obj.toString());
		System.out.println(StringUtils.isObject(obj));
	}
	
	

}
