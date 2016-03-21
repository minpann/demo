package com.travelsky.entity;
import java.io.Serializable;

import com.travelsky.Utils.Rules;
import com.travelsky.Utils.ValidateFieldRules;




/** 
* @ClassName: Student 
* @Description: 
* @author 
* @date 2014-8-13 下午2:20:42 
*  
*/ 

public class Student implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9158740741787161153L;

	
	public static final ValidateFieldRules [] validateField = {new ValidateFieldRules("name", Rules.NOT_NULL),
		new ValidateFieldRules("course", Rules.NOT_NULL),new ValidateFieldRules("score", Rules.NUMBER)};

	private long id;
	
	
	private String name;
	
	
	private String course;
	
	
	private String score;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	

}