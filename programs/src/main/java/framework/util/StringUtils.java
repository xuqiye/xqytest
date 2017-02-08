package framework.util;

/**
 * 字符串工具类
 * @author xqy
 *
 */
public final class StringUtils {
	/**
	 * 将obj转换为字符串，如果obj为null，转换为""
	 * @param obj
	 * @return
	 */
	public static String getNullStr(Object obj){
		if(obj==null){
			return "";			
		}
		return obj.toString();
	}
	/**
	 * 判断对象是否不为空且不为空串
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj){
		if(obj == null || "".equals(obj)){
			return true;
		}
		return false;
		
	}
}
