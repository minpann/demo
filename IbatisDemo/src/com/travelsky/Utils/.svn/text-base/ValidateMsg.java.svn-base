package com.travelsky.Utils;

/** 
* @ClassName: ValidateMsg 
* @Description: 
* @author Yangshuang
* @date 2014-8-8 下午2:16:31 
*  
*/ 

public class ValidateMsg {
	
	private StringBuffer msg;
	
	public final static String  NULL_MSG = "the element  could not be null";
	public final static String  ONLY_NUMBER_MSG = "the element must be all number";
	public final static String  TEL_NUMBER_MSG = " the element must be a phone number,such as 023-88888888";
	public final static String  EMAIL_MSG = "the element  could not be a email,such as xxxx@xxx.xxx";
	
	
	public ValidateMsg()
	{
		msg = new StringBuffer();
	}
	
	/**
	 * @param element the validate element name
	 * @param staticString select string from validateMsgs
	 */
	public ValidateMsg(String element,String staticString)
	{
		msg = new StringBuffer();
		msg.append(element);
		msg.append(" ");
		msg.append(staticString);
	}

	@Override
	public String toString() {
		return msg.toString();
	}

	
	
}
