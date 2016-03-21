package com.travelsky.dao;

import java.util.List;

import com.travelsky.entity.Student;

public interface StudentDao {
	public void save(Student stu);
	public void delete(Integer id);
	public List<Student> findAll();
	public Student findById(Integer id);
	public void update(Student stu);
	public List<Student> findStuByParams(Student stu);
}
