package com.example.demo.integratiom;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


@Service
public class MapperService {

	private ModelMapper modelMapper;
	
	public MapperService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration()
		  .setFullTypeMatchingRequired(true)
		  .setAmbiguityIgnored(true)
		  .setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public <D> D map(Object source, Class<D> destinationType) {
		return source == null ? null : modelMapper.map(source, destinationType);
	}

	public <S, D> List<D> mapList(List<S> source, Class<D> destinationType) {
		
		List<D> destination = null;
		
		if(source != null && !source.isEmpty()) {
			destination = new ArrayList<>();
			for(S element : source) {
				destination.add(map(element, destinationType));
			}
		}

		return destination;
	}
	
}
