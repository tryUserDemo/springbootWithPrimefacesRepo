package com.example.demo.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.integratiom.MapperService;


public abstract class BaseService {

	@Autowired
	protected MapperService mapperService;
	
	public <D> D map(Object source, Class<D> destination) {
		return mapperService.map(source, destination);
	}
	
	public <S, D> List<D> mapList(List<S> source, Class<D> destination) {
		return mapperService.mapList(source, destination);
	}
	
	public <T> void deleteDataById(JpaRepository<T, Long> repo, Long id) {
		repo.deleteById(id);
	}
	
	public <T> T findDataById(JpaRepository<T, Long> repo, Long id) {
		return repo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
	}
	
}
