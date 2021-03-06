package com.hab.bms.sys.basicdata.tools.exceptions;

import com.hab.bms.sys.utils.ExceptionUtil;
import com.hab.bms.sys.utils.StrUtil;

/**
 * 未初始化异常
 */
public class UtilException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4743795389157328397L;

	public UtilException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}
	
	public UtilException(String message) {
		super(message);
	}
	
	public UtilException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}
	
	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public UtilException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
