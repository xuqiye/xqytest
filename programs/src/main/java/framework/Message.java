package framework;

import framework.util.SpringUtils;

/**
 * 请求消息
 * @author XQY
 *
 */
public class Message {
	/**
	 * 消息类型
	 * @author xqy
	 * 
	 */
	public enum Type{
		SUCCESS,ERROR,WARN
	}
	/**
	 *消息类型 
	 */
	private Type type;
	/**
	 * 消息内容
	 */
	private String content;
	
	
	public Message() {
		
	}
	
	public Message(Type type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public Message(Type type, String content, Object ... args) {
		this.type = type;
		this.content = SpringUtils.getMessage(content, args);
	}

	public static Message success(String content, Object ... args) {
		return new Message(Type.SUCCESS, content, args);
	}

	public static Message warn(String content, Object  ... args) {
		return new Message(Type.WARN, content, args);
	}

	public static Message error(String content, Object ... args) {
		return new Message(Type.ERROR, content, args);
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
	
}
