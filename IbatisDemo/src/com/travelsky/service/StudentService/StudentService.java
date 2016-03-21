package com.travelsky.service.StudentService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelsky.dao.StudentDao;
import com.travelsky.entity.Student;

@Service
@Transactional
public class StudentService implements IStudentService {

	@Resource
    private StudentDao studentDao;
	
	@Override
	public void save(Student t) {
		studentDao.save(t);
		
	}

	@Override
	public void delete(Integer id) {
		studentDao.delete(id);
		
	}

	@Override
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	@Override
	public Student findById(Integer id) {
		return studentDao.findById(id);
	}

	@Override
	public void update(Student t) {
		studentDao.update(t);
		
	}

	@Override
	public List<Student> findStuByParams(Student t) {
		return studentDao.findStuByParams(t);
	}

}
