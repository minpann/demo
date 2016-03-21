package com.travelsky.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travelsky.Utils.ValidateUtils;
import com.travelsky.entity.Student;
import com.travelsky.service.StudentService.IStudentService;

@Controller
@RequestMapping("/Student")
public class StudentController  {

	private static  final  String VIEW_STUDENT = "/studentInfo";
	private static  final  String ADD_STUDENT = "/addStudent";
	
	@Resource
	private IStudentService studentService;
		

	@RequestMapping("/getAll")
	public ModelAndView findAll(HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();		
		 try {
			 List<Student> students = studentService.findAll();
			mav.addObject("students", studentService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "数据库连接错误!");
		}
		 mav.setViewName(VIEW_STUDENT);
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest req)
	{
		
		ModelAndView mav = new ModelAndView();		
		 try {
			 String id = req.getParameter("id");
				if(null != id && !"".equals(id))
				{
					int intId = Integer.parseInt(id);
					studentService.delete(intId);
					mav.addObject("message", "删除成功!");
				}
				else
				{
					mav.addObject("message", "提取信息失败!");
				}
			 List<Student> students = studentService.findAll();
			mav.addObject("students", studentService.findAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "删除失败!");
		}
		 mav.setViewName(VIEW_STUDENT);
		return mav;
	}

	@RequestMapping("/modify")
	public ModelAndView modify(HttpServletRequest req,Student stu)
	{
		
		ModelAndView mav = new ModelAndView();
		 try {
			 if(null == stu){
				 mav.addObject("message","数据提交失败!");
				 return mav;
			 }
			 if(ValidateUtils.validateObject(stu))
			 {
				 if( 0 == stu.getId())
				 {
					 studentService.save(stu);
				 }
				 else{
					 studentService.update(stu);
				 }
			 }	 
			mav.addObject("message", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "修改失败!错误信息:" + e.getMessage());
		}
		 mav.addObject("students", studentService.findAll());
		 mav.setViewName(VIEW_STUDENT);
		return mav;
	}
	@RequestMapping("/goModify")
	public ModelAndView goModify(HttpServletRequest req){
		ModelAndView mav = new ModelAndView();	
		String id = req.getParameter("id");
		if(null != id)
		{
			Student stu = studentService.findById(Integer.parseInt(id));
			mav.addObject("student", stu);
		}
		mav.setViewName(ADD_STUDENT);
		return mav;
	}
	
	@RequestMapping("findByParams")
	public ModelAndView findByParams(HttpServletRequest req,Student stu)
	{

		ModelAndView mav = new ModelAndView();		
		 try {
			if(null != stu)
			{
				mav.addObject("students", studentService.findStuByParams(stu));
			}
			else
			{
				mav.addObject("students", studentService.findAll());
				mav.addObject("message", "查询数据接收失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "数据库连接错误!");
		}
		 mav.setViewName(VIEW_STUDENT);
		return mav;
	}
 
}
