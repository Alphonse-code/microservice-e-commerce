package com.dev.commons.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BasicMapper {

	private ModelMapper mapper;

	public <T, S> S convertTo(T data, Class<S> type) {
		return mapper.map(data, type);
	}

	public <T, S> List<S> convertListTo(List<T> dataList, Class<S> type) {
		return dataList.stream().map(data -> mapper.map(data, type)).collect(Collectors.toList());
	}
}
