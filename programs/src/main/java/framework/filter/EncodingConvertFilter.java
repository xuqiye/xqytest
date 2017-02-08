package framework.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 请求编码过滤器
 * @author xuqiye
 * 
 */
public class EncodingConvertFilter extends OncePerRequestFilter{
	
	/**
	 * 页面过来的编码
	 */
	private String fromEncoding;
	
	/**
	 * 要转变的编码
	 */
	private String toEncoding;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")){
			for(Iterator<String[]> iterator = request.getParameterMap().values().iterator(); iterator.hasNext();){
				String[] as = (String[])iterator.next();
				for(int i = 0;i<as.length;i++){
					try{
						as[i] = new String(as[i].trim().getBytes(fromEncoding),toEncoding);
					}catch(UnsupportedEncodingException unsupportedencodingexception){
						unsupportedencodingexception.printStackTrace();
					}
					
				}
			}
		}
		filterChain.doFilter(request, response);
		
	}

	/**
	 * 获取页面请求的编码
	 * 
	 * @return 页面编码
	 */
	public String getFromEncoding() {
		return fromEncoding;
	}
	/**
	 * 设置页面请求编码
	 * 
	 * @param fromEncoding
	 *            页面编码
	 */
	public void setFromEncoding(String fromEncoding) {
		this.fromEncoding = fromEncoding;
	}
	/**
	 * 获取要转变的编码
	 * 
	 * @return 要转变的编码
	 */
	public String getToEncoding() {
		return toEncoding;
	}
	/**
	 * 设置要转变的编码
	 * 
	 * @param toEncoding
	 *            要转变的编码
	 */
	public void setToEncoding(String toEncoding) {
		this.toEncoding = toEncoding;
	}

	
}
