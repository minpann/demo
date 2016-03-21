package com.travelsky.service.BaseService;

import java.util.List;



public interface IBaseService<T> {
	
	public void save(T t);
	public void delete(Integer id);
	public List<T> findAll();
	public T findById(Integer id);
	public void update(T t);
	public List<T> findStuByParams(T t);

}
