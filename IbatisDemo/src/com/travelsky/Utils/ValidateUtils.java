package com.travelsky.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/** 
* @ClassName: ValidateUtils 
* @Description: validate utils class
* @author Yangshuang
* @date 2014-8-7 下午3:27:43 
*  
*/ 

public class ValidateUtils {
	
	/**
	 * regex of only number
	 */
	private final static String ONLY_NUMBER_REGEX = "^[0-9]*$";
	
	private final static String EMAIL_REGEX = "^\\w+[-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	private final static String TEL_NUMBER_REGEX = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$";
	
	
	
	
	/** 
	* @Title: stringNotNull 
	* @Description: validate a string is null or not
	* @param @param string
	* @param @return     
	* @return boolean    
	* @throws 
	*/ 
	
	private static boolean stringNotNull(String string) {
		return !string.equals(null);
	}
	
	
	
	
	/** 
	* @Title: onlyNumber 
	* @Description: check this string that is only number or not
	* @param @param number
	* @param @return    
	* @return boolean    
	* @throws 
	*/ 
	
	private static boolean onlyNumber(String number )
	{
		
		return checkRegex(ValidateUtils.ONLY_NUMBER_REGEX,number);
		
	}

	/** 
	* @Title: isEmail 
	* @Description: check this string that is email string  or not
	* @param @param email
	* @param @return    
	* @return boolean    
	* @throws 
	*/ 
	
	private static boolean isEmail(String email)
	{
		return checkRegex(EMAIL_REGEX, email);
	}
	
	
	/** 
	* @Title: isPhoneNumber 
	* @Description: check this string that is telephone number or not
	* @param @param phoneNumber
	* @param @return    
	* @return boolean    
	* @throws 
	*/ 
	
	private static boolean isPhoneNumber(String phoneNumber)
	{
		return checkRegex(TEL_NUMBER_REGEX, phoneNumber);
	}
	
	
	/** 
	* @Title: checkRegex 
	* @Description:  check the string is meet the regex
	* @param @param regex
	* @param @param checkString
	* @param @return    
	* @return boolean    
	* @throws 
	*/ 
	
	private static  boolean checkRegex(String regex,String checkString)
	{
		if(  null  == regex || null  == checkString || "".equals(regex) || "".equals(checkString) )
		{
			return false;
		}		
		return checkString.matches(regex);
		
		
	}
	
	
	
	/** 
	* @Title: validateObject 
	* @Description: validate a object ,this obj must be a pojo,and has a field which named <p>validateFieldRules </p>,and congig a config ,refrences of Class Rules,
	* @param @param obj  a Object which need validate
	* @param @return
	* @param @throws Exception    
	* @return boolean    
	* @throws 
	*/ 
	public static  boolean  validateObject(Object obj) throws Exception
	{			
		Method [] methods ;
		Field  valiField;		
		try {		
			methods = getAllMethodByIns(obj);
			Map<String,Method> methodLists  = new HashMap<String,Method>();
			for(Method m : methods)
			{
				String name = m.getName();
				if(name.startsWith("get")){
					methodLists.put(name.substring(3,name.length()).toLowerCase(), m)	;
				}				
			}			
			valiField = getValiField(obj);
			if(null == valiField)
			{
				throw new Exception("实体类中未配置校验规则！");
			}
			Object vali = (Object)valiField.get(obj);
			ValidateFieldRules [] valis = (ValidateFieldRules []) vali;
			for (int i = 0 ; i < valis.length ; i ++)
			{
				Method method = methodLists.get(valis[i].getField().toLowerCase());
				String rule = valis[i].getRule();
				if(!"".equals(rule) && method != null)
				{
					String value = (String)method.invoke(obj);				 
					if(rule.equals(Rules.NOT_NULL))
					{
						if(!stringNotNull(value)){
							throw new Exception(new ValidateMsg(valis[i].getField(),ValidateMsg.NULL_MSG).toString());
						}
					}
					else if (rule.equals(Rules.NUMBER))
					{
						if(!onlyNumber(value))
						{
							throw new Exception(new ValidateMsg(valis[i].getField(),ValidateMsg.ONLY_NUMBER_MSG).toString());
						}
					}
						
					else if (rule.equals(Rules.EMAIL))
					{
						if(!isEmail(value)){
							throw new Exception(new ValidateMsg(valis[i].getField(),ValidateMsg.EMAIL_MSG).toString());
						}
					}
							
					else if (rule.equals(Rules.TEL))
					{
						if( !isPhoneNumber(value))
						{
							throw new Exception(new ValidateMsg(valis[i].getField(),ValidateMsg.TEL_NUMBER_MSG).toString());
						}
					}					
				}
			}			
		} catch (SecurityException e) {
			throw new Exception(e);
		} catch (NoSuchFieldException e) {
			throw new Exception(e);
		} catch (IllegalArgumentException e) {			
			throw new Exception(e);
		} catch (IllegalAccessException e) {		
			throw new Exception(e);
		} catch (InvocationTargetException e) {			
			throw new Exception(e);
		}	
		return true;		
	}

	
	
	/** 
	* @Title: getAllMethodByIns 
	* @Description: get all declared method of a instance of a class 
	* @param @param obj
	* @param @return
	* @param @throws Exception    
	* @return Method[]    
	* @throws 
	*/ 
	
	private static Method [] getAllMethodByIns(Object obj) throws Exception
	{
		if(obj == null)
		{
			throw new Exception("validate object is null");
		}
		Class<? extends Object> clazz = obj.getClass();
		return clazz.getDeclaredMethods();
		
	}
	
	/** 
	* @Title: getValiField 
	* @Description: get a pojo's validatefield
	* @param @param obj
	* @param @return
	* @param @throws Exception
	* @param @throws SecurityException
	* @param @throws NoSuchFieldException    
	* @return Field    
	* @throws 
	*/ 
	
	private static Field getValiField(Object obj) throws Exception, SecurityException, NoSuchFieldException{
		if(obj == null)
		{
			throw new Exception("validate object is null");
		}
		Class<? extends Object> clazz = obj.getClass();
		Field [] fs = clazz.getDeclaredFields();
		for (int i = 0; i < fs.length; i++ )
		{
			String name = fs[i].getName();
			if("validateField".equals(name))
				return fs[i];
		}
		return null;
	}
	
	
	
	
}
