package com.example.test.service;

import com.example.test.entity.ValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.repository.ValueRepository;

import java.util.Optional;

@Service
public class ValueService {
    private final ValueRepository valueRepository;

    @Autowired
    public ValueService(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public int getValue() {
        Optional<ValueEntity> valueEntityOptional = valueRepository.findById(1L);
        return valueEntityOptional.map(ValueEntity::getCountedValue).orElse(0);
    }

    public void setValue(int value) {
        Optional<ValueEntity> valueEntityOptional = valueRepository.findById(1L);
        ValueEntity valueEntity = valueEntityOptional.orElse(new ValueEntity());
        valueEntity.setCountedValue(value);
        valueRepository.save(valueEntity);
    }
}



