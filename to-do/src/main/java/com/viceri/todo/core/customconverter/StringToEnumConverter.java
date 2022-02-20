package com.viceri.todo.core.customconverter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.viceri.todo.domain.models.Prioridade;

public class StringToEnumConverter implements com.fasterxml.jackson.databind.util.Converter<String, Prioridade> {
   
	@Override
    public Prioridade convert(String source) {
		try {
        return Prioridade.valueOf(source.toUpperCase());
	
		}catch(IllegalArgumentException e) {
			return null;
		}
    }

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		return null;
	}
}
